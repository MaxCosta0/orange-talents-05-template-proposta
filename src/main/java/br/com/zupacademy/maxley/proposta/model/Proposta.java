package br.com.zupacademy.maxley.proposta.model;

import br.com.zupacademy.maxley.proposta.config.annotation.CPForCNPJ;
import br.com.zupacademy.maxley.proposta.controller.dto.EstadoProposta;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank
    @CPForCNPJ
    private String documento;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private EstadoProposta estado;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public Proposta(){}

    public Proposta(@NotBlank @CPF String documento, @NotBlank @Email String email,
                    @NotBlank String nome, @NotBlank String endereco,
                    @NotNull @Positive BigDecimal salario){

        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setEstado(EstadoProposta estado) {
        this.estado = estado;
    }

    public EstadoProposta getEstado() {
        return estado;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}