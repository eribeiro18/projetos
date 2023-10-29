/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import connection.SingleConnectionBanco;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author evandro
 */
/*Intercepta todas as requisicoes que vierem do projeto ou mapeamento*/
@WebFilter(filterName = "FilterAutenticacao", urlPatterns = {"/pages/*"})
public class FilterAutenticacao implements Filter {

    private static Connection connection;

    public FilterAutenticacao() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
    }

    /*Intercepta as requisições e as respostas no sistema*/
 /* Tudo que fizer no sistema passará por aqui, exemplo autenticação, transações com o banco de dados*/
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            HttpSession session = httpServletRequest.getSession();
            Object obj = session.getAttribute("usuario");
            String usuarioLogado = obj != null ? String.valueOf(obj) : null;
            String urlParaAutenticar = httpServletRequest.getServletPath();
            /*validar se esta logado, senão redireciona para login*/
            if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletLogin")) {
                RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
                request.setAttribute("msg", "Por favor realize o login!");
                redireciona.forward(request, response);
                return;
            } else {
                chain.doFilter(request, response);
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    /*Encerra os processos quando o servidor é parado*/
 /*Exemplo encerra os processos de conexões com o banco*/
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /*Inicia os processos ou recursosquando o servidor sobe o projeto*/
 /*Exemplo inicia os processos de conexões com o banco*/
    public void init(FilterConfig filterConfig) {
        connection = SingleConnectionBanco.getConnection();
    }
}
