package com.biogade.proyectomusical.model;

public enum GeneroMusical {
    ROCK("Rock"),
    POP("Pop"),
    JAZZ("Jazz"),
    HIPHOP("Hip-Hop"),
    CLASICA("Clasica"),
    REGUETON("Regueton"),
    ELECTRONICA("Electronica");

    private String categoriaCancion;
    GeneroMusical(String categoriaCancion){
        this.categoriaCancion = categoriaCancion;
    }

    public static GeneroMusical fromString(String text) {
        for (GeneroMusical categoria : GeneroMusical.values()) {
            if (categoria.categoriaCancion.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

}
