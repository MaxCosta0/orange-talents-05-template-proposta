package br.com.zupacademy.maxley.proposta.model;

import br.com.zupacademy.maxley.proposta.controller.dto.EstadoCartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id @Column(length = 50)
    private String id;
    @NotBlank
    private String emitidoEm;
    @NotBlank
    private String titular;
    @Positive
    private Integer limite;
    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometrias = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private EstadoCartao estadoCartao;
    @OneToOne
    private DadosBloqueioCartao bloqueio;

    @Deprecated
    public Cartao(){}

    public Cartao(String id, String emitidoEm, String titular, Integer limite) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }
    
    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public DadosBloqueioCartao getBloqueio(){
        return bloqueio;
    }

    public void setBloqueio(DadosBloqueioCartao bloqueio){
        this.bloqueio = bloqueio;
    }

    public EstadoCartao getEstadoCartao() {
        return estadoCartao;
    }

    public void setEstadoCartao(EstadoCartao estadoCartao) {
        this.estadoCartao = estadoCartao;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id='" + id + '\'' +
                ", emitidoEm='" + emitidoEm + '\'' +
                ", titular='" + titular + '\'' +
                ", limite=" + limite +
                ", biometrias=" + biometrias +
                ", estadoCartao=" + estadoCartao +
                ", bloqueio=" + bloqueio +
                '}';
    }
}
