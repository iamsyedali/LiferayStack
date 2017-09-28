package com.liferaystack.portlet;

import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferaystack.constants.MyPortletKeys;
import org.osgi.service.component.annotations.Component;

/**
 * @author Syed Ali
 */

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=LiferayStack",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Portlet Filter - Liferay Stack",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MyPortletKeys.My,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)

public class MyPortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(MyPortlet.class);
	
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		_log.info("processAction.....");
		super.processAction(actionRequest, actionResponse);
	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		_log.info("render.....");
		super.render(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, PortletException {
		_log.info("serveResource.....");
		resourceResponse.getWriter().print("Serve Resource Triggered....");
		//super.serveResource(resourceRequest, resourceResponse);
	}
}