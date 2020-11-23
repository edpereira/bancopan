package br.com.empresa.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class Regiao {

    @Getter(AccessLevel.NONE)
    private String id;
    private String sigla;
    private String nome;

}
