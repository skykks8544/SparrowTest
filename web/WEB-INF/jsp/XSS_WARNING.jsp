<%!
	/**
	 * Filter function
	 * @param s the target string
	 * @return the filtered string
	 */
	private String filter(String s) {
		if(s != null) {
			s = s.replaceAll("<", "&lt;");
			s = s.replaceAll(">", "&gt;");
		}
		return s;
	}
%>

<%
	boolean condition = request.getParameter("condition") != null;
	String thresholdString = request.getParameter("threshold");
	int threshold = -1;
	try {
		threshold = Integer.parseInt(thresholdString);
	} catch(NumberFormatException nfe) {
		threshold = -1;
	} catch(Exception e) {
		threshold = -1;
	}
	String initParam = "initialized";
	String initWarnParam = "initialized";
	String initFilteredParam = "initialized";
	
	initParam = request.getParameter("initParam");
	initWarnParam = request.getParameter("initWarnParam");
	initFilteredParam = request.getParameter("initFilteredParam");
	
	String param = request.getParameter("param");	
	String warnParam = request.getParameter("warnParam");
	String filteredParam = request.getParameter("filteredParam");
	
	if(condition) {
		warnParam = "";
	}
	
	if(condition) {
		initWarnParam = "";
	}
	
	for(int i=0; i<threshold; i++) {
		warnParam = "";
	}	
	
	initFilteredParam = "";
	filteredParam = "";		
	
	String passedParam = param;
	String passedWarnParam = warnParam;
	String passedFilteredParam = filteredParam;
%>

<body>
<%=param%> <!-- violation: XSS -->
<%=warnParam%> <!-- violation: XSS.WARNING -->
<%=filteredParam%> <!-- No warning -->

<%=initParam%> <!-- violation: XSS -->
<%=initWarnParam%> <!-- violation: XSS.WARNING -->
<%=initFilteredParam%> <!-- No warning -->

<%=passedParam%> <!-- violation: XSS -->
<%=passedWarnParam%> <!-- violation: XSS.WARNING -->
<%=passedFilteredParam%> <!-- No warning -->

<%
	initFieldParam = request.getParameter("initFieldParam");
	initFieldWarnParam = request.getParameter("initFieldWarnParam");
	initFieldFilteredParam = request.getParameter("initFieldFilteredParam");
	
	if(condition) {
		initFieldWarnParam = "";
	}	
	
	initFieldFilteredParam = "";
%>

<%=initFieldParam%> <!-- violation: XSS -->
<%=initFieldWarnParam%> <!-- violation: XSS.WARNING -->
<%=initFieldFilteredParam%> <!-- No warning -->

<%!
	// Field declarations		
	String initFieldParam = "initialized";
	String initFieldWarnParam = "initialized";
	String initFieldFilteredParam = "initialized";
%>

<%
// 외부로부터 이름을 입력 받음
String name = request.getParameter("name");
if(condition) {
	name = filter(name); // 조건부로 필터링을 수행
}
%>
<p>NAME:<%=name%></p> <!-- violation: XSS.WARNING -->

<%
// 외부로부터 이름을 입력 받음
String safeName = request.getParameter("name");
safeName = filter(safeName); // 반드시 필터링을 수행하도록 함 
%>
<p>NAME:<%=safeName%></p> <!-- No warning -->

</body>