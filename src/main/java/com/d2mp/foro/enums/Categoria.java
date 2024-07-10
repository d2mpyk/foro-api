package com.d2mp.foro.enums;

public enum Categoria {
    BACKEND,
    DESARROLLO_PERSONAL,
    FRONTEND,
    PROGRAMACION;

    private String categoria;

    Categoria(String categoria){
        this.categoria = categoria;
    }

    Categoria() {

    }

    public static Categoria fromString(String text){
        for (Categoria categoria : Categoria.values()){
            if (categoria.categoria.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Esta categoria no fue encontrada: " + text);
    }
}
