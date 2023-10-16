package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import javax.servlet.ServletContext;
import model.Result;
import model.ResultsList;

import model.Result;
@WebServlet("/AreaCheck")
public class AreaCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long timestamp = System.nanoTime();
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");
        ServletContext context = getServletContext();
        ResultsList resultsList = (ResultsList) context.getAttribute("resultsList");
        if (resultsList == null) {
            resultsList = new ResultsList();
        }
        Result result = new Result(x, y, r, timestamp);
        resultsList.addResult(result);

        context.setAttribute("resultsList", resultsList);
//        request.setAttribute("resultsList", resultsList);
        request.getRequestDispatcher("/jsp/table.jsp").forward(request, response);
    }
}
