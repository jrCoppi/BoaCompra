/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.util.HashMap;

/**
 *
 * @author Unimestre
 */
public class CategoriaPrincipal {
    private HashMap<String, PalavraAlgoritimo> hashPalavrasRefrigerante;
    private HashMap<String, PalavraAlgoritimo> hashPalavrasSalgado;
    private HashMap<String, PalavraAlgoritimo> hashPalavrasBolacha;
    private HashMap<String, PalavraAlgoritimo> hashPalavrasBebida;

    public CategoriaPrincipal() {
        this.hashPalavrasRefrigerante = new HashMap<>();
        this.hashPalavrasSalgado = new HashMap<>();
        this.hashPalavrasBolacha = new HashMap<>();
        this.hashPalavrasBebida = new HashMap<>();
        
        this.iniciaListaBebida();;
        this.iniciaListaBolacha();
        this.iniciaListaRefrigerante();
        this.iniciaListaSalgado();
    }
    
    public HashMap<String, PalavraAlgoritimo> getHashPalavrasRefrigerante() {
        return hashPalavrasRefrigerante;
    }

    public void setHashPalavrasRefrigerante(HashMap<String, PalavraAlgoritimo> hashPalavrasRefrigerante) {
        this.hashPalavrasRefrigerante = hashPalavrasRefrigerante;
    }

    public HashMap<String, PalavraAlgoritimo> getHashPalavrasSalgado() {
        return hashPalavrasSalgado;
    }

    public void setHashPalavrasSalgado(HashMap<String, PalavraAlgoritimo> hashPalavrasSalgado) {
        this.hashPalavrasSalgado = hashPalavrasSalgado;
    }

    public HashMap<String, PalavraAlgoritimo> getHashPalavrasBolacha() {
        return hashPalavrasBolacha;
    }

    public void setHashPalavrasBolacha(HashMap<String, PalavraAlgoritimo> hashPalavrasBolacha) {
        this.hashPalavrasBolacha = hashPalavrasBolacha;
    }

    public HashMap<String, PalavraAlgoritimo> getHashPalavrasBebida() {
        return hashPalavrasBebida;
    }

    public void setHashPalavrasBebida(HashMap<String, PalavraAlgoritimo> hashPalavrasBebida) {
        this.hashPalavrasBebida = hashPalavrasBebida;
    }
    
    private void iniciaListaRefrigerante(){
        PalavraAlgoritimo palavra;
        
        palavra = new PalavraAlgoritimo("cereal", 5, false);
        this.getHashPalavrasRefrigerante().put("cereal", palavra);
        palavra = new PalavraAlgoritimo("cocada", 5, false);
        this.getHashPalavrasRefrigerante().put("cocada", palavra);
        palavra = new PalavraAlgoritimo("copo", 3, false);
        this.getHashPalavrasRefrigerante().put("copo", palavra);
        palavra = new PalavraAlgoritimo("250ml", 3, true);
        this.getHashPalavrasRefrigerante().put("250ml", palavra);
        palavra = new PalavraAlgoritimo("350ml", 3, true);
        this.getHashPalavrasRefrigerante().put("350ml", palavra);
        palavra = new PalavraAlgoritimo("ml", 3, true);
        this.getHashPalavrasRefrigerante().put("ml", palavra);
        palavra = new PalavraAlgoritimo("refrigerante", 5, true);
        this.getHashPalavrasRefrigerante().put("refrigerante", palavra);
        palavra = new PalavraAlgoritimo("garrafa", 3, true);
        this.getHashPalavrasRefrigerante().put("garrafa", palavra);
        palavra = new PalavraAlgoritimo("Lata", 3, true);
        this.getHashPalavrasRefrigerante().put("Lata", palavra);
        palavra = new PalavraAlgoritimo("Pet", 1, true);
        this.getHashPalavrasRefrigerante().put("Pet", palavra);
    }
    
    private void iniciaListaSalgado(){
        PalavraAlgoritimo palavra;
        
        palavra = new PalavraAlgoritimo("salgadinho", 5, true);
        this.getHashPalavrasSalgado().put("salgadinho", palavra);
        palavra = new PalavraAlgoritimo("pacote", 3, true);
        this.getHashPalavrasSalgado().put("pacote", palavra);
        palavra = new PalavraAlgoritimo("167g", 1, true);
        this.getHashPalavrasSalgado().put("167g", palavra);
        palavra = new PalavraAlgoritimo("doritos", 5, true);
        this.getHashPalavrasSalgado().put("doritos", palavra);
        palavra = new PalavraAlgoritimo("fandangos", 5, true);
        this.getHashPalavrasSalgado().put("fandangos", palavra);
        palavra = new PalavraAlgoritimo("salgado", 3, true);
        this.getHashPalavrasSalgado().put("salgado", palavra);
        palavra = new PalavraAlgoritimo("queijo", 2, true);
        this.getHashPalavrasSalgado().put("queijo", palavra);
    }
    
    private void iniciaListaBolacha(){
        PalavraAlgoritimo palavra;
        
        palavra = new PalavraAlgoritimo("bolacha", 5, true);
        this.getHashPalavrasBolacha().put("bolacha", palavra);
        palavra = new PalavraAlgoritimo("biscoito", 5, true);
        this.getHashPalavrasBolacha().put("biscoito", palavra);
        palavra = new PalavraAlgoritimo("biscoito", 5, true);
        this.getHashPalavrasBolacha().put("biscoito", palavra);
    }
    
    private void iniciaListaBebida(){
        PalavraAlgoritimo palavra;
        
        palavra = new PalavraAlgoritimo("coco", 3, false);
        this.getHashPalavrasBebida().put("coco", palavra);
        
        palavra = new PalavraAlgoritimo("Unidade", 3, false);
        this.getHashPalavrasBebida().put("Unidade", palavra);
        palavra = new PalavraAlgoritimo("bebida", 5, true);
        this.getHashPalavrasBebida().put("bebida", palavra);
        palavra = new PalavraAlgoritimo("Soja", 1, true);
        this.getHashPalavrasBebida().put("Soja", palavra);
        palavra = new PalavraAlgoritimo("cerveja", 3, true);
        this.getHashPalavrasBebida().put("cerveja", palavra);
        palavra = new PalavraAlgoritimo("lata", 5, true);
        this.getHashPalavrasBebida().put("lata", palavra);
    }
}
