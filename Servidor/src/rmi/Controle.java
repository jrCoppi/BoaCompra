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
 * @author lcoppi
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
        
        siteAtual = new Site(
                "Extra Delivery",
                "http://busca.deliveryextra.com.br/search?w=replace", 
                "UTF-8", 
                "+",
                "class=\"showcase showcase--3 group \"",
                "class=\"showcase-item__name\"",
                "class=\"value");
        this.listaSites.add(siteAtual);
        
        siteAtual = new Site(
                "Manbo Delivery",
                "http://busca.mambo.com.br/busca?q=replace", 
                "UTF-8", 
                "+",
                "class=\"prateleira principal",
                "class=\"collection-name\"",
                "class=\"preco-por\"");
        this.listaSites.add(siteAtual);
        
        this.numeroMercados = 3;
        
        
       /* Site siteAtual = new Site(
                "Pão de Açucar",
                "http://busca.paodeacucar.com.br/search?view=grid&asug=&w=replace", 
                "UTF-8", 
                "+",
                "class=\"sli_container ",
                "title=\"http://www.paodeacucar.com.br/produto",
                "class=\"value\"");
        this.listaSites.add(siteAtual); */
       // http://busca.deliveryextra.com.br/search?w=pao+de+acular
        //http://buscando.extra.com.br/search?w=nescau
        //http://busca.paodeacucar.com.br/search?view=grid&asug=&w=nescau
        //http://www.angeloni.com.br/super/busca?q=feijao
        //http://www.sondadelivery.com.br/delivery.aspx/busca/0/arroz%20prato
    }
        
    //Dispara os mtodos de Leitura
    public void disparaPrograma(){
        this.limpaConsulta();
        this.leitura = new Leitura();
        this.leitura.setControle(this);
        this.leitura.iniciaLeitura();
        
        this.leitura.finalizaLeitura();
    }
    
    //Controle de threads, varios sites adicionando ao mesmo tempo varios produtos
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
