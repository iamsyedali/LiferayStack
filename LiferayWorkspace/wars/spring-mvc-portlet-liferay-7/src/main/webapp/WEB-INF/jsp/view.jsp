<%@ include file="/WEB-INF/jsp/init.jsp" %>

<p>
	<b>Click On each Button and See the Console For Log To Understand Clear Flow</b>
</p>

<portlet:renderURL var="defaultRenderURL"/>

<portlet:actionURL var="defaultActionURL"/>

<portlet:renderURL var="myRenderURL">
	<portlet:param name="render" value="myRender"/>
</portlet:renderURL>

<portlet:actionURL var="myActionURL">
	<portlet:param name="action" value="myAction"/>
</portlet:actionURL>

<portlet:resourceURL id="myResource" var="myResourceURL"/>

<aui:button href="${defaultRenderURL}" value="Default Render"/>

<aui:button href="${defaultActionURL}" value="Default Action"/>

<aui:button href="${myRenderURL}" value="My Render"/>

<aui:button href="${myActionURL}" value="My Action"/>

<aui:button href="${myResourceURL}" value="My Resource"/>
