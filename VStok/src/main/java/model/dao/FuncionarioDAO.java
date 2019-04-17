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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Funcionario;

/**
 *
 * @author iagob
 */
public class FuncionarioDAO {
      
    ConnectionFactory conex = new ConnectionFactory();
    private Connection con = null;

    public FuncionarioDAO() {
        con = ConnectionFactory.getConnection();
    }

    
    //Editar Funcionarios no BD
    public boolean editar(Funcionario funcionario){
        Connection con =ConnectionFactory.getConnection();
        String sql = "UPDATE funcionarios SET nome =?,dataInicio =?,usuario=?,ativo=? WHERE cpf = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataInicio());
            stmt.setString(3, funcionario.getUsuario());
            stmt.setBoolean(4, funcionario.isAtivo());
            stmt.setString(5, funcionario.getCpf());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "atualizado com sucesso");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao alterar"+ex);
            return false;
        }finally{
           ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //Deleta funcionario BD
    public boolean delete(Funcionario funcionario){
        Connection con =ConnectionFactory.getConnection();
        String sql = "DELETE FROM funcionarios WHERE cpf = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getCpf());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "excluido com sucesso com sucesso");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro ao excluir"+ex);
            return false;
        }finally{
           ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
         //Salvar No Banco de Dados
    public boolean save(Funcionario funcionario){
        Connection con =ConnectionFactory.getConnection();
        String sql = "INSERT INTO funcionarios (nome, cpf, usuario,dataInicio,funcao, ativo) values(?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getUsuario());
            stmt.setString(4, funcionario.getDataInicio());
            stmt.setString(5, funcionario.getFuncao());
            stmt.setBoolean(6, funcionario.isAtivo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Funcionario Cadastrado");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            JOptionPane.showMessageDialog(null, "erro ao  cadastrar funcionario");
            return false;
        }finally{
           ConnectionFactory.closeConnection(con, stmt);
        }
    }
         
         
    
    //Listar Funcionarios JTable
         public List <Funcionario> listar(){
             Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = null;
             ResultSet rs = null;
             
             List<Funcionario> funcionarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM funcionarios");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario func = new Funcionario();
                
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setAtivo(rs.getBoolean("ativo"));
                func.setDataInicio(rs.getString("dataInicio"));
                func.setUsuario(rs.getString("usuario"));
                func.setFuncao(rs.getString("funcao"));
                funcionarios.add(func);
                
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao Listar Produtos"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
             return funcionarios;
         }
         
         //pesquisa Funcionario BD
         public Funcionario pesquisarCpf(Funcionario func){
             Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = null;
             ResultSet rs = null;
             
        try {
            stmt = con.prepareStatement("SELECT * FROM funcionarios where cpf = ?");
            stmt.setString(1, func.getCpf());
            rs = stmt.executeQuery();
            
            rs.first();
                
                func.setNome(rs.getString("nome"));
                func.setCpf(rs.getString("cpf"));
                func.setAtivo(rs.getBoolean("ativo"));
                func.setDataInicio(rs.getString("dataInicio"));
                func.setUsuario(rs.getString("usuario"));
                
            
        } catch (SQLException ex) {
            System.err.println("Erro ao Listar Produtos"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
             return func;
         }
    
}
