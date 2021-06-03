package br.com.zupacademy.maxley.proposta.config.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroPadrao {

    private String campo;
    private String mensagem;
    private LocalDateTime dataHora;

    public ErroPadrao(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
        this.dataHora = LocalDateTime.now();
    }

    public ErroPadrao(String mensagem){
        this.mensagem = mensagem;
        this.dataHora = LocalDateTime.now();
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
