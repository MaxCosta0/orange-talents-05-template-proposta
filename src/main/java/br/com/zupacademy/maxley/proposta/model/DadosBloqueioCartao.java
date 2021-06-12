package br.com.zupacademy.maxley.proposta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class DadosBloqueioCartao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String ip;
    @NotBlank
    private String userAgent;
    private LocalDateTime instanteBloqueio;

    @Deprecated
    public DadosBloqueioCartao(){}

    public DadosBloqueioCartao(String ip, String userAgent) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.instanteBloqueio = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getInstanteBloqueio() {
        return instanteBloqueio;
    }
}
