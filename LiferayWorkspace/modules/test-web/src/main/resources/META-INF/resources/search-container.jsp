<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%
	List<User> users = UserLocalServiceUtil.getUsers(-1,-1);
	System.out.println(users.size());
%>

<liferay-ui:search-container total="<%=users.size()%>" var="searchContainer" delta="1" deltaConfigurable="true" 
		emptyResultsMessage="Oops. There Are No Users To Display, Please add Users">
		
	<liferay-ui:search-container-results results="<%=ListUtil.subList(users, searchContainer.getStart(),searchContainer.getEnd())%>" />
		<liferay-ui:search-container-row className="com.liferay.portal.kernel.model.User" modelVar="user" keyProperty="userId" >
			<liferay-ui:search-container-column-text name="User Id" value="${user.userId}"/>
			<liferay-ui:search-container-column-text name="firstName" property="firstName"/>
			<liferay-ui:search-container-column-text name="lastName" property="lastName"/>
			<liferay-ui:search-container-column-text name="Email" value="${user.emailAddress}"/>
		</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />

</liferay-ui:search-container>