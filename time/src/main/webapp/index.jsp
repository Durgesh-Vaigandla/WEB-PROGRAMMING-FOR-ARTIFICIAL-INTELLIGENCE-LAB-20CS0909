<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<body>
    <h2>Server Current Time</h2>
    <%
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
    %>
    <p>Current time on server: <%= formattedDateTime %></p>
</body>
</html>
