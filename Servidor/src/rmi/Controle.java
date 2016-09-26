package rmi;

import Arquivo.Manipula;
import Dados.ResultadoBusca;
import Dados.Site;
import Leitura.Leitura;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de Controle do Servidor
 */
public class Controle {
    private ArrayList<String> listaProdutos;
    private ArrayList<Site> listaSites;
    private HashMap<String,ArrayList<ResultadoBusca>> hashResultados;
    public Manipula manipula;
    private Integer numeroMercados;
    private Leitura leitura;
    private Semaphore semaforoInt;
    
    public Controle(){
        this.listaProdutos = new ArrayList<>();
        this.listaSites = new ArrayList<>();
        this.numeroMercados = 0;
        this.iniciaSites();
        this.hashResultados = new HashMap<>();
        this.manipula = manipula.getInstance();
        this.semaforoInt = new Semaphore(500,true);
    }
    
    public ArrayList<String> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<String> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }    
    
    public ArrayList<Site> getListaSites() {
        return listaSites;
    }
    
    //Incia a listagem com os sites
    private void iniciaSites(){
        Site siteAtual = new Site(
                "Angeloni",
                "http://www.angeloni.com.br/super/busca?q=replace", 
                "UTF-8", 
                ",",
                "class=\"lstProd \"",
                "descr",
                "class=\"price\"");
        this.listaSites.add(siteAtual); 
        
        this.numeroMercados = 1;
    }
        
    //Dispara os mtodos de Leitura
    public void disparaPrograma(){
        this.limpaConsulta();
        this.leitura = new Leitura();
        this.leitura.setControle(this);
        this.leitura.iniciaLeitura();
        
        this.leitura.finalizaLeitura();
    }
    
    //Chave = Produto
    public synchronized void adicionarResultado(String produto,ResultadoBusca resultado) {
        if(this.hashResultados.get(produto) == null){
            ArrayList<ResultadoBusca> arrInterno = new ArrayList<>();
            this.hashResultados.put(produto, arrInterno);
        }

        this.hashResultados.get(produto).add(resultado);
        notifyAll();
    }
    
    public HashMap<String,ArrayList<ResultadoBusca>> getHashResultado(){
        return this.hashResultados;
    }
    
    public void limpaConsulta(){
        this.hashResultados = new HashMap<>();
    }

    public Integer getNumeroMercados() {
        return numeroMercados;
    }
    
    public void requisitaLugarSemaforo(){
        try {
            this.semaforoInt.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void liberaLugarSemaforo(){
        this.semaforoInt.release();
    } 
    
}
