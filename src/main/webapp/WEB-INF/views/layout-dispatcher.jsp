<%
    String view = request.getParameter("view");
	if (view.startsWith("admin")) {
	    pageContext.forward("admin-layout.jsp");
	} else if (view.startsWith("auth")) {
	    pageContext.forward("auth-layout.jsp");
	} else if ("error-page.jsp".equalsIgnoreCase(view)) {
	    pageContext.forward("error-page.jsp");
	} else {
	    pageContext.forward("home.jsp");
	}
%>