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
public class Entregador {

    private int id;
    private String nome;
    private String cnh;

    public Entregador() {
    }

    public Entregador(int id, String nome, String cnh) {
        this.id = id;
        this.nome = nome;
        this.cnh = cnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
    
    @Override
    public String toString() {
        return nome;
    }

}
