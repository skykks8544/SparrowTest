<%@ page contentType="text/html; charset=utf-8"%>

<%
    String a=request.getParameter("test");
	String b=a;
	out.print(a);
%>

<script type="text/javascript">
    document.write(<%=request.getParameter("test")%>);
    document.write(<%=a%>);
	document.write(<%=b%>);
	document.location="http://<%=request.getServerName()%>:<%=request.getServerPort()%>/ntis";
	var iconStr = "<%=request.getRequestURL()%>";
</script>

<form name="MyForm" method="get" action="customer.do"> <!-- CROSS_SITE_REQUEST_FORGERY -->

<!-- Input form here ... -->

<input type=text name="txt1">
<input type=submit value="보내기">

<!-- Contents here ... -->

</form>
