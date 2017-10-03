package com.liferaystack.products.web.portlet;

import java.io.IOException;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaystack.products.web.constants.ProductWebPortletKeys;

import products.model.Product;
import products.service.ProductLocalServiceUtil;

/**
 * @author Syed Ali
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=products-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.name=" + ProductWebPortletKeys.ProductWeb
	},
	service = Portlet.class
)
public class ProductsWebPortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(ProductsWebPortlet.class);
	
	public void addProduct(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException, PortalException {
		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		_log.info("addProduct : name : "+name+", description : "+description);
		ServiceContext serviceContext = ServiceContextFactory.getInstance(Product.class.getName(), actionRequest);
		ProductLocalServiceUtil.addProductMy(name,description, serviceContext);
	}
}