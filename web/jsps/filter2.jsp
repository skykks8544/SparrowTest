<%@page import="java.util.Enumeration"%>
<%@page import="sun.text.normalizer.ReplaceableString"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="org.springframework.web.util.UrlPathHelper" %>
<%@page import="javax.servlet.*,
                javax.servlet.http.HttpServletRequest,
                javax.servlet.http.HttpServletRequestWrapper" %>
<%@page import="java.util.Map,
                 java.util.HashMap,
                 java.util.Iterator" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML>
<html>
    <head>
    </head>
    <body>
<%

String name = request.getParameter("name");
Map resultMap = null;
String key = "";
String[] values = null;
Map map = request.getParameterMap();
Iterator iterator = map.keySet().iterator();


while (iterator.hasNext())  {
    key = (String)iterator.next();
    values = (String[])map.get(key);
}

if (values != null) {
    resultMap = new HashMap();

    out.println("* SIZE = " + values.length + "<br />");

    for (int i = 0; i < values.length; i++)
        resultMap.put(new String(key + "_" + i), values[i]);

    out.println("* RESULT_MAP = " + resultMap + "<br />");
}

String[] param = request.getParameterValues("param");
Enumeration enu= request.getParameterNames();


try {
out.println("<BR>request.getQueryString(): " + request.getQueryString());
out.println("<BR>request.getParameterValues(): " + param[0].toString());
out.println("<BR>request.getParameterNames(): " + enu.nextElement());
} catch(Exception e)
{
	out.println("error");
}


UrlPathHelper urlPathHelper = new UrlPathHelper();
out.println(urlPathHelper.getOriginatingQueryString(request));

%>




<p><%=name%></p>
<input id="name" name="userid" value="${name}">
<br />
상품코드 : ${param.code}<br />
<c:out value="${param.code}" /><br />
<c:out value="<b>${param.code}</b>" escapeXml="false"/><br />


    </body>
</html>
