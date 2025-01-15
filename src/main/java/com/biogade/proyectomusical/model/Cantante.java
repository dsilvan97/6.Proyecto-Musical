package com.biogade.proyectomusical.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cantantes")
public class Cantante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Integer edad;
    @Enumerated(EnumType.STRING)
    private GeneroMusical generoMusical;
    private String nacionalidad;
    private String biografia;
    @OneToMany(mappedBy = "cantante", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cancion> canciones;

    public Cantante(String nombre, Integer edad, String genero, String nacionalidad, String biografia){

        this.nombre = nombre;
        this.edad = edad;
        this.generoMusical = GeneroMusical.fromString(genero);
        this.nacionalidad = nacionalidad;
        this.biografia = biografia;

    }

    public Cantante(){}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return "Nombre='" + nombre + '\'' +
                ", Edad=" + edad +
                ", GeneroMusical=" + generoMusical +
                ", Nacionalidad='" + nacionalidad + '\'' +
                ", Biografia='" + biografia;
    }
}