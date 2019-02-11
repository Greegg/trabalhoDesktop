/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sc.senac.br.modelo;

/**
 *
 * @author Alunos
 */
public class EncomendaProduto {

    private int id;
    private Encomenda encomenda;
    private Produto produto;

    private int quantidade;
    private float valor;
    private float subTotal;

    public EncomendaProduto() {
        produto = new Produto();
    }

    public EncomendaProduto(int id, Encomenda encomenda, Produto produto, int quantidade, float valor, float subTotal) {
        this.id = id;
        this.encomenda = encomenda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.subTotal = subTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

}
