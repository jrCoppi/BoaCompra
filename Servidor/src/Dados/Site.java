/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;


/**
 * Classe criada para definidir propriedades dos sites
 * apenasLinksNaHome - define se a primeira pagina é busca
 * numeroNiveisAcessados - quantos niveis pode se acessar
 * hashLinks - guarda os links 
 * indicadorPagina - Indica o que simbolica um link de paginação no site
 * lockLinks - usa ReadWrite para manipular os links nas treads
 * 
 * Usamos ReadWriteLock pois queermos deixar que varias leituras acotençam ao mesmo tempo
 * mas temos de bloquear varias escitas[
 * Ainda temos de usar o await e o signall para esperar por novos links
 */
public class Site {
    private String endereco;
    private String codificacao;
    private String quebraDeLinha;
    private String tagProdutos;
    private String tagNome;
    private String tagPreco;
    private String nome;

    public Site(String nome,String endereco, String codificacao,String quebraDeLinha, String tagProdutos, String tagNome, String tagPreco) {
        this.endereco = endereco;
        this.codificacao = codificacao;
        this.quebraDeLinha = quebraDeLinha;
        this.tagProdutos = tagProdutos;
        this.tagNome = tagNome;
        this.tagPreco = tagPreco;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCodificacao() {
        return codificacao;
    }

    public void setCodificacao(String codificacao) {
        this.codificacao = codificacao;
    }

    public String getTagProdutos() {
        return tagProdutos;
    }

    public void setTagProdutos(String tagProdutos) {
        this.tagProdutos = tagProdutos;
    }

    public String getQuebraDeLinha() {
        return quebraDeLinha;
    }

    public void setQuebraDeLinha(String quebraDeLinha) {
        this.quebraDeLinha = quebraDeLinha;
    }

    public String getTagNome() {
        return tagNome;
    }

    public void setTagNome(String tagNome) {
        this.tagNome = tagNome;
    }

    public String getTagPreco() {
        return tagPreco;
    }

    public void setTagPreco(String tagPreco) {
        this.tagPreco = tagPreco;
    } 
}
