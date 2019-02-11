/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.modelo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sc.senac.br.modelo.Entregador;
import sc.senac.factory.Dao;

/**
 *
 * @author Alunos
 */
public class EntregadorDao extends Dao {

    public boolean salvar(Entregador obj) {
        sql = "INSERT INTO entregador (nome,cnh) VALUES(?,?)";

        try {
            stmt = conexao.prepareStatement(this.sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnh());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Entregador obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE entregador SET nome = ?, cnh=? WHERE id=?");

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnh());
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

    public boolean excluir(Entregador obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE entregador SET status = 0 WHERE id= ?");
            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Entregador> listar() {
        try {
            this.sql = "SELECT id,nome,cnh FROM entregador WHERE status = 1 ORDER BY id ASC";
            stmt = conexao.prepareStatement(sql);
            res = stmt.executeQuery();

            List<Entregador> lista = new ArrayList<>();
            while (res.next()) {
                entregador = new Entregador();

                entregador.setId(res.getInt("id"));
                entregador.setNome(res.getString("nome"));
                entregador.setCnh(res.getString("cnh"));

                lista.add(entregador);
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Entregador> pesquisarNome(String nome) {
        try {
            stmt = conexao.prepareStatement("SELECT id,nome,cnh FROM entegador WHERE nome LIKE?");

            stmt.setString(1, nome + "%");
            res = stmt.executeQuery();
            List<Entregador> lista = new ArrayList<>();
            while (res.next()) {
                entregador.setId(res.getInt("id"));
                entregador.setNome(res.getString("nome"));
                entregador.setCnh(res.getString("cnh"));

                lista.add(entregador);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
