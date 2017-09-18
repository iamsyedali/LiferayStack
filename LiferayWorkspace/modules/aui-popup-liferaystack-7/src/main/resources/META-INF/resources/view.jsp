<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<portlet:renderURL var="secondJspURL" windowState="<%=LiferayWindowState.POP_UP.toString()%>">
	<portlet:param name="mvcPath" value="/second.jsp"/>
</portlet:renderURL>

<liferay-ui:success key="success" message="Your Action Completed Successfully..."/>

<aui:button href="${secondJspURL}" useDialog="true" value="Open Popup Using useDialog" />

<aui:button id="openDiv" value="Open Popup Using Modal" />

<aui:button id="popupButton" value="Open Popup Using AUI Script " />

</br>
<div id="my-content-div">
	<div>
		This Content Is From DIV This DIV is Used To Render The AUI Modal Popup, 
		ideally you can hide it.
	</div>
</div>

<!-- AUI Script For Modal POPUP -->
<aui:script use="aui-modal,aui-overlay-manager">
A.one("#<portlet:namespace />openDiv").on('click',function(event){
	var dialog = new A.Modal({
		title: "AUI Modal Popup Title",
		bodyContent: "AUI Modal Popup Content Use it For Simple Popup Opertions",
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

<!-- AUI Script For IFrame POPUP -->
<aui:script position="inline" use="aui-base">
var popupButton = A.one('#<portlet:namespace />popupButton');
popupButton.on('click',
	function() {
		Liferay.Util.openWindow(
			{
				dialog: {
					//cssClass: 'aui-popup-example',
					destroyOnHide: true,
					height: 400,
					width: 400
				},
				dialogIframe: {
					//bodyCssClass: 'custom-css-class'
				},
				title: 'My Own Title(400x400 Window Size)',
				uri: '<%=secondJspURL.toString()%>'
			}
		);
	}
);
</aui:script>