<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<b><liferay-ui:message key="Action Render Resource URLS Demo"/></b>

<portlet:actionURL var="doActionVariable" name="doActionMethod"/>

<portlet:renderURL var="renderJSPVariable">
	<portlet:param name="jspPage" value="/META-INF/resources/second.jsp"/>
</portlet:renderURL>

<portlet:resourceURL var="resourceVaraible" id="resourceId"/>

<aui:form action="${doActionVariable}">
	<aui:input name="title" id="title"/>
	<aui:button onClick="verifyLiferayStack()" value="verify"/><br>0<br>
	<div id="title-error-message" class="alert alert-danger" style="display: none;">
		Entered Title is not LiferayStack
	</div>
	<aui:button type="submit" value="Do Action"/>
</aui:form>
<br>
<aui:a href="${renderJSPVariable}">RenderJSP</aui:a>

<script type="text/javascript">
function verifyLiferayStack(){
	var resourceURL = "<%=resourceVaraible.toString()%>";
	var title = $("#<portlet:namespace/>title").val();
	resourceURL = resourceURL+"&<portlet:namespace/>title="+title;
	console.log("resourceURL : "+resourceURL);
   $.ajax({url: resourceURL, success: function(result){
       console.log("blah23 : "+result);
        if(result=="true"){
    	   $("#title-error-message").hide();
       }else{
    	   $("#title-error-message").show();
       }
   }});
}
</script>