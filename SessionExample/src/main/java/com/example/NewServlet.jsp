import java.io.IOException; 
import java.io.PrintWriter; 
import java.util.Date; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession; 
 
public class NewServlet extends HttpServlet { 
@Override 
protected void doGet(HttpServletRequest rq, HttpServletResponse rs) 
throws ServletException, IOException { 
rs.setContentType("text/html"); 
PrintWriter out=rs.getWriter(); 
HttpSession session=rq.getSession(true); 
Integer count=(Integer)session.getAttribute("NewServlet.HitCount"); 
if(count==null) 
{ 
count=Integer.valueOf(1); 
} 
else 
{ count=Integer.valueOf(count.intValue()+1); 
} 
 
 
session.setAttribute("NewServlet.HitCount",count); 
out.println("<html><head><title>Session example </title></head>"); 
out.println("<body><h4>Session server to "+"demonstrate session Tracking and its life cycle </h4>"); 
out.println("<br/> session status : "); 
if(session.isNew()) 
{ 
out.println("New session <br/>");} 
out.println("HitCount for your current session is : "+count); 
out.println("<br/> <h2>some basic session information</h2>"); 
out.println("session ID : "+session.getId()+"<br/>"); 
out.println("it is a new session: "+session.isNew()); 
out.println("<br/>session creation time : "+session.getCreationTime()); 
out.println("("+new Date(session.getCreationTime())+")<br/>"); 
out.println("Last acessed Time : "+session.getLastAccessedTime()); 
out.println("("+ new Date(session.getLastAccessedTime())+")<br/>"); 
out.println("Max Inactive Time : "+session.getMaxInactiveInterval()+"<br/>"); 
out.println("session info as in cookie : "+rq.isRequestedSessionIdFromCookie()+"<br/>"); 
out.println("</body></html>"); 
 
} 
} 