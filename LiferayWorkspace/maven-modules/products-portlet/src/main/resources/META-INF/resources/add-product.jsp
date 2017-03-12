<%@ include file="init.jsp" %>

Product Details

<portlet:actionURL var="addProductAction"></portlet:actionURL>
<aui:form action="${addProductAction}">
	<aui:input name="name" type="text" />
	<aui:input name="description" type="text" />
	<aui:button type="submit" value="save"/>
</aui:form>