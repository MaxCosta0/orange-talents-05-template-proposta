package br.com.zupacademy.maxley.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id @Column(length = 50)
    private String id;
    @NotBlank
    private String emitidoEm;
    @NotBlank
    private String titular;
    @Positive
    private Integer limite;
    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometrias = new ArrayList<>();

    @Deprecated
    public Cartao(){}

    public Cartao(String id, String emitidoEm, String titular, Integer limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }

    public List<Biometria> getBiometrias() {
        return biometrias;
    }
}
