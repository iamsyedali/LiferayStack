package com.liferaystack.portlet.aui.popup.portlet;

import com.liferaystack.portlet.aui.popup.constants.AuiPopupLiferaystack7PortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Syed Ali
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=LiferayStack",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AUI Popup Portlet LiferayStack",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AuiPopupLiferaystack7PortletKeys.AuiPopupLiferaystack7,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AuiPopupLiferaystack7Portlet extends MVCPortlet {
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
	
		System.out.println("processAction executed.....");
		SessionMessages.add(actionRequest, "success");// for success message after action 
		super.processAction(actionRequest, actionResponse);
	}
}