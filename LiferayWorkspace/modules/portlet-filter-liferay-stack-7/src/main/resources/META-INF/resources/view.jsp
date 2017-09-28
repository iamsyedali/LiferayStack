<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:actionURL var="doActionURL"/>
<portlet:renderURL var="doRenderURL"/>
<portlet:resourceURL var="doResourceURL"/>

<aui:button href="${doActionURL}" value="Do Action"/>
<aui:button href="${doRenderURL}" value="Do Render"/>
<aui:button href="${doResourceURL}" value="Do Resource" />