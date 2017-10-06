package com.liferay.docs.product.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.docs.product.constants.ProductWebPortletKeys;
import com.liferay.docs.product.service.permission.ProductPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import products.model.Product;
import products.service.ProductLocalServiceUtil;


@Component(immediate = true, 
  property = {"javax.portlet.name=" + ProductWebPortletKeys.ProductWeb}, 
  service = AssetRendererFactory.class
  )
public class ProductAssetRendererFactory extends 
  BaseAssetRendererFactory<Product> {

  public ProductAssetRendererFactory() {
    setClassName(CLASS_NAME);
    setLinkable(_LINKABLE);
    setPortletId(ProductWebPortletKeys.ProductWeb);
    setSearchable(true);
    setSelectable(true);
  }

  @Override
  public AssetRenderer<Product> getAssetRenderer(long classPK, int type) 
  throws PortalException {

    Product guestbook = _productLocalServiceUtil.getProduct(classPK);

    ProductAssetRenderer guestbookAssetRenderer = 
    new ProductAssetRenderer(guestbook);

    guestbookAssetRenderer.setAssetRendererType(type);
    guestbookAssetRenderer.setServletContext(_servletContext);

    return guestbookAssetRenderer;
  }


@Reference(
		target = "(osgi.web.symbolicname=com.liferaystack.products.web)",
		unbind = "-"
)
public void setServletContext(ServletContext servletContext) {
	_log.info("setServletContext");
	_servletContext = servletContext;
}

@Reference(unbind = "-")
protected void setProductLocalServiceUtil(ProductLocalServiceUtil productLocalServiceUtil) {
	_log.info("setProductLocalServiceUtil");
	_productLocalServiceUtil = productLocalServiceUtil;
}

@Override
public String getClassName() {
  return CLASS_NAME;
}

@Override
public String getType() {
  return TYPE;
}

@Override
public boolean hasPermission(PermissionChecker permissionChecker, 
long classPK, String actionId) throws Exception {

  Product guestbook = _productLocalServiceUtil.getProduct(classPK);
  
  return ProductPermission.contains(permissionChecker, guestbook, 
  actionId);
}

@Override
public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
    LiferayPortletResponse liferayPortletResponse, long classTypeId) {
  PortletURL portletURL = null;

  try {
    ThemeDisplay themeDisplay = (ThemeDisplay) 
    liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

    portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),
    		ProductWebPortletKeys.ProductWeb, PortletRequest.RENDER_PHASE);
    portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_guestbook");
    portletURL.setParameter("showback", Boolean.FALSE.toString());
  } catch (PortalException e) {
  }

  return portletURL;
}

@Override
public boolean isLinkable() {
  return _LINKABLE;
}

@Override
public String getIconCssClass() {
    return "bookmarks";
}

private static final boolean _LINKABLE = true;
public static final String CLASS_NAME = Product.class.getName();
public static final String TYPE = "product";
private ProductLocalServiceUtil _productLocalServiceUtil;
private static Log _log = LogFactoryUtil.getLog(ProductAssetRendererFactory.class);
private ServletContext _servletContext;
}