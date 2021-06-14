package br.com.zupacademy.maxley.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String destino;
    @NotNull
    private LocalDate validoAte;
    @NotBlank
    private String userAgent;
    @NotBlank
    private String remoteAddr;
    @NotNull
    private LocalDateTime instanteAviso;
    @ManyToOne
    private Cartao cartao;

    public AvisoViagem(String destinoViagem, LocalDate dataTermino, String userAgent, String remoteAddr, Cartao cartao) {

        this.destino = destinoViagem;
        this.validoAte = dataTermino;
        this.userAgent = userAgent;
        this.remoteAddr = remoteAddr;
        this.cartao = cartao;
        this.instanteAviso = LocalDateTime.now();
    }
}
