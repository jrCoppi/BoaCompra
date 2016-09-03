package Leitura;

import rmi.Controle;
import Dados.Site;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Inicia leitura, dispara sites
 * arrLeitores - Guarda as threads dos sites para esperar acabar
 * limiteLinhas - Limitamos o numero de threads usando semaforo, 
 *   as threads que ele limita são as linhas dos sites então pode ocorrer estouro
 */
public class Leitura {
    private Controle controle;
    private ArrayList<LeitorSite> arrLeitores; 

    public Leitura(){
        this.arrLeitores = new ArrayList();
    }
    
    public Controle getControle() {
        return controle;
    }

    public void setControle(Controle controle) {
        this.controle = controle;
    }
    
    public void iniciaLeitura(){
        ArrayList<Site> sites;
        LeitorSite leitor;
        sites = this.controle.getListaSites();
        ArrayList<String> produtos = this.controle.getListaProdutos();
       
        // Dispara thread para cada site, guarda para ver se todos acabarem
        for (Site siteAtual : sites) {
            
            for (String produtoAtual : produtos) {
                leitor = new LeitorSite(siteAtual,removerAcentos(produtoAtual),this.controle);
                leitor.start();
                arrLeitores.add(leitor);
            }
        }
    }
    
    public void finalizaLeitura(){
        for (LeitorSite leitor1 : arrLeitores) {
            try { 
                leitor1.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Leitura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
