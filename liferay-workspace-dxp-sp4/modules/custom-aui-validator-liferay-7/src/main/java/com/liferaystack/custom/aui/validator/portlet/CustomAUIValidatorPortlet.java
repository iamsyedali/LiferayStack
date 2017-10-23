package com.liferaystack.custom.aui.validator.portlet;

import com.liferaystack.custom.aui.validator.constants.CustomAUIValidatorPortletKeys;

import java.io.IOException;
import java.io.PrintWriter;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ali
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=custom-aui-validator-liferay-7 Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CustomAUIValidatorPortletKeys.CustomAUIValidator,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CustomAUIValidatorPortlet extends MVCPortlet {
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		String emailAddress = ParamUtil.getString(resourceRequest, "emailAddress");
		User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, emailAddress);
		
		PrintWriter out = resourceResponse.getWriter();
		
		if(Validator.isNotNull(user)){
			out.print(true);
		}else{
			out.print(false);
		}
	}
}