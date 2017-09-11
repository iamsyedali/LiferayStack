package com.liferaystack.spring.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

/**
 * @author Syed Ali
 */

@Controller
@RequestMapping("VIEW")
public class SpringMvcPortletLiferay7PortletViewController {

	@ActionMapping
	public void defaultAction(ActionRequest request, ActionResponse actionResponse) {
		System.out.println("Default Action.....");
	}

	@RenderMapping
	public String defaultRender(RenderRequest renderRequest, RenderResponse renderResponse) {
		System.out.println("Default Render.....");
		return "view";
	}
	
	@ActionMapping (params = "action=myAction")
	public void myAction(ActionRequest actionRequest, ActionResponse actionResponse) {
		System.out.println("myAction.....");
	}
	
	@RenderMapping (params = "render=myRender")
	public String myRender(RenderRequest renderRequest, RenderResponse renderResponse){
		System.out.println("myRender for second.JSP.....");
		return "second";
	}
	
	@ResourceMapping (value = "myResource")
	public void myResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		System.out.println("myResource.....");
	}
}