package servlets;

import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request, response);
        PrintWriter writer = response.getWriter();
  //      pageVariables.put("message", "");
        String value = request.getParameter("value");
        response.getWriter().println(new PageGenerator().getPage("page.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        if (value == null || value.isEmpty()) {
            writer.println(0);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            writer.println(Integer.parseInt(value) * 2);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("status",  response.getStatus());
        return pageVariables;
    }
}
