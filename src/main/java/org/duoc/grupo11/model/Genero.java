package org.duoc.grupo11.model;

public enum Genero {
    COMEDIA("Comedia"),
    DRAMA("Drama"),
    ACCION("Acción"),
    TERROR("Terror"),
    CIENCIA_FICCION("Ciencia Ficción"),
    ROMANCE("Romance"),
    AVENTURA("Aventura"),
    ANIMACION("Animación");


    private final String nombre;

    Genero(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}