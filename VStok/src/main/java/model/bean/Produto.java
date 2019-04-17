/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author iagob
 */
public class Produto {
    private int id;
    private String produto;
    private int qtd;
    private double precoCusto;
    private double precoVenda;
    private int codBarra;
    private String tipo;
    private String validade;

    public Produto(){
    }
    
    public Produto(String produto,String tipo,String validade, int codBarra, int qtd, double precoCusto,double precoVenda){
        this.produto = produto;
        this.tipo = tipo;
        this.codBarra = codBarra;
        this.qtd = qtd;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.validade = validade;
    }
    
     public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getCodBarra() {
        return codBarra;
    }

    public void setCodBarras(int codBarras) {
        this.codBarra = codBarras;
    }    
    
}
