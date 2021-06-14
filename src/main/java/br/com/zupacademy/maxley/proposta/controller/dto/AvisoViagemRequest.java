package br.com.zupacademy.maxley.proposta.controller.dto;

import br.com.zupacademy.maxley.proposta.model.AvisoViagem;
import br.com.zupacademy.maxley.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {
    @NotBlank
    private String destino;
    @NotNull @Future @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validoAte;

    @Deprecated
    public AvisoViagemRequest(){}

    public AvisoViagemRequest(String destinoViagem, LocalDate dataTermino) {
        this.destino = destinoViagem;
        this.validoAte = dataTermino;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public AvisoViagem toModel(Cartao cartao, String userAgent, String remoteAddr) {
        return new AvisoViagem(destino, validoAte, userAgent, remoteAddr, cartao);
    }
}
