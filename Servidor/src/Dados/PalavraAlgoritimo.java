/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class PalavraAlgoritimo  implements Serializable  {
    private String palavra;
    private Integer importancia;
    private Boolean valorPositivo;

    public PalavraAlgoritimo(String palavra, Integer importancia, Boolean valorPositivo) {
        this.palavra = palavra;
        this.importancia = importancia;
        this.valorPositivo = valorPositivo;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public Integer getImportancia() {
        return importancia;
    }

    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }

    public Boolean getValorPositivo() {
        return valorPositivo;
    }

    public void setValorPositivo(Boolean valorPositivo) {
        this.valorPositivo = valorPositivo;
    }
    
    
    
}
