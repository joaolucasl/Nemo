package me.jlucas.nemo.Models;

/**
 * Created by hansolo on 14/12/16.
 */
public class Category {
    private long id;
    private String nome;

    public long getId() {
        return id;
    }

    public Category setId(long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Category setNome(String nome) {
        this.nome = nome;
        return this;
    }
}
