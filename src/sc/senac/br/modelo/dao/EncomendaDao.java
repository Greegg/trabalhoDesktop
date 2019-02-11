/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.modelo.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sc.senac.br.interfaces.DaoI;
import sc.senac.br.modelo.Encomenda;
import sc.senac.br.modelo.EncomendaProduto;
import sc.senac.factory.Dao;

/**
 *
 * @author hvb
 */
public class EncomendaDao extends Dao {

    public boolean salvar(Encomenda obj) {

        EncomendaProdutoDao encProdDao = new EncomendaProdutoDao();

        sql = "INSERT INTO encomenda(data_pedido, data_entrega, cliente_id, endereço_id, entregador_id) VALUES(?,?,?,?,?)";

        try {
            stmt = conexao.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setDate(1, obj.getDataPedido());
            stmt.setDate(2, obj.getDataEntrega());
            stmt.setInt(3, obj.getCliente().getId());
            stmt.setInt(4, obj.getEndereco().getId());
            stmt.setInt(5, obj.getEntregador().getId());

            boolean resExecucao = stmt.executeUpdate() > 0;

            if (resExecucao) {
                res = stmt.getGeneratedKeys();
                res.next();
                obj.setId(res.getInt(1));

                for (EncomendaProduto listEncProd : obj.getEncPRoduto()) {

                    listEncProd.setEncomenda(obj);
                    encProdDao.salvar(listEncProd);
                }
            } else {
                System.out.println("Erro cadastro.");
            }

            return resExecucao;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Encomenda obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE encomenda SET data_pedido = ?, data_entrega=? WHERE id=?");

            stmt.setDate(1, obj.getDataPedido());
            stmt.setDate(2, obj.getDataEntrega());
            stmt.setInt(3, obj.getId());

            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean excluir(Encomenda obj) {

        try {
            stmt = conexao.prepareStatement("UPDATE encomenda SET status = 0 WHERE id= ?");
            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Encomenda> listar() {

        try {
            this.sql = "select e.id, e.data_pedido, e.data_entrega, e.cliente_id, e.entregador_id, e.endereço_id, c.nome, en.nome, ende.logradouro  from encomenda e \n"
                    + "inner join cliente c ON e.cliente_id = c.id \n"
                    + "inner join entregador en on e.entregador_id = en.id\n"
                    + "inner join endereço ende on e.endereço_id = ende.id where e.status=1 ORDER BY e.id ASC";
            stmt = conexao.prepareStatement(sql);
            res = stmt.executeQuery();

            List<Encomenda> lista = new ArrayList<>();
            while (res.next()) {
                encomenda = new Encomenda();

                encomenda.setId(res.getInt("id"));
                encomenda.setDataPedido((res.getDate("data_pedido")));
                encomenda.setDataEntrega((res.getDate("data_entrega")));

                encomenda.getCliente().setId(res.getInt("e.cliente_id"));
                encomenda.getEntregador().setId(res.getInt("e.entregador_id"));
                encomenda.getEndereco().setId(res.getInt("e.endereço_id"));

                encomenda.getCliente().setNome(res.getString("c.nome"));
                encomenda.getEntregador().setNome("en.nome");
                encomenda.getEndereco().setLogradouro("ende.logradouro");

                lista.add(encomenda);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Encomenda> pesquisarNome(String nome) {
        try {
            stmt = conexao.prepareStatement("select e.id, e.data_pedido, e.data_entrega, e.cliente_id, e.entregador_id, e.endereço_id, c.nome, en.nome, ende.logradouro  from encomenda e\n"
                    + "inner join cliente c ON e.cliente_id = c.id\n"
                    + "inner join entregador en on e.entregador_id = en.id\n"
                    + "inner join endereço ende on e.endereço_id = ende.id where e.status=1 and c.nome LIKE? ORDER BY e.id ASC");

            stmt.setString(1, nome + "%");
            res = stmt.executeQuery();
            List<Encomenda> lista = new ArrayList<>();
            while (res.next()) {
                encomenda = new Encomenda();

                encomenda.setId(res.getInt("e.id"));
                encomenda.setDataPedido(res.getDate("e.data_pedido"));
                encomenda.setDataEntrega(res.getDate("e.data_entrega"));

                encomenda.getCliente().setId(res.getInt("e.cliente_id"));
                encomenda.getCliente().setNome(res.getString("c.nome"));

                encomenda.getEndereco().setId(res.getInt("e.endereço_id"));
                encomenda.getEndereco().setLogradouro(res.getString("ende.logradouro"));

                encomenda.getEntregador().setId(res.getInt("e.entregador_id"));
                encomenda.getEntregador().setNome(res.getString("en.nome"));

                lista.add(encomenda);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
}
