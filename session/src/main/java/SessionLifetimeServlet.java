package com.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionLifetimeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Create or retrieve session
        HttpSession session = request.getSession(true);

        // Set session timeout (5 minutes)
        session.setMaxInactiveInterval(300);

        // Store session ID in a cookie
        Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
        sessionCookie.setMaxAge(300);
        response.addCookie(sessionCookie);

        // Output session details
        out.println("<html><head><title>Session Info</title></head><body>");
        out.println("<h2>Session Details</h2>");
        out.println("<p>Session Created: " + new Date(session.getCreationTime()) + "</p>");
        out.println("<p>Last Accessed: " + new Date(session.getLastAccessedTime()) + "</p>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<p><b>Note:</b> Session expires after 5 minutes of inactivity.</p>");
        out.println("</body></html>");
    }
}
