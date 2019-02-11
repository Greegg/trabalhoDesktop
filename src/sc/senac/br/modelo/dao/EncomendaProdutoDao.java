/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sc.senac.br.interfaces.DaoI;
import sc.senac.br.modelo.EncomendaProduto;
import sc.senac.factory.Dao;

/**
 *
 * @author hvb
 */
public class EncomendaProdutoDao extends Dao {

    public boolean salvar(EncomendaProduto obj) {
        sql = "INSERT INTO encomenda_produtos(valor,quantidade, encomenda_id, produto_id) VALUES(?,?,?,?)";

        try {
            stmt = conexao.prepareStatement(this.sql);

            stmt.setFloat(1, obj.getValor());
            stmt.setInt(2, obj.getQuantidade());
            stmt.setInt(3, obj.getEncomenda().getId());
            stmt.setInt(4, obj.getProduto().getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.err.println(sql);
            return false;
        }
    }

    public boolean atualizar(EncomendaProduto obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE encomenda_produtos SET valor =?, quantidade=?, encomenda_id=?, produto_id=? WHERE id = ?");

            stmt.setFloat(1, obj.getValor());
            stmt.setInt(2, obj.getQuantidade());
            stmt.setInt(3, obj.getEncomenda().getId());
            stmt.setInt(4, obj.getProduto().getId());

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

    public boolean excluir(EncomendaProduto obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE encomenda_produtos SET status = 0 WHERE id= ?");
            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<EncomendaProduto> lerPorId(int id) {
        try {
            stmt = conexao.prepareStatement("SELECT \n"
                    + "    p.id,\n"
                    + "    p.nome AS produto,\n"
                    + "    e.valor,\n"
                    + "    e.quantidade,\n"
                    + "    (e.valor * e.quantidade) AS subtotal,\n"
                    + "    e.encomenda_id AS encomenda\n"
                    + "FROM\n"
                    + "    produto p\n"
                    + "        INNER JOIN\n"
                    + "    encomenda_produtos e ON p.id = e.produto_id\n"
                    + "WHERE\n"
                    + "    e.encomenda_id = ?\n"
                    + "AND e.status = 1 ORDER BY p.id ASC;");

            stmt.setInt(1, id);
            res = stmt.executeQuery();
            encProduto = new EncomendaProduto();
            List<EncomendaProduto> lista = new ArrayList<>();
            while (res.next()) {
                encomendaProduto = new EncomendaProduto();

                encomendaProduto.setId(res.getInt("encomenda"));

                encomendaProduto.getProduto().setId(res.getInt("id"));
                encomendaProduto.getProduto().setNome(res.getString("produto"));
                encomendaProduto.getProduto().setValor(res.getFloat("valor"));
                encomendaProduto.setQuantidade(res.getInt("e.quantidade"));
                encomendaProduto.setSubTotal(res.getFloat("subtotal"));

                lista.add(encomendaProduto);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
