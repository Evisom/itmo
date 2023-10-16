<%@ page import="model.ResultsList" %>
<%@ page import="model.Result" %>
<%@ page import="javax.servlet.ServletContext" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.servlet.ServletContext" %>


<%
    ResultsList results = (ResultsList) getServletContext().getAttribute("resultsList");
    ArrayList<Result> resultsList = results.getResults();
    for (int i = 0; i < resultsList.size(); i++) {
        Result result = resultsList.get(i);
        String[] resultArray = result.arrayRepresentation();
        %><tr><%
        for (int j = 0; j < resultArray.length; j++) {
            String s = resultArray[j];
        %><td><%=s%></td><%

        }%></tr><%
    }
%>