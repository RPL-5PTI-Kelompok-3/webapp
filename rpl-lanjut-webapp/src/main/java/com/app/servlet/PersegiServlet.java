package com.app.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hitung-persegi")
public class PersegiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double panjang = Double.parseDouble(request.getParameter("panjang"));
            double lebar = Double.parseDouble(request.getParameter("lebar"));

            // Perhitungan Luas dan Keliling
            double luas = panjang * lebar;
            double keliling = 2 * (panjang + lebar);

            // Logika Soal 2: Penentuan jenis bangun datar
            String jenisBangun;
            if (panjang == lebar) {
                jenisBangun = "BUJUR Sangkar";
            } else {
                jenisBangun = "persegi panjang";
            }

            // Mengirim data ke halaman JSP
            request.setAttribute("panjang", panjang);
            request.setAttribute("lebar", lebar);
            request.setAttribute("luas", luas);
            request.setAttribute("keliling", keliling);
            request.setAttribute("jenisBangun", jenisBangun);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Input harus berupa angka yang valid.");
        }

        request.getRequestDispatcher("persegi.jsp").forward(request, response);
    }
}
