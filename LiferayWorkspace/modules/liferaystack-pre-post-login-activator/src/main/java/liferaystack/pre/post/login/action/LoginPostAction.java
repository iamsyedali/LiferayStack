package liferaystack.pre.post.login.action;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Syed Ali
 * 
 */

@Component(
	immediate = true,
	property = {
        "key=login.events.post"
    },
    service = LifecycleAction.class
)
public class LoginPostAction implements LifecycleAction {

    @Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

    	long userId = 0;
    	HttpServletRequest request = lifecycleEvent.getRequest();
    	try {
			userId = PortalUtil.getUser(request).getUserId();
		} catch (PortalException e) {
			e.printStackTrace();
		}
		System.out.println("login.event.post, userId : " +userId);
	}

}
