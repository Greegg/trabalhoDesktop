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
import sc.senac.br.modelo.Cliente;
import sc.senac.factory.Dao;

/**
 *
 * @author hvb
 */
public class ClienteDao extends Dao implements DaoI<Cliente> {

    public ClienteDao() {
        super();
    }

    @Override
    public boolean salvar(Cliente obj) {
        EnderecoDao enderecoDao = new EnderecoDao();
        sql = "INSERT INTO cliente (nome,data_nascimento,email,Telefone) VALUES(?,?,?,?)";

        try {
            stmt = conexao.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNome());
            stmt.setDate(2, obj.getDataNascimento());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());

            boolean resExecucao = stmt.executeUpdate() > 0;

            res = stmt.getGeneratedKeys();
            res.next();
            obj.setId(res.getInt(1));
            

            obj.getEnderecos().get(0).setCliente(obj);
            endereco = obj.getEnderecos().get(0);

//            endereco.setCliente(cliente);
            return enderecoDao.salvar(endereco) && resExecucao;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean atualizar(Cliente obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE cliente SET nome = ?, data_nascimento=?, email=?, telefone=? WHERE id=?");

            stmt.setString(1, obj.getNome());
            stmt.setDate(2, obj.getDataNascimento());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setInt(5, obj.getId());

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

    @Override
    public boolean excluir(Cliente obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE cliente SET status = 0 WHERE id= ?");
            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cliente> listar() {
        try {
            this.sql = "SELECT id, nome, data_nascimento, email, telefone FROM cliente WHERE status = 1 ORDER BY id ASC";
            stmt = conexao.prepareStatement(sql);
            res = stmt.executeQuery();

            List<Cliente> lista = new ArrayList<>();
            while (res.next()) {
                cliente = new Cliente();

                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setDataNascimento(res.getDate("data_nascimento"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));

                lista.add(cliente);
            }
            return lista;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Cliente lerPorId(int id) {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome, data_nascimento, email, telefone FROM cliente");
            stmt.setInt(1, id);
            res = stmt.executeQuery();

            if (res.next()) {

                cliente = new Cliente();

                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setNome(res.getString("nome"));
                cliente.setDataNascimento(res.getDate("data_nascimento"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));
            }

            return cliente;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> pesquisarNome(String nome) {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome, data_nascimento, email, telefone FROM cliente WHERE nome LIKE?");
            
            stmt.setString(1, nome + "%");
            res = stmt.executeQuery();
            
            List<Cliente> lista = new ArrayList<>();
            while (res.next()) {
                cliente = new Cliente();
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setNome(res.getString("nome"));
                cliente.setDataNascimento(res.getDate("data_nascimento"));
                cliente.setEmail(res.getString("email"));
                cliente.setTelefone(res.getString("telefone"));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int getUltimoIdInserido() {

        try {
            this.sql = "Select max(id) as id FROM cliente";

            stmt = conexao.prepareStatement(sql);

            res = stmt.executeQuery();

            if (res.next()) {
                return res.getInt("id");
            } else {
                return 0;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }

    }
}
