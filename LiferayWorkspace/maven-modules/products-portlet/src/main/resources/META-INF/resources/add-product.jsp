<%@ include file="init.jsp" %>

Product Details

<portlet:actionURL var="addProductAction" name="addProductAction"></portlet:actionURL>
<aui:form action="${addProductAction}" >
	<aui:input name="name" type="text"  required="true" showRequiredLabel="false" />
	<aui:input name="description" type="text" required="true" showRequiredLabel="false" />
	<aui:button type="submit" value="save"/>
</aui:form>