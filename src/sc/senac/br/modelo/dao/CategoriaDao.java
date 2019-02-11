/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sc.senac.br.modelo.Categoria;
import sc.senac.factory.Dao;

/**
 *
 * @author hvb
 */
public class CategoriaDao extends Dao {

    private PreparedStatement stmt;
    private Categoria categoria;

    public CategoriaDao() {
        super();
    }

    public boolean salvar(Categoria obj) {

        try {
            stmt = conexao.prepareStatement("INSERT INTO categoria(nome) VALUES(?)");
            stmt.setString(1, obj.getNome());
            int res = stmt.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public boolean atualizar(Categoria obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE categoria SET nome =? WHERE id = ?");
            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getId());
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean excluir(Categoria obj) {
        try {
            stmt = conexao.prepareStatement("DELETE FROM categoria WHERE id = ?");
            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Categoria> listar() {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome FROM categoria ORDER BY id ASC");
            ResultSet result = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();
            while (result.next()) {
                Categoria c = new Categoria();
                c.setId(result.getInt("id"));
                c.setNome(result.getString("nome"));
                lista.add(c);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Categoria lerPorId(int id) {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome FROM categoria WHERE id=?");
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            Categoria c = new Categoria();
            if (res.next()) {
                c.setId(res.getInt("id"));
                c.setNome(res.getString("nome"));
            }
            return c;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Categoria> pesquisarPorNome(String nome) {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome FROM categoria WHERE nome LIKE ?");
            stmt.setString(1, nome + "%");
            ResultSet res = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();
            while (res.next()) {
                Categoria c = new Categoria();
                c.setId(res.getInt("id"));
                c.setNome(res.getString("nome"));
                lista.add(c);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List listarTodos() throws SQLException {
        List<Categoria> listCategorias = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM categoria");
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                categoria = new Categoria();
                categoria.setId(res.getInt("id"));
                categoria.setNome(res.getString("nome"));
                listCategorias.add(categoria);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar todos " + e.getMessage());
        } finally {

        }
        return listCategorias;
    }
}
