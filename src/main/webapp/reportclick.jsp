<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page isELIgnored="false"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Testing JSP</h1>
    <p>
        <%= "Привет"%>
    </p>

    <p>
        <%= new java.util.Date() %>
    </p>

    <p>Категория: ${requestScope.Category}</p>
    <p>Сортировка: ${requestScope.Sorting}</p>

    <p>
        <%
         if (true) {
             for (int i = 0; i < 10; i++) {
                out.println("<p>" + "Hi, " + i + "</p>");
             }
         } else {
         out.println("<p>" + "Hi, mum" + "</p>");
         }
         %>
    </p>
    <table>
        <tr>
            <th>Hi, I'm your first cell.</th>
            <th>I'm your second cell.</th>
            <th>I'm your third cell.</th>
            <th>I'm your fourth cell.</th>
        </tr>
        <tr>
            <td>Hi, I'm your first cell.</td>
            <td>I'm your second cell.</td>
            <td>I'm your third cell.</td>
            <td>I'm your fourth cell.</td>
        </tr>
    </table>

</body>
</html>
