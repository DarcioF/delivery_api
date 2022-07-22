package dev.delivery.delivery.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "entrega")
@SequenceGenerator(name = "GEN_ID_ENTREGA", sequenceName = "GEN_ID_ENTREGA")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "GEN_ID_ENTREGA")
    private Integer id;
    private String rua;
    private Integer numero;
    private String cep;
    private String complemento;
    @Column(precision = 12, scale = 2)
    private BigDecimal taxa_entrega;
    private String cidade;
    private String bairro;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public BigDecimal getTaxa_entrega() {
        return taxa_entrega;
    }

    public void setTaxa_entrega(BigDecimal taxa_entrega) {
        this.taxa_entrega = taxa_entrega;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
