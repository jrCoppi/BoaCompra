package rmi;

import Arquivo.Manipula;
import Dados.CategoriaPrincipal;
import Dados.PalavraAlgoritimo;
import Dados.Produto;
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
    private ArrayList<Produto> listaProdutos;
    private ArrayList<Site> listaSites;
    private HashMap<String,ArrayList<ResultadoBusca>> hashResultados;
    public Manipula manipula;
    private Integer numeroMercados;
    private Leitura leitura;
    private Semaphore semaforoInt;
    private CategoriaPrincipal categoria;
    
    public Controle(){
        this.listaProdutos = new ArrayList<>();
        this.listaSites = new ArrayList<>();
        this.numeroMercados = 0;
        this.iniciaSites();
        this.hashResultados = new HashMap<>();
        this.manipula = manipula.getInstance();
        this.semaforoInt = new Semaphore(500,true);
        this.categoria = new CategoriaPrincipal();
    }
    
    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
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
    public synchronized void adicionarResultado(String chave,ResultadoBusca resultado) {
        if(this.hashResultados.get(chave) == null){
            ArrayList<ResultadoBusca> arrInterno = new ArrayList<>();
            this.hashResultados.put(chave, arrInterno);
        }

        this.hashResultados.get(chave).add(resultado);
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
    
    public void classificaResultados(){
        HashMap<String,Integer[]> hashPontuacao = new HashMap<>();
        
        this.geraPontuacao(hashPontuacao);
        
        this.ajustaPontuacao(hashPontuacao);
        
        this.normalizaDados(hashPontuacao);
        
        this.geraResultado();
    }
    
    private void geraResultado(){
        String[] chave;
        String produto;
        HashMap<String,ArrayList<ResultadoBusca>> arrResultado = new HashMap<>();
        ResultadoBusca resultado = null;
        int maiorPontuacao;
        
        for (String key : this.getHashResultado().keySet()) {
            chave = key.split(";");
            produto = chave[0];
            maiorPontuacao = 0;
            resultado = null;
            
            //Praca cada elemento do hashmap
            for (ResultadoBusca ResuladoBusca : this.getHashResultado().get(key)) {
                if(resultado == null){
                    maiorPontuacao = ResuladoBusca.getPontuacao();
                    resultado = ResuladoBusca;
                }
                
                if(maiorPontuacao < ResuladoBusca.getPontuacao()){
                    maiorPontuacao = ResuladoBusca.getPontuacao();
                    resultado = ResuladoBusca;
                }
            }
            
            //Depois de achar o melhor adiciona no array interno
            if(arrResultado.get(produto) == null){
                ArrayList<ResultadoBusca> arrInterno = new ArrayList<>();
                arrResultado.put(produto, arrInterno);
            }
            arrResultado.get(produto).add(resultado);
            
        }
        
        this.hashResultados = arrResultado;
    }
    
    private void ajustaPontuacao(HashMap<String,Integer[]> hashPontuacao){
        String[] chave;
 
        for (String key : hashPontuacao.keySet()) {
            if(hashPontuacao.get(key)[0] < 0){
                //soma no maior a diferenca pra 0
               hashPontuacao.get(key)[1] = hashPontuacao.get(key)[1] + (hashPontuacao.get(key)[0] * -1);
               hashPontuacao.get(key)[0] = 0;
               //VER COMO FICA E FAZER O MESMO PRAS PONTUACOES, VER PROXY
            }
        }
    }
    
    private void normalizaDados(HashMap<String,Integer[]> hashPontuacao){
        String[] chave;
        String produto;
        int menorValor;
        int maiorValor;
        float valorMedio;
        float pontuacao;
        float valorCima;
                
        for (String key : this.getHashResultado().keySet()) {
            chave = key.split(";");
            produto = chave[0];
            
            //Praca cada elemento do hashmap
            for (ResultadoBusca ResuladoBusca : this.getHashResultado().get(key)) {
                maiorValor = hashPontuacao.get(produto)[1];
                menorValor = hashPontuacao.get(produto)[0];
                valorMedio = maiorValor - menorValor;
                
                valorCima = (ResuladoBusca.getPontuacao() - menorValor);
                
                pontuacao =  valorCima / (valorMedio);
                
                ResuladoBusca.setPontuacao((int) (pontuacao*100));
            }
        }
    }
    
    private void geraPontuacao(HashMap<String,Integer[]> hashPontuacao){
        String[] chave;
        String produto;
        Produto produtoInt;
        int pontuacao;
        Integer[] arrPontuacao;
        
        //hash de pontuacao
        for (String key : this.getHashResultado().keySet()) {
            chave = key.split(";");
            produto = chave[0];
            arrPontuacao = new Integer[2];
            arrPontuacao[0] = 999;
            arrPontuacao[1] = 0;
            if(hashPontuacao.get(produto) == null){
                hashPontuacao.put(produto, arrPontuacao);
            }
            
            //Praca cada elemento do hashmap
            for (ResultadoBusca ResuladoBusca : this.getHashResultado().get(key)) {
                produtoInt = this.getProduto(produto);
                pontuacao = this.getPontuacao(
                        ResuladoBusca.getDescricaoProduto(), 
                        produtoInt.getCategoria()
                );
                
                //Maior/Menor
                if(pontuacao < hashPontuacao.get(produto)[0]){
                    hashPontuacao.get(produto)[0] = pontuacao;
                }
                
                if(pontuacao > hashPontuacao.get(produto)[1]){
                    hashPontuacao.get(produto)[1] = pontuacao;
                }
                
                //Seta pontuacação
                ResuladoBusca.setPontuacao(pontuacao);
            }
        }
    }
    
    //PEGAR MAIOR E MNOR > FAZER NORMALIZACAO
    public int getPontuacao(String descricao, String categoria){
        String[] chave;
        HashMap<String, PalavraAlgoritimo> hash = null;
        int pontos = 0;
        //Pega as palavras com base na categoria
        switch(categoria){
            case "1":{
                hash = this.categoria.getHashPalavrasRefrigerante();
                break;
            }
            case "2":{
                hash = this.categoria.getHashPalavrasSalgado();
                break;
            } 
            case "3":{
                hash = this.categoria.getHashPalavrasBolacha();
                break;
            }
            case "4":{
                hash = this.categoria.getHashPalavrasBebida();
                break;
            }
            default:
                return 0;
        }
        
        
        
        descricao = descricao.toLowerCase();
        String[] partes = descricao.split(" ");
        //Pontua as palavras que voltaram da busca
        for (String palavra : hash.keySet()) {

            for (int i = 0; i < partes.length; i++) {
                palavra = palavra.toLowerCase();
                if(partes[i].contains(palavra)){
                    try{
                        if(hash.get(palavra).getValorPositivo()){
                            pontos += hash.get(palavra).getImportancia();
                        } else {
                            pontos -= hash.get(palavra).getImportancia();
                        }  
                    } catch (Exception ex){
                        
                    }

                }
            }
        }
        return pontos;
    }

    private Produto getProduto(String chave){
        for (Produto listaProduto : listaProdutos) {
            if(chave.equals(listaProduto.getDescricao())){
                return listaProduto;
            }
        }
        
        return null;
    }
}
