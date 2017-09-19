<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<aui:button id="openDiv" value="Open Popup Using Modal Dialog" />

<br>
<div id="my-content-div">
	<div>
		DIVContent Is Rendered In The AUI Modal Popup.
	</div>
</div>

<!-- AUI Script For Modal Dialog POPUP -->
<aui:script use="aui-modal,aui-overlay-manager">
A.one("#<portlet:namespace />openDiv").on('click',function(event){
	var dialog = new A.Modal({
		title: "AUI Modal Popup Title",
		bodyContent: A.one("#my-content-div").html(),
		headerContent: 'AUI Modal Heading',
		centered: true,
		modal: true,
		height: 200,
		width:300,
		render: '#my-content-div',
		close: true
	});
	dialog.render();
});
</aui:script>