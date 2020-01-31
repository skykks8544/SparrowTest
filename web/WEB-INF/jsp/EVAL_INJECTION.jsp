<%@ page contentType="text/html; charset=utf-8"%>

<%
    String a=request.getParameter("eval_injection");
%>

<script>
    eval(<%=request.getParameter("eval_injection")%>);
    eval(<%=a%>);
</script>

<%=request.getParameter("eval_injection")%>
<%=a%>

