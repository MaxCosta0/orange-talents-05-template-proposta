package br.com.zupacademy.maxley.proposta.controller.dto;

import org.springframework.security.crypto.encrypt.Encryptors;

public class DocumentoLimpo {

    private String documento;

//    Preciso pensar em como receber esses valores do application.properties com @Value
    private String secret = "uJSNRAVKueud4J2uNitEsS0Fx2sIuW60";
    private String salt = "be22411cc4391bd1";

    public DocumentoLimpo(String documentoLimpo){
        this.documento = documentoLimpo;
    }

    public String encodeDocumento(){
        return Encryptors.text(secret, salt).encrypt(documento);
    }

    public String decodeDocumento(){
        return Encryptors.text(secret, salt).decrypt(documento);
    }
}
