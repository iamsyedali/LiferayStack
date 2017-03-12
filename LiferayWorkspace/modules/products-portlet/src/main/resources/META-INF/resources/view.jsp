<%@ include file="init.jsp" %>

<%-- <b><liferay-ui:message key="products-portlet.caption"/></b> --%>

<portlet:renderURL var="addProduct"></portlet:renderURL>

<br>
<aui:button href="${addProduct}" value="add-product"/>
