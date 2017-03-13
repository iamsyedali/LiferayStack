<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="java.util.List"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="com.liferaystack.products.model.Product"%>
<%@ include file="init.jsp" %>

<liferay-ui:search-container delta="2" emptyResultsMessage="no-products-found" iteratorURL="<%=renderResponse.createRenderURL()%>">
    <liferay-ui:search-container-results  results="<%= ListUtil.subList((List<Product>)request.getAttribute("products"), searchContainer.getStart(), searchContainer.getEnd()) %>" />
    <liferay-ui:search-container-row className="com.liferaystack.products.model.Product" keyProperty="productId" modelVar="product">
        <liferay-ui:search-container-column-text name="Title" value="${product.name}" />
        <liferay-ui:search-container-column-text name="Author" value="${product.description}" />
        <liferay-ui:search-container-column-jsp  name="action" path="/products-action.jsp"/>
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator searchContainer="<%=searchContainer %>" paginate="true" />
</liferay-ui:search-container>

<portlet:renderURL var="addProduct">
	<portlet:param name="mvcPath" value="/add-product.jsp"/>
</portlet:renderURL>

<aui:button href="${addProduct}" value="add-product"/>Syed