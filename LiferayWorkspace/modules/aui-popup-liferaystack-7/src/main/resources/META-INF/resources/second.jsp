<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<portlet:actionURL var="actionURL"/>

<h2>Second JSP</h2>

<aui:form action="${actionURL}" >
	<aui:button type="submit" value="action"/>
	
	<aui:button type="cancel" value="cancel" id="cancelButton"/>
	<!-- 
		^If we give the button type as 'cancel' then the popup will be closed by liferay 
		We Dont Have to write explicit code to close the popup 
	-->
</aui:form>