package com.biogade.proyectomusical.model;

import jakarta.persistence.*;

@Entity
@Table(name = "canciones")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String titulo;
    private Integer duracionEnSegundos;
    private String album;
    @ManyToOne
    private Cantante cantante;

    public Cancion(){}

    public Cancion (String titulo, Integer duracionEnSegundos, String album, Cantante cantante){
        this.titulo = titulo;
        this.duracionEnSegundos = duracionEnSegundos;
        this.album = album;
        this.cantante = cantante;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracionEnSegundos() {
        return duracionEnSegundos;
    }

    public void setDuracionEnSegundos(Integer duracionEnSegundos) {
        this.duracionEnSegundos = duracionEnSegundos;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Cantante getCantante() {
        return cantante;
    }

    public void setCantante(Cantante cantante) {
        this.cantante = cantante;
    }

    @Override
    public String toString() {
        return "Titulo='" + titulo + '\'' +
                ", Duracion En Segundos=" + duracionEnSegundos +
                ", Album='" + album + '\'' +
                ", Cantante=" + cantante.getNombre();
    }
}
