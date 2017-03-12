<%@ include file="init.jsp" %>

<%-- <b><liferay-ui:message key="products-portlet.caption"/></b> --%>

<portlet:renderURL var="addProduct">
	<portlet:param name="mvcPath" value="/add-product.jsp"/>
</portlet:renderURL>

<aui:button href="${addProduct}" value="add-product"/>
