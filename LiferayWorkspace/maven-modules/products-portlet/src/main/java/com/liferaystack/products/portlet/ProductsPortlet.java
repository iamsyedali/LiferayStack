package com.liferaystack.products.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferaystack.products.model.Product;
import com.liferaystack.products.service.ProductLocalServiceUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=liferaystack",
		"com.liferay.portlet.instanceable=true",
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
	
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		_log.info("processAction....");
		super.processAction(actionRequest, actionResponse);
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		_log.info("render....");
		List<Product> products = ProductLocalServiceUtil.getProducts(-1, -1); 
		_log.info("products : products "+products.size());
		super.render(renderRequest, renderResponse);
	}
}