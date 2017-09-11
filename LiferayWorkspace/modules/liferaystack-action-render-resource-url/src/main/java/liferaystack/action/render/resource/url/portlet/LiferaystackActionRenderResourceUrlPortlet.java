package liferaystack.action.render.resource.url.portlet;

import liferaystack.action.render.resource.url.constants.LiferaystackActionRenderResourceUrlPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Syed Ali
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=liferaystack-action-render-resource-url Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LiferaystackActionRenderResourceUrlPortletKeys.LiferaystackActionRenderResourceUrl,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class LiferaystackActionRenderResourceUrlPortlet extends MVCPortlet {
	
	public void doActionMethod(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		System.out.println("doActionMethod Triggered");
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		String title = ParamUtil.getString(resourceRequest,"title");
		
		if(title.equalsIgnoreCase("Liferaystack")){
			System.out.println("Enetered Title is LiferayStack ");
			resourceResponse.getWriter().print("true");
		}else{
			System.out.println("Enetered Title is NOT LiferayStack ");
			resourceResponse.getWriter().print("false");
		}
	}
}