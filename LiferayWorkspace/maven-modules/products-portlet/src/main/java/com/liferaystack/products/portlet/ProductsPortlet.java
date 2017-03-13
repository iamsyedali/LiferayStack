package com.liferaystack.products.portlet;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaystack.products.model.Product;
import com.liferaystack.products.service.ProductLocalServiceUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=liferaystack",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"javax.portlet.display-name=Products Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ProductsPortlet extends MVCPortlet {
	
private static Log _log = LogFactoryUtil.getLog(ProductsPortlet.class);
	
	@ProcessAction(name="addProductAction")
	public void addProductAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException, PortalException {
		_log.info("addProductAction....");
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		Product product = ProductLocalServiceUtil.createProduct(CounterLocalServiceUtil.increment(Product.class.getName()));
		product.setName(name);
		product.setGroupId(groupId);
		long userI =  themeDisplay.getUserId();
		product.setUserId(userI);
		product.setDescription(description);
		product.setCompanyId(themeDisplay.getCompanyId());
		product.setCreateDate(new Date());
		product.setModifiedDate(new Date());
		ProductLocalServiceUtil.updateProduct(product);
		
		
		
		Date modifiedDate = new Date();
		String classUuid = product.getUuid();
		long classPK = product.getProductId();
		long[] categoryIds = null;
		String className = Product.class.getName();
		long classTypeId = 0;
		boolean listable = false;
		Date createDate = new Date();
		String layoutUuid = null;
		String mimeType = null;
		Date expirationDate = null;
		String url = null;
		Date endDate = null;
		Date startDate = new Date();
		String title = product.getName();
		String[] tagNames = null;
		boolean visible = true;
		int height = 0;
		int width = 0;
		String summary = product.getName()+" : "+product.getDescription();
		Double priority = null;
		
		AssetEntry updateEntry = AssetEntryLocalServiceUtil.updateEntry(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), createDate, modifiedDate, className, classPK, classUuid, classTypeId, categoryIds,
				tagNames, listable, visible, startDate, endDate, expirationDate, mimeType, title, description, summary, url, layoutUuid, height, width, priority);
		
	
		/*AssetEntry assetEntry = AssetEntryLocalServiceUtil.updateEntry( themeDisplay .getUserId(), themeDisplay.getScopeGroupId(), new Date(),
	            new Date(), Product.class.getName(),product.getProductId(), product.getUuid(), 0, null, null, true, false, new Date(), null,
	            new Date(), null, ContentTypes.TEXT_HTML, product.getName(), product.getName(), null, null, null, 0, 0, null);*/
		_log.info("addProductAction completed...");
	}
	
	@ProcessAction(name="editProductAction")
	public void editProductAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		long productId = ParamUtil.getLong(actionRequest, "productId");
		_log.info("editProductAction : productId : "+productId);
		try {
			Product product = ProductLocalServiceUtil.getProduct(productId);
			String name = ParamUtil.getString(actionRequest, "name");
			String description = ParamUtil.getString(actionRequest, "description");
			product.setName(name);
			product.setDescription(description);
			product.setModifiedDate(new Date());
			ProductLocalServiceUtil.updateProduct(product);
		} catch (PortalException e) {
			_log.error("PortalException : While fetching the Product : "+e.getMessage());
		}
	}
	
	@ProcessAction(name="deleteProductAction")
	public void deleteProductAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		long productId = ParamUtil.getLong(actionRequest, "productId");
		try {
			ProductLocalServiceUtil.deleteProduct(productId);
		} catch (PortalException e) {
			_log.error("PortalException : While deleting the Product : "+e.getMessage());
		}
		_log.info("editProductAction : productId : "+productId);
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		_log.info("render....");
		List<Product> products = ProductLocalServiceUtil.getProducts(-1, -1);
		renderRequest.setAttribute("products",products);
		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");
		//_log.info("products : products "+products.size());
		if(mvcPath .equals("/edit-product.jsp")){
			_log.info("/edit-product.jsp");
			long productId = ParamUtil.getLong(renderRequest, "productId");
			try{
				renderRequest.setAttribute("product", ProductLocalServiceUtil.getProduct(productId));
			}
			catch (PortalException e) {
				_log.error("PortalException : While fetching the Product : "+e.getMessage());
			}
			
		}
		super.render(renderRequest, renderResponse);
	}
}