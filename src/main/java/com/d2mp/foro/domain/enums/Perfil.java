package com.d2mp.foro.domain.enums;

public enum Perfil {
    ADMINISTRADOR("Administrador"),
    ESTUDIANTE("Estudiante"),
    INSTRUCTOR("Instructor"),
    MODERADOR("Moderador");

    private String perfil;

    Perfil(String perfil){
        this.perfil = perfil;
    }
    public static Perfil fromString(String text){
        for(Perfil perfil: Perfil.values()){
            if (perfil.perfil.equalsIgnoreCase(text)){
                return perfil;
            }
        }
        throw new IllegalArgumentException("Este perfil no fue encontrado: " + text);
    }
}
