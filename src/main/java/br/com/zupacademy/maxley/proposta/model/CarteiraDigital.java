package br.com.zupacademy.maxley.proposta.model;

import br.com.zupacademy.maxley.proposta.controller.dto.TipoCarteiraDigital;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCarteiraDigital carteira;
    @ManyToOne
    private Cartao cartao;

    public CarteiraDigital(String email, TipoCarteiraDigital carteira, Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
