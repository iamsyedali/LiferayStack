<%@ include file="init.jsp" %>

<%
ResultRow resultRow = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Product product=(Product)resultRow.getObject();
String productId = String.valueOf(product.getProductId());
%>
<portlet:renderURL var="editProduct" >
	<portlet:param name="mvcPath" value="/edit-product.jsp"/>
	<portlet:param name="productId" value="<%=productId%>"/>
</portlet:renderURL>

<portlet:actionURL var="deleteProduct" name="deleteProductAction">
	<portlet:param name="productId" value="<%=productId%>"/>
</portlet:actionURL>

<liferay-ui:icon-menu>
        <liferay-ui:icon iconCssClass="icon-edit" message="edit" url="${editProduct}" />
        <liferay-ui:icon iconCssClass="icon-trash" message="delete" url="${deleteProduct}" />
</liferay-ui:icon-menu>