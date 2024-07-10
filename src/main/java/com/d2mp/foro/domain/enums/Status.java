package com.d2mp.foro.domain.enums;

public enum Status {
    ABIERTO("Abierto"),
    CERRADO("Cerrado"),
    SOLUCIONADO("Solucionado");

    private String status;

    Status (String status){ this.status = status;}

    public static Status fromString(String text){
        for(Status status : Status.values()){
            if(status.status.equalsIgnoreCase(text)){
                return status;
            }
        }
        throw new IllegalArgumentException("Este status no fue encontrado: " + text);
    }
}
