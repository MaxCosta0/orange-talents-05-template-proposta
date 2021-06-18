package br.com.zupacademy.maxley.proposta.controller.dto;

import br.com.zupacademy.maxley.proposta.config.annotation.CPForCNPJ;
import br.com.zupacademy.maxley.proposta.model.Proposta;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank @CPForCNPJ
    private String documento;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull @Positive
    private BigDecimal salario;

    public NovaPropostaRequest(@NotBlank @CPF String documento, @NotBlank @Email String email,
                               @NotBlank String nome, @NotBlank String endereço,
                               @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereço;
        this.salario = salario;
    }

    public String documentoCryptografado(){
        return new DocumentoLimpo(this.documento).encodeDocumento();
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

    public Proposta toModel() {
        return new Proposta(new DocumentoLimpo(documento), email, nome, endereco, salario);
    }
}
