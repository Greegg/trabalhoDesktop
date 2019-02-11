/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sc.senac.br.modelo.Categoria;
import sc.senac.br.modelo.Cliente;
import sc.senac.br.modelo.Encomenda;
import sc.senac.br.modelo.EncomendaProduto;
import sc.senac.br.modelo.Endereco;
import sc.senac.br.modelo.Entregador;
import sc.senac.br.modelo.Produto;

/**
 *
 * @author hvb
 */
public class Dao {

    protected Connection conexao;
    protected PreparedStatement stmt;

    //modelo
    protected Categoria categoria;
    protected Cliente cliente;
    protected Encomenda encomenda;
    protected EncomendaProduto encProduto;
    protected Endereco endereco;
    protected Entregador entregador;
    protected Produto produto;
    protected EncomendaProduto encomendaProduto;
    //fim modelo
    
    protected String sql = "";
    protected ResultSet res;
    
    protected boolean debug = true;
    protected static final String DATABASE_NAME = "marmitao";

    public Dao() {
        conexao = Conexao.getConexao();
        openOrCreateDatabase();
    }

    private void openOrCreateDatabase() {
        try {
            stmt = conexao.prepareStatement("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
            stmt.execute();

            stmt = conexao.prepareStatement("USE " + DATABASE_NAME);
            stmt.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
