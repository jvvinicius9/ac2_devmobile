package com.example.remedios.model;

public class Medicamento {
    public long id;
    public String nome;
    public String descricao;
    public String horario;
    public int consumido;

    public Medicamento(long id, String nome, String descricao, String horario, int consumido) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.horario = horario;
        this.consumido = consumido;
    }

    public Medicamento(String nome, String descricao, String horario, int consumido) {
        this.nome = nome;
        this.descricao = descricao;
        this.horario = horario;
        this.consumido = consumido;
    }
}
