package com.liferay.docs.product.asset;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.docs.product.constants.ProductWebPortletKeys;
import com.liferay.docs.product.service.permission.ProductPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import products.model.Product;

public class ProductAssetRenderer extends BaseJSPAssetRenderer<Product>{
	
	public ProductAssetRenderer(Product product) {
        _product = product;
	}
	
	private Product _product;
	private static Log _log = LogFactoryUtil.getLog(ProductAssetRenderer.class);
	
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) 
	throws PortalException {

	  long productId = _product.getProductId();
	  return ProductPermission.contains(permissionChecker, productId, 
	  ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) 
	throws PortalException {

	  long productId = _product.getProductId();
	  return ProductPermission.contains(permissionChecker, productId, 
	  ActionKeys.VIEW);
	}
	
	@Override
	public Product getAssetObject() {
		return _product;
	}
	@Override
	public long getGroupId() {
		return _product.getGroupId();
	}
	@Override
	public long getUserId() {
		return _product.getUserId();
	}
	@Override
	public String getUserName() {
		return _product.getUserName();
	}
	@Override
	public String getUuid() {
		return _product.getUuid();
	}
	@Override
	public String getClassName() {
		return Product.class.getName();
	}
	@Override
	public long getClassPK() {
		return _product.getProductId();
	}
	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		 return "Name: " + _product.getName();
	}
	@Override
	public String getTitle(Locale locale) {
		return  "Name: " + _product.getName();
	}
	
	@Override
	public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {
	    request.setAttribute("PRODUCT", _product);
	    request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
	    request.setAttribute("StringUtil", new StringUtil());
	    return super.include(request, response, template);
	}
	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {

	    if (template.equals(TEMPLATE_FULL_CONTENT)) {
	      request.setAttribute("gb_product", _product);
	      return "/asset/product/" + template + ".jsp";
	    } else {
	      return null;
	    }
	}
	 @Override
	  public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
	      LiferayPortletResponse liferayPortletResponse) throws Exception {
	    PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
	        getControlPanelPlid(liferayPortletRequest), ProductWebPortletKeys.ProductWeb,
	        PortletRequest.RENDER_PHASE);
	    portletURL.setParameter("mvcRenderCommandName", "/productwebportlet/edit_product");
	    portletURL.setParameter("productId", String.valueOf(_product.getProductId()));
	    portletURL.setParameter("showback", Boolean.FALSE.toString());
	    _log.info("getURLEdit : portletURL : "+portletURL.toString());
	    return portletURL;
	  }
	 

	 @Override
	  public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,
	      LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) throws Exception {
	    try {
	      long plid = PortalUtil.getPlidFromPortletId(_product.getGroupId(),
	    		  ProductWebPortletKeys.ProductWeb);

	      PortletURL portletURL;
	      if (plid == LayoutConstants.DEFAULT_PLID) {
	        portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(liferayPortletRequest),
	        		ProductWebPortletKeys.ProductWeb, PortletRequest.RENDER_PHASE);
	      } else {
	        portletURL = PortletURLFactoryUtil.create(liferayPortletRequest,
	        		ProductWebPortletKeys.ProductWeb, plid, PortletRequest.RENDER_PHASE);
	      }

	      portletURL.setParameter("mvcRenderCommandName", "/productwebportlet/view");
	      portletURL.setParameter("productId", String.valueOf(_product.getProductId()));

	      String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);

	      portletURL.setParameter("redirect", currentUrl);

	      return portletURL.toString();

	    } catch (PortalException e) {
	    	_log.error(e.getMessage());

	    } catch (SystemException e) {
	    	_log.error(e.getMessage());
	    }
	    return noSuchEntryRedirect;
	  }
	 
	 @Override
	  public String getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) throws Exception {
	    return super.getURLView(liferayPortletResponse, windowState);
	  }
}
