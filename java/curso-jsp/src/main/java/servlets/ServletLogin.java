/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.ModelLogin;

/**
 *
 * @author evandro
 */
@WebServlet(urlPatterns = {"/pages/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {

    private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if(acao != null && acao.equalsIgnoreCase("logout")){
            request.getSession().invalidate();
                this.validaRedirecionamento(request, response,
                        "msg", "Deslogado", "/index.jsp");
        }else{
            this.validarPostGet(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.validarPostGet(request, response);
    }

    private void validarPostGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String url = request.getParameter("url");
            if (login != null && !login.isBlank()
                    && senha != null && !senha.isBlank()) {
                ModelLogin modelLogin = new ModelLogin();
                modelLogin.setLogin(login);
                modelLogin.setSenha(senha);
                if (daoLoginRepository.validarAutenticacao(modelLogin)) {
                    if (url == null || url.equals("null") || url.equals("")) {
                        url = "/pages/main.jsp";
                    }
                    request.getSession().setAttribute("usuario", modelLogin.getLogin());
                    this.validaRedirecionamento(request, response, "",
                            "", url);
                } else {
                    this.validaRedirecionamento(request, response,
                            "msg", "Informe o Login e Senha corretamente!", "/index.jsp");
                }
            } else {
                this.validaRedirecionamento(request, response,
                        "msg", "Informe o Login e Senha corretamente!", "/index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.validaRedirecionamento(request, response,
                    "msg", e.getMessage(), "erro.jsp");
        }
    }

    private void validaRedirecionamento(HttpServletRequest request, HttpServletResponse response, String nameAtr, String msgAtr, String url)
            throws ServletException, IOException {
        RequestDispatcher redirecionar = request.getRequestDispatcher(url);
        request.setAttribute(nameAtr, msgAtr);
        redirecionar.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
