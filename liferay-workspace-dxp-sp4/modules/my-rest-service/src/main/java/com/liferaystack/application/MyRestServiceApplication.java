package com.liferaystack.application;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Syed Ali
 */

@ApplicationPath("/users")
@Component(immediate = true, service = Application.class)
public class MyRestServiceApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Produces("text/plain")
	public String working() {
		return "Web Service Works!";
	}

	@GET
	@Path("/getalluser")
	@Produces(ContentTypes.TEXT_PLAIN)
	public String hello() {
		List<User> users = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS,QueryUtil.ALL_POS);
		return users.toString();
	}

	@GET
	@Path("/getuser/{userId}")
	@Produces(ContentTypes.TEXT_PLAIN)
	public String morning(
		@PathParam("userId") long userId,
		@QueryParam("param") String param) {

		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(userId);
		} catch (PortalException e) {
			_log.error("PortalException : "+e.getMessage()); 
		}

		if(Validator.isNull(user)){
			return "[No User Found With User Id : "+userId+"]";
		}
		return user.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(MyRestServiceApplication.class);
}