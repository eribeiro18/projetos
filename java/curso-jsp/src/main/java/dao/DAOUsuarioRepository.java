/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.SingleConnectionBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ModelLogin;

/**
 *
 * @author evandro
 */
public class DAOUsuarioRepository {

    private Connection con;

    public DAOUsuarioRepository() {
        con = SingleConnectionBanco.getConnection();
    }

    public boolean inserirUsuario(ModelLogin modelLogin) throws SQLException {
        try {
            boolean valida = false;
            String sql = """
                     INSERT INTO public.model_login(
                     	login, senha, nome, email, descricao)
                     	VALUES (?, ?, ?, ?, ?);
                     """;
            PreparedStatement pst = con.prepareCall(sql);
            pst.setString(1, modelLogin.getLogin());
            pst.setString(2, modelLogin.getSenha());
            pst.setString(3, modelLogin.getNome());
            pst.setString(4, modelLogin.getEmail());
            pst.setString(5, modelLogin.getDescricao());

            pst.executeUpdate();
            con.commit();
            valida = true;
            pst.close();
            return valida;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public boolean atualizarUsuario(ModelLogin modelLogin) throws SQLException {
        try {
            boolean valida = false;
            String sql = """
                       UPDATE public.model_login
                     	SET login=?, senha=?, nome=?, email=?, descricao=?
                     	WHERE id=?
                     """;
            PreparedStatement pst = con.prepareCall(sql);
            pst.setString(1, modelLogin.getLogin());
            pst.setString(2, modelLogin.getSenha());
            pst.setString(3, modelLogin.getNome());
            pst.setString(4, modelLogin.getEmail());
            pst.setString(5, modelLogin.getDescricao());
            pst.setLong(6, modelLogin.getId());
            pst.executeUpdate();
            valida = true;
            con.commit();
            pst.close();
            return valida;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public ModelLogin consultaUsuario(String login) throws SQLException {
        ModelLogin model = new ModelLogin();
        String sql = " select login, senha, id, nome, email, descricao from model_login where upper(login) = upper(?)";

        PreparedStatement pst = con.prepareCall(sql);
        pst.setString(1, login);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            model.setLogin(rs.getString("login"));
            model.setSenha(rs.getString("senha"));
            model.setId(rs.getLong("id"));
            model.setNome(rs.getString("nome"));
            model.setEmail(rs.getString("email"));
            model.setDescricao(rs.getString("descricao"));
        }
        pst.close();
        rs.close();
        return model;
    }
    
    public ModelLogin consultaUsuarioPorId(String id) throws SQLException {
        ModelLogin model = new ModelLogin();
        String sql = " select login, senha, id, nome, email, descricao from model_login where id = ?";

        PreparedStatement pst = con.prepareCall(sql);
        pst.setLong(1, Long.parseLong(id));

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            model.setLogin(rs.getString("login"));
            model.setSenha(rs.getString("senha"));
            model.setId(rs.getLong("id"));
            model.setNome(rs.getString("nome"));
            model.setEmail(rs.getString("email"));
            model.setDescricao(rs.getString("descricao"));
        }
        pst.close();
        rs.close();
        return model;
    }
    
    public List<ModelLogin> consultaUsuarioList(String nome) throws SQLException {
        List<ModelLogin> modelList = new ArrayList<>();
        String sql = " select login, senha, id, nome, email, descricao from model_login where upper(nome) like ? ";

        PreparedStatement pst = con.prepareCall(sql);
        pst.setString(1, "%" + nome.toUpperCase() + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            ModelLogin model = new ModelLogin();
            model.setLogin(rs.getString("login"));
            model.setSenha(rs.getString("senha"));
            model.setId(rs.getLong("id"));
            model.setNome(rs.getString("nome"));
            model.setEmail(rs.getString("email"));
            model.setDescricao(rs.getString("descricao"));
            modelList.add(model);
        }
        pst.close();
        rs.close();
        return modelList;
    }

    public boolean deletaUsuario(Long id) throws SQLException{
        boolean valida = false;
        String sql = " DELETE FROM public.model_login wHERE id=?";

        PreparedStatement pst = con.prepareCall(sql);
        pst.setLong(1, id);

        pst.executeUpdate();
        con.commit();
        valida = true;
        pst.close();
        return valida;
    }

}
