package Dados;

import java.io.Serializable;

// Salva o resutlado de uma busca
public class ResultadoBusca implements Serializable {
    private String descricaoProduto;
    private String precoProduto;
    private String mercado;

    public ResultadoBusca(String mercado) {
        this.mercado = mercado;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(String precoProduto) {
        this.precoProduto = precoProduto;
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }
    
    
}
