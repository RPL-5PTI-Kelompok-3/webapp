package com.app.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hitung-kuadrat")
public class PersamaanKuadratServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double a = Double.parseDouble(request.getParameter("a"));
            double b = Double.parseDouble(request.getParameter("b"));
            double c = Double.parseDouble(request.getParameter("c"));

            if (a == 0) {
                request.setAttribute("error", "Nilai koefisien 'a' tidak boleh bernilai 0 pada persamaan kuadrat.");
            } else {
                double d = (b * b) - (4 * a * c);
                String jenisAkar;
                String hasilX1;
                String hasilX2;

                if (d > 0) {
                    jenisAkar = "Dua akar riil berbeda (D > 0)";
                    double x1 = (-b + Math.sqrt(d)) / (2 * a);
                    double x2 = (-b - Math.sqrt(d)) / (2 * a);
                    hasilX1 = String.format("%.2f", x1);
                    hasilX2 = String.format("%.2f", x2);
                } else if (d == 0) {
                    jenisAkar = "Satu akar riil kembar (D = 0)";
                    double x = -b / (2 * a);
                    hasilX1 = String.format("%.2f", x);
                    hasilX2 = String.format("%.2f", x);
                } else {
                    jenisAkar = "Dua akar imajiner / kompleks (D < 0)";
                    double bagianRiil = -b / (2 * a);
                    double bagianImajiner = Math.sqrt(-d) / (2 * a);
                    hasilX1 = String.format("%.2f + %.2fi", bagianRiil, Math.abs(bagianImajiner));
                    hasilX2 = String.format("%.2f - %.2fi", bagianRiil, Math.abs(bagianImajiner));
                }

                request.setAttribute("a", a);
                request.setAttribute("b", b);
                request.setAttribute("c", c);
                request.setAttribute("diskriminan", d);
                request.setAttribute("jenisAkar", jenisAkar);
                request.setAttribute("x1", hasilX1);
                request.setAttribute("x2", hasilX2);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Input koefisien a, b, dan c harus berupa angka.");
        }

        request.getRequestDispatcher("kuadrat.jsp").forward(request, response);
    }
}
