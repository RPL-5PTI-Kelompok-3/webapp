<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Penyelesai Persamaan Kuadrat</title>
</head>
<body>
    <h2>Kalkulator Persamaan Kuadrat (ax&sup2; + bx + c = 0)</h2>
    <form action="hitung-kuadrat" method="post">
        <label for="a">Nilai a:</label>
        <input type="number" step="any" id="a" name="a" required><br><br>
        <label for="b">Nilai b:</label>
        <input type="number" step="any" id="b" name="b" required><br><br>
        <label for="c">Nilai c:</label>
        <input type="number" step="any" id="c" name="c" required><br><br>
        <button type="submit">Cari Akar</button>
    </form>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <% if (request.getAttribute("diskriminan") != null) { %>
        <hr>
        <h3>Hasil:</h3>
        <p>Persamaan: <%= request.getAttribute("a") %>x&sup2; + (<%= request.getAttribute("b") %>)x + (<%= request.getAttribute("c") %>) = 0</p>
        <p>Diskriminan (D): <%= request.getAttribute("diskriminan") %></p>
        <p>Kondisi: <strong><%= request.getAttribute("jenisAkar") %></strong></p>
        <p>Akar x<sub>1</sub>: <strong><%= request.getAttribute("x1") %></strong></p>
        <p>Akar x<sub>2</sub>: <strong><%= request.getAttribute("x2") %></strong></p>
    <% } %>

    <p><a href="index.jsp">&laquo; Kembali ke menu</a></p>
</body>
</html>
