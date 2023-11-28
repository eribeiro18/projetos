/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ModelLogin;

/**
 *
 * @author evandro
 */
@WebServlet(name = "ServletUsuarioController", urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends HttpServlet {

    private DAOUsuarioRepository usuarioRepository = new DAOUsuarioRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String msg = "Operação de exclusão realizada com sucesso!";
        try {
            List<ModelLogin> modelLogins = this.usuarioRepository.consultaUsuarioList();
            request.setAttribute("modelLogins", modelLogins);
            if (acao != null && !acao.isBlank() && acao.equalsIgnoreCase("deletar")) {
                String idUser = request.getParameter("id");
                if (idUser != null && !idUser.equals("")) {
                    usuarioRepository.deletaUsuario(Long.parseLong(idUser));
                } else {
                    msg = "Usuário não encontrado para exclusão!";
                }
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("pages/usuario.jsp").forward(request, response);

            } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
                String idUser = request.getParameter("id");
                if (idUser != null && !idUser.equals("")) {
                    usuarioRepository.deletaUsuario(Long.parseLong(idUser));
                    response.getWriter().write("Excluido com sucesso!");
                } else {
                    msg = "Usuário não encontrado para exclusão!";
                }
            } else if(acao != null && !acao.isBlank() && acao.equalsIgnoreCase("buscarUserAjax")){
                String nomeBusca = request.getParameter("nomeBusca");
                List<ModelLogin> result = usuarioRepository.consultaUsuarioList(nomeBusca);
                ObjectMapper map = new ObjectMapper();
                response.getWriter().write(map.writeValueAsString(result));
            }else if(acao != null && !acao.isBlank() && acao.equalsIgnoreCase("buscarEditar")){
                String idUser = request.getParameter("id");
                ModelLogin modelLogin = this.usuarioRepository.consultaUsuarioPorId(idUser);
                request.setAttribute("msg", "Usuário em edição");
                request.setAttribute("modelLogin", modelLogin);
                request.getRequestDispatcher("pages/usuario.jsp").forward(request, response);
            } else if(acao != null && !acao.isBlank() && acao.equalsIgnoreCase("listarUser")){
                modelLogins = this.usuarioRepository.consultaUsuarioList();
                request.setAttribute("msg", "Usuários carregados");
                request.setAttribute("modelLogins", modelLogins);
                request.getRequestDispatcher("pages/usuario.jsp").forward(request, response);                
                
            } else {
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.validaRedirecionamento(request, response,
                    "msg", e.getMessage(), "erro.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String email = request.getParameter("email");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            ModelLogin modelLogin = new ModelLogin();
            modelLogin.setId(id != null && !id.equals("") ? Long.parseLong(id) : null);
            modelLogin.setEmail(email);
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);
            modelLogin.setNome(nome);
            modelLogin.setDescricao(descricao);
            //vaidar login já existe cadastrado
            ModelLogin valida = usuarioRepository.consultaUsuario(login);
            String msg = "Operação salvar realizada com sucesso!";
            if ((valida != null && valida.getId() == null) || (modelLogin.getId() != null && modelLogin.getId().equals(valida.getId()))) {
                if (modelLogin.getId() == null) {
                    usuarioRepository.inserirUsuario(modelLogin);
                } else {
                    usuarioRepository.atualizarUsuario(modelLogin);
                }
                modelLogin = usuarioRepository.consultaUsuario(login);
            } else {
                msg = "Login já usado em outro cadastro! Escolha outro e tente novamente.";
            }
            request.setAttribute("msg", msg);
            request.setAttribute("modelLogin", modelLogin);
            request.getRequestDispatcher("pages/usuario.jsp").forward(request, response);
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
}
