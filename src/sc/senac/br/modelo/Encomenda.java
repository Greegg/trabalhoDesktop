/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.modelo;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class Encomenda {

    private int id;
    private Date dataPedido;
    private Date dataEntrega;
    private Cliente cliente;
    private Endereco endereco;
    private Entregador entregador;
    private List<EncomendaProduto> encPRoduto;

    public Encomenda() {
        cliente = new Cliente();
        endereco = new Endereco();
        entregador = new Entregador();
    }

    public Encomenda(int id, Date dataPedido, Date dataEntrega, Cliente cliente, Endereco endereco, Entregador entregador) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.cliente = cliente;
        this.endereco = endereco;
        this.entregador = entregador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public List<EncomendaProduto> getEncPRoduto() {
        return encPRoduto;
    }

    public void setEncPRoduto(List<EncomendaProduto> encPRoduto) {
        this.encPRoduto = encPRoduto;
    }

    
    

}
