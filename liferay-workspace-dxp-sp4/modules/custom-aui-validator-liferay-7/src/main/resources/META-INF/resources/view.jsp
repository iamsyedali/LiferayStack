<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<aui:input name="emailAddress">
	<aui:validator name="numericCheck" errorMessage="Numeric Value Not Allowed in Email">
		function (val, fieldNode, ruleValue) {
		var result = false;
		alert("hi");
		<!-- if (val >=18) {
			result = true;
		} -->
		return result;
		}
	</aui:validator>
	
	<aui:validator name="emailExistCheck" errorMessage="This Email Is Already Taken">
		function (val, fieldNode, ruleValue) {
		var result = false;
		alert("hi");
		<!-- if (val >=18) {
			result = true;
		} -->
		return result;
		}
	</aui:validator>
	<aui:button type="submit"/>
</aui:input>