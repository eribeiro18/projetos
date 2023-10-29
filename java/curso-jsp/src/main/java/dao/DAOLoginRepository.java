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
import model.ModelLogin;

/**
 *
 * @author evandro
 */
public class DAOLoginRepository {
    
    private Connection con;
    
    public DAOLoginRepository(){
        con = SingleConnectionBanco.getConnection();
    }
    
    public boolean validarAutenticacao(ModelLogin login) throws SQLException{
        boolean valida = false;
        String sql = " select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?) ";
        
        PreparedStatement pst = con.prepareCall(sql);
        pst.setString(1, login.getLogin());
        pst.setString(2, login.getSenha());
        
        ResultSet rs = pst.executeQuery();
        
        if(rs.next()){
            valida = true;
        }
        pst.close();
        rs.close();
        return valida;
    }
        
}
