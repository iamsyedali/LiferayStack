package com.liferaystack.products.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
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
	public void addProductAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
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
		_log.info("addProductAction completed...");
	}
	
	@ProcessAction(name="editProductAction")
	public void editProductAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		_log.info("editProductAction....");
	}
	
	@ProcessAction(name="deleteProductAction")
	public void deleteProductAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		_log.info("deleteProductAction....");
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		_log.info("render....");
		List<Product> products = ProductLocalServiceUtil.getProducts(-1, -1);
		renderRequest.setAttribute("products",products);
		//_log.info("products : products "+products.size());
		super.render(renderRequest, renderResponse);
	}
}