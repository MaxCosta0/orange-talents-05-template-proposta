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
    private String destinoViagem;
    @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTermino;

    @Deprecated
    public AvisoViagemRequest(){}

    public AvisoViagemRequest(String destinoViagem, LocalDate dataTermino) {
        this.destinoViagem = destinoViagem;
        this.dataTermino = dataTermino;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public AvisoViagem toModel(Cartao cartao, String userAgent, String remoteAddr) {
        return new AvisoViagem(destinoViagem, dataTermino, userAgent, remoteAddr, cartao);
    }
}
