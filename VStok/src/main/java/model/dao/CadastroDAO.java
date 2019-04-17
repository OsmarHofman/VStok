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
import model.bean.Produto;

/**
 *
 * @author iagob
 */
public class CadastroDAO {
    
    private Connection con = null;

    public CadastroDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    //Cadastra Produtos
    public boolean save(Produto produto){
        String sql = "INSERT INTO cadastro (produto, precoCusto, precoVenda,codBarras,quantidade,tipo,validade) values(?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, produto.getProduto());
            stmt.setDouble(2, produto.getPrecoCusto());
            stmt.setDouble(3, produto.getPrecoVenda());
            stmt.setInt(4, produto.getCodBarra());
            stmt.setInt(5, produto.getQtd());
            stmt.setString(6, produto.getTipo());
            stmt.setString(7, produto.getValidade());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto Cadastrado");
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: "+ex);
            JOptionPane.showMessageDialog(null, "erro ao  cadastrar produto");
            return false;
        }finally{
           ConnectionFactory.closeConnection(con, stmt);
        }
    }
             public List <Produto> listar(){
             Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = null;
             ResultSet rs = null;
             
             List<Produto> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM cadastro");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Produto prod = new Produto();
                
                prod.setId(rs.getInt("id"));
                prod.setCodBarras(rs.getInt("codBarras"));
                prod.setProduto(rs.getString("produto"));
                prod.setQtd(rs.getInt("quantidade"));
                prod.setPrecoCusto(rs.getDouble("precoCusto"));
                prod.setPrecoVenda(rs.getDouble("precoVenda"));
                prod.setTipo(rs.getString("tipo"));
                
                produtos.add(prod);
                
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao Listar Produtos"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
             return produtos;
         }
    
    
    
   /* public List <Produto> findAll(){
       
        String sql = "SELECT* FROM cadastro";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Produto produto = new Produto();
                produto.setProduto(rs.getString("produto"));
                produto.setCodBarras(rs.getInt("codBarras"));
                produto.setQtd(rs.getInt("quantidade"));
                produto.setPrecoCusto(rs.getDouble("precoCusto"));
                produto.setPrecoVenda(rs.getDouble("precoVenda"));
                produtos.add(produto);
            }
            
            
        } catch (SQLException ex) {
            
           System.err.println("Erro: "+ex);
           
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }*/
    
}
