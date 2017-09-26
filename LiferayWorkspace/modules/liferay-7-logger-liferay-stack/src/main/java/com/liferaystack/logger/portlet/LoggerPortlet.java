package com.liferaystack.logger.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferaystack.logger.constants.LoggerPortletKeys;

/**
 * @author Syed Ali
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=liferay-7-logger-liferay-stack Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LoggerPortletKeys.Logger,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class LoggerPortlet extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(LoggerPortlet.class);
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)throws IOException, PortletException {
		
		System.out.println("......Sysout Message Using System.out.println....");
		
		//Different Log Levels
		_log.info("Use INFO To Log Useful Information");
		_log.warn("Use WARN To Log Warning Message");
		_log.error("Use ERROR To Log Error Message ");
		_log.debug("Use DEBUG To Log Debug Message ");
		_log.fatal("Use DEBUG To Log Fatal Message");
		_log.trace("Use TRACE To Log Trace Message of the exception");
		
		// To Check Whether Log is Enabled for INFO Level  
		if(_log.isInfoEnabled()){
			_log.info("This Messge Will be printed only if the info log level is enbled"
					+ " for the class 'LoggerExampleClass' from Liferay Server Administration");
		}
		
		//To Check whether the log level is Enabled for other Log Levels
		_log.isInfoEnabled();
		_log.isWarnEnabled();
		_log.isErrorEnabled();
		_log.isDebugEnabled();
		_log.isFatalEnabled();
		_log.isTraceEnabled();
		
		// To Log only error
		try {
			UserLocalServiceUtil.getUser(0);
		} catch (PortalException e) {
			_log.error(e);
		}
		
		// To Log Defined Message and error
		try {
			UserLocalServiceUtil.getUser(0);
		} catch (PortalException e) {
			_log.error("User Not Found Message ",e);
		}
		
		// To Set LogWrapper
		_log.setLogWrapperClassName(CustomLogWrapper.class.getName());
		
		super.render(renderRequest, renderResponse);
	}
}