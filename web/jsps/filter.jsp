<%@page import="org.omg.CORBA.Request"%>
<%@page import="org.springframework.web.util.UrlPathHelper" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
goSite
</head>
<body>

<%
    String url = request.getParameter("url");
    String queryString = request.getQueryString();



/*     out.println(request.getParameterNames());*/
    out.println("XSS_url_out.println: " + url);
    out.println(request.getQueryString());
/*
    UrlPathHelper urlPathHelper = new UrlPathHelper();
    out.println(urlPathHelper.getOriginatingQueryString(request)); */
%>



XSS_HTML : <%=url %>
XSS QueryString_HTML : <%=queryString %>




</body>




</html>
