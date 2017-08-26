<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<aui:form>

	<aui:input name="title" type="text"/>

	<aui:input name="description" type="textarea"/>
	
	<aui:select name="items">
		<aui:option value="item1">Item1</aui:option>
		<aui:option value="item2">Item2</aui:option>
	</aui:select>

	<aui:input name="timeZone" type="timeZone"/>

	<aui:input name="checkbox" type="checkbox"/>

	<aui:input name="radio" type="radio"/>
	
	<aui:input name="hidden" type="hidden"/>
	
	
	<aui:input name="file" type="file"/>
	
</aui:form>
