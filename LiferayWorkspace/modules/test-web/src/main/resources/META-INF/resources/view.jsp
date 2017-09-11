<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:actionURL var="doActionVariable" name="doActionMethodName" windowState="<%=LiferayWindowState.NORMAL%>">
	<portlet:param name="title" value="LiferayStack"/>
</portlet:actionURL>