<%@page import="products.model.Product"%>
<%@page import="products.service.ProductLocalServiceUtil"%>
<%@ include file="init.jsp" %>

<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>

<portlet:renderURL var="addProductURL">
	<portlet:param name="mvcPath" value="/add-product.jsp"/>
</portlet:renderURL>

<aui:button href="${addProductURL}" value="Add Product" />

<%
List<Product> products = ProductLocalServiceUtil.getProducts(-1,-1);
System.out.println("products : size "+products.size());
%>

<liferay-ui:search-container total="<%=products.size()%>" var="searchContainer" delta="10" deltaConfigurable="true" 
  emptyResultsMessage="Oops. There Are No Products To Display, Please add Products">
  
	<liferay-ui:search-container-results results="<%=ListUtil.subList(products, searchContainer.getStart(),searchContainer.getEnd())%>" />
		<liferay-ui:search-container-row className="products.model.Product" modelVar="product" keyProperty="productId" >
			<liferay-ui:search-container-column-text name="name" property="name"/>
		</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />

</liferay-ui:search-container>