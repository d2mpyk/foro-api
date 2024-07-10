package com.d2mp.foro.infra.errores;

public class IntegrityCheck extends RuntimeException{
    public IntegrityCheck(String field){
        super(field);
    }
}
