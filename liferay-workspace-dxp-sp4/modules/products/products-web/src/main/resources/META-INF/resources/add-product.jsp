<%@ include file="init.jsp" %>
Add Product
<portlet:actionURL var="addProductURL" name="addProduct"/>
<aui:form action="${addProductURL}">
	<aui:input name="name" type="text" required="true"/>
	<aui:input name="description" type="text" required="true"/>
	<aui:button type="submit"/>
</aui:form>