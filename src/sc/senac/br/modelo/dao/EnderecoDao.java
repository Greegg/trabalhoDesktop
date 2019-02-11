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
import sc.senac.br.modelo.Cliente;
import sc.senac.br.modelo.Endereco;
import sc.senac.factory.Dao;

/**
 *
 * @author hvb
 */
public class EnderecoDao extends Dao {

    public EnderecoDao() {
        super();
    }

    public boolean salvar(Endereco obj) {
        sql = "INSERT INTO endereço (logradouro,bairro,cidade,cep, complemento, cliente_id) VALUES(?,?,?,?,?,?)";

        try {
            stmt = conexao.prepareStatement(this.sql);

            stmt.setString(1, obj.getLogradouro());
            stmt.setString(2, obj.getBairro());
            stmt.setString(3, obj.getCidade());
            stmt.setString(4, obj.getCep());
            stmt.setString(5, obj.getComplemento());
            stmt.setInt(6, obj.getCliente().getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean atualizar(Endereco obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE endereço SET logradouro = ?, bairro=?, cidade=?, cep=?, complemento=? WHERE id=?");

            stmt.setString(1, obj.getLogradouro());
            stmt.setString(2, obj.getBairro());
            stmt.setString(3, obj.getCidade());
            stmt.setString(4, obj.getCep());
            stmt.setString(5, obj.getComplemento());
            stmt.setInt(6, obj.getId());

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

    public boolean excluir(Endereco obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE endereço SET status = 0 WHERE id= ?");
            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Endereco> listar(int id) {
        try {
            this.sql = "SELECT id,logradouro,bairro,cidade,cep,complemento FROM endereço WHERE status = 1 AND cliente_id = ? ORDER BY id ASC";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            res = stmt.executeQuery();

            List<Endereco> lista = new ArrayList<>();
            while (res.next()) {
                endereco = new Endereco();

                endereco.setId(res.getInt("id"));
                endereco.setLogradouro(res.getString("logradouro"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setCidade(res.getString("cidade"));
                endereco.setCep(res.getString("cep"));
                endereco.setComplemento(res.getString("complemento"));

                lista.add(endereco);
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Endereco> pesquisarNome(String nome) {
        try {
            stmt = conexao.prepareStatement("SELECT id,logradouro,bairro,cidade,cep, complemento FROM endereço WHERE logradouro LIKE?");

            stmt.setString(1, nome + "%");
            res = stmt.executeQuery();
            List<Endereco> lista = new ArrayList<>();
            while (res.next()) {
                endereco.setId(res.getInt("id"));
                endereco.setLogradouro(res.getString("logradouro"));
                endereco.setBairro(res.getString("bairro"));
                endereco.setCidade(res.getString("cidade"));
                endereco.setCep(res.getString("cep"));
                endereco.setComplemento(res.getString("complemento"));
                lista.add(endereco);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
