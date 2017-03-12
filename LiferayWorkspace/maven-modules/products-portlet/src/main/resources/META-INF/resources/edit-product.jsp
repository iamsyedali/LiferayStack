<%@ include file="init.jsp" %>

Product Details

<portlet:actionURL var="editProductAction" name="editProductAction"></portlet:actionURL>
<aui:form action="${editProductAction}" >
	<aui:input name="name" type="text" />
	<aui:input name="description" type="text" />
	<aui:button type="submit" value="save"/>
</aui:form>