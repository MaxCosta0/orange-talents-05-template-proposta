package br.com.zupacademy.maxley.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String carteira;
    @ManyToOne
    private Cartao cartao;

    public CarteiraDigital(String email, String carteira, Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
