package my.rest.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Syed Ali
 */
@ApplicationPath("/users12")
@Component(immediate = true, service = Application.class)
public class MyRestApplication extends Application {
	

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Produces(ContentTypes.TEXT_PLAIN)
	public String working() {
		return "It works!";
	} 

	
	@GET
	@Path("/getuser/{userId}")
	@Produces(ContentTypes.TEXT_PLAIN)
	public String morning(
		@PathParam("userId") long userId) {
		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(userId);
		} catch (PortalException e) {
			System.out.println("PortalException : "+e.getMessage());
		}
		if(Validator.isNull(user)){
			return "[No User Found with userId:"+userId+"]";
		}
		return user.toString();
	}

}