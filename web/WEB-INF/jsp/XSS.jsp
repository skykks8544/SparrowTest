<%@ page contentType="text/html; charset=utf-8"%>

<%
    String a=request.getParameter("test");
%>

<div id="map" class="mapStyle">server port: <%=request.getServerPort()%></div>
<%=PageIndex.PageNumber(pageNo, tot_pg, action_url, imageRoot, add_tag)%> <!-- XSS -->
<%=request.getParameter("test")%> <!-- XSS -->
<%=a%>
<%
	String today = Util.getNowDateString("yyyy-MM-dd");
	String start = today;
%>
<input type="text", size=10, name="start", value='<%=start%>' readonly>
<%
	response.getWriter().write("abc");
%>
<%
	String s = request.getParameter("test");
	System.out.println("test: " + s);
%>
