<%@ page contentType="text/html; charset=utf-8"%>

<%
    String a=request.getParameter("test");
%>

<script type="text/javascript">
    document.write(<%=request.getParameter("test")%>);
    document.write(<%=a%>);
</script>

<%=request.getParameter("test")%>
<%=a%>
