package br.com.zupacademy.maxley.proposta.controller.dto;

import br.com.zupacademy.maxley.proposta.model.Cartao;
import br.com.zupacademy.maxley.proposta.model.CarteiraDigital;

import javax.validation.constraints.NotBlank;

public class CarteiraDigitalRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String carteira;

    @Deprecated
    public CarteiraDigitalRequest(){}

    public CarteiraDigitalRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public CarteiraDigital toModel(Cartao cartao) {
        return new CarteiraDigital(email, carteira, cartao);
    }

    @Override
    public String toString() {
        return "CarteiraDigitalRequest{" +
                "email='" + email + '\'' +
                ", carteira='" + carteira + '\'' +
                '}';
    }
}
