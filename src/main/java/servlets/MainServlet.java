package servlets;

import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message", "");
        String value = request.getParameter("value");
        response.getWriter().println(new PageGenerator().getPage("page.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        if (value == null || value.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        return pageVariables;
    }
}
