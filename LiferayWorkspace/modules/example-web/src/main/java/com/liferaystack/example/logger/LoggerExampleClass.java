package com.liferaystack.example.logger;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferaystack.example.portlet.ExampleWebPortlet;

/**
 * @author Syed Ali
 */

public class LoggerExampleClass {

	private static Log _log = LogFactoryUtil.getLog(ExampleWebPortlet.class);
	
	public void processAction(){
		
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
	}
}
