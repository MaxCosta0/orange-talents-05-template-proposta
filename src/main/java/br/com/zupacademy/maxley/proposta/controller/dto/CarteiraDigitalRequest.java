package br.com.zupacademy.maxley.proposta.controller.dto;

import br.com.zupacademy.maxley.proposta.model.Cartao;
import br.com.zupacademy.maxley.proposta.model.CarteiraDigital;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @NotBlank
    private String email;
    @NotNull @Enumerated(EnumType.STRING)
    private TipoCarteiraDigital carteira;

    @Deprecated
    public CarteiraDigitalRequest(){}

    public CarteiraDigitalRequest(String email, TipoCarteiraDigital carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteiraDigital getCarteira() {
        return carteira;
    }

    public CarteiraDigital toModel(Cartao cartao) {
        return new CarteiraDigital(email, carteira, cartao);
    }
}
