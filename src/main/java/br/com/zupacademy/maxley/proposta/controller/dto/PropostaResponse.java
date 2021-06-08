package br.com.zupacademy.maxley.proposta.controller.dto;

public class PropostaResponse {

    private EstadoProposta estado;

    public PropostaResponse(EstadoProposta estado) {
        this.estado = estado;
    }

    public EstadoProposta getEstado() {
        return estado;
    }
}
