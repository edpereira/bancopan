package br.com.bancopan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue
    @JsonIgnore
    private BigInteger id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    @Getter(AccessLevel.NONE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cpf")
    private Client client;

}
