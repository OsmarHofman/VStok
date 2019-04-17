/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.bean.Usuario;


/**
 *
 * @author iagob
 */
public class UsuarioDAO {
    // realiza verificação
             public boolean checkLogin(String login, String senha){
             Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = null;
             ResultSet rs = null;
             boolean check = false;
             

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if (rs.next()){
                check = true;
                
                
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao checkar login"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
             return check;
         }
             
 // cadastra login e senha            
public boolean save(Usuario user){
        Connection con =ConnectionFactory.getConnection();
        String sql = "INSERT INTO usuario (login, senha,funcao) values(?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getSenha());
            stmt.setString(3, user.getFuncao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            return false;
        }finally{
           ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(Usuario user){
        Connection con =ConnectionFactory.getConnection();
        String sql = "DELETE FROM usuario WHERE login = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getLogin());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }finally{
           ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
