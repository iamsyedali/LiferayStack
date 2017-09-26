<%@ include file="init.jsp" %>
<%@page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>

<%

	// To Log Messages
	log("You can print message using log nethod in JSP");
	
	// To Print Defined Message and error
	try {
		UserLocalServiceUtil.getUser(0);
	} catch (PortalException e) {
		log("User Not Found Message ",e);
	}

%>