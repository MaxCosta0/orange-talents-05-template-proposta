package br.com.zupacademy.maxley.proposta.controller.dto;

import br.com.zupacademy.maxley.proposta.model.Biometria;
import br.com.zupacademy.maxley.proposta.model.Cartao;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    public String fingerprint;

    @Deprecated
    public BiometriaRequest(){}

    public BiometriaRequest(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public Biometria toModel(Cartao cartao){
        return new Biometria(fingerprint, cartao);
    }
}
