<%@ include file="init.jsp" %>

Product Details

<portlet:actionURL var="editProductAction" name="editProductAction">
	<portlet:param name="productId" value="${product.productId}"/>
</portlet:actionURL>
<aui:form action="${editProductAction}" >
	<aui:input name="name" type="text" required="true" showRequiredLabel="false" value="${product.name}"/>
	<aui:input name="description" type="text"  required="true" showRequiredLabel="false" value="${product.description}"/>
	<aui:button type="submit" value="save"/>
</aui:form>