<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="RestClientMvcPortlet.caption"/></b>
	<pre>
	    <%=request.getAttribute("response")%>
	</pre>
</p>