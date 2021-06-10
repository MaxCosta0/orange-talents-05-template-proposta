package br.com.zupacademy.maxley.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Entity
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Lob
    private byte[] fingerprint;
    @NotNull
    private LocalDate dataCriacao;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria(){}

    public Biometria(String fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint.getBytes(StandardCharsets.UTF_8);
        this.cartao = cartao;
        this.dataCriacao = LocalDate.now();
    }

    public Long getId() {
        return id;
    }
}
