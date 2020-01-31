<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="org.springframework.web.util.UrlPathHelper" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

URL : ${param.url}
<%
    if (request.getParameter("url") != null)
        response.sendRedirect(request.getParameter("url"));

    UrlPathHelper urlPathHelper = new UrlPathHelper();
    out.println(urlPathHelper.getOriginatingQueryString(request));
   
%>

</body>
</html>
