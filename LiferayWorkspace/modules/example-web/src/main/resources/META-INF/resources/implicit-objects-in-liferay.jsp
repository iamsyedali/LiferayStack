<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
	/* Implicit Objects From <portlet:defineObjects */
	actionRequest;
	actionResponse;
	eventRequest;
	eventResponse;
	liferayPortletRequest;
	liferayPortletResponse;
	portletConfig;
	portletName;
	portletPreferences;
	portletPreferencesValues;
	portletSession;
	portletSessionScope;
	renderRequest;
	renderResponse;
	resourceRequest;
	resourceResponse;
	searchContainerReference;

	/* Implicit Objects From <liferay-theme:defineObjects  */
	account;
	colorScheme;
	company;
	contact;
	layout;
	layouts;
	layoutTypePortlet;
	locale;
	permissionChecker;
	plid;
	portletDisplay;
	portletGroupId;
	realUser;
	scopeGroupId;
	theme;
	themeDisplay;
	timeZone;
	user;

	/* Implicit Objects From JSP */
	application;
	config;
	out;
	page;
	pageContext;
	request;
	response;
	session;
%>