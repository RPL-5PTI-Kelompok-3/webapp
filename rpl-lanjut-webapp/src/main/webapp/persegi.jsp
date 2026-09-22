<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Perhitungan Persegi Panjang</title>
</head>
<body>
    <h2>Kalkulator Bangun Datar</h2>
    <form action="hitung-persegi" method="post">
        <label for="panjang">Panjang:</label>
        <input type="number" step="any" id="panjang" name="panjang" required><br><br>
        <label for="lebar">Lebar:</label>
        <input type="number" step="any" id="lebar" name="lebar" required><br><br>
        <button type="submit">Hitung</button>
    </form>

    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>

    <% if (request.getAttribute("luas") != null) { %>
        <hr>
        <h3>Hasil Perhitungan:</h3>
        <p>Panjang: <%= request.getAttribute("panjang") %></p>
        <p>Lebar: <%= request.getAttribute("lebar") %></p>
        <p>Jenis Bangun: <strong><%= request.getAttribute("jenisBangun") %></strong></p>
        <p>Luas: <%= request.getAttribute("luas") %></p>
        <p>Keliling: <%= request.getAttribute("keliling") %></p>
    <% } %>

    <p><a href="index.jsp">&laquo; Kembali ke menu</a></p>
</body>
</html>
