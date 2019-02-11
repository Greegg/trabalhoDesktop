/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sc.senac.br.modelo.Categoria;
import sc.senac.br.modelo.Produto;
import sc.senac.br.uteis.Conversor;
import sc.senac.factory.Dao;

/**
 *
 * @author hvb
 */
public class ProdutoDao extends Dao {

    public boolean salvar(Produto obj) {
        sql = "INSERT INTO produto(nome, valor, descricao, categoria_id) VALUES(?,?,?,?)";

        try {
            stmt = conexao.prepareStatement(this.sql);

            stmt.setString(1, obj.getNome());
            stmt.setFloat(2, obj.getValor());
            stmt.setString(3, obj.getDescricao());
            stmt.setInt(4, obj.getCategoria().getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Produto obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE produto SET nome =?, valor=?, descricao=?, categoria_id=? WHERE id = ?");

            stmt.setString(1, obj.getNome());
            stmt.setFloat(2, obj.getValor());
            stmt.setString(3, obj.getDescricao());
            stmt.setInt(4, obj.getCategoria().getId());
            stmt.setInt(5, obj.getId());

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

    public boolean excluir(Produto obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE produto SET status = 0 WHERE id= ?");
            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Produto> listar() {
        try {
            this.sql = "SELECT p.id, p.nome as produto, p.valor, p.descricao, p.categoria_id, c.nome as categoria"
                    + " FROM "
                    + " produto p INNER JOIN categoria c ON p.categoria_id=c.id ORDER BY p.id ASC";
            stmt = conexao.prepareStatement(sql);
            res = stmt.executeQuery();
            List<Produto> lista = new ArrayList<>();
            while (res.next()) {
                produto = new Produto();

                produto.setId(res.getInt("id"));
                produto.setNome(res.getString("produto"));
                produto.setValor(res.getFloat("valor"));
                produto.setDescricao(res.getString("descricao"));

                produto.getCategoria().setId(res.getInt("categoria_id"));
                produto.getCategoria().setNome(res.getString("categoria"));

                lista.add(produto);
            }
            return lista;

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public Produto lerPorId(int id) {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome,descricao, valor FROM produto WHERE id=?");
            stmt.setInt(1, id);
            res = stmt.executeQuery();
            Produto produto = new Produto();

            if (res.next()) {
                produto.setId(res.getInt("id"));
                produto.setNome(res.getString("nome"));
                produto.setDescricao(res.getString("descricao"));
                produto.setValor(res.getFloat("Valor"));
            }
            return produto;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Produto> pesquisarNome(String nome) {
        try {
            CategoriaDao cat = new CategoriaDao();
            stmt = conexao.prepareStatement("SELECT id, nome, valor,categoria_id FROM produto WHERE nome LIKE ?");
            stmt.setString(1, nome + "%");
            res = stmt.executeQuery();
            List<Produto> lista = new ArrayList<>();
            while (res.next()) {
                Produto produto = new Produto();
                produto.setId(res.getInt("id"));
                produto.setNome(res.getString("nome"));
                produto.setValor(res.getFloat("valor"));
                produto.getCategoria().setNome(res.getString("categoria_id"));

                Categoria cate = cat.lerPorId(res.getInt("categoria_id"));
                produto.setCategoria(cate);
                lista.add(produto);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
