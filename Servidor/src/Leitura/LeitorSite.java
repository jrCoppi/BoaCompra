package Leitura;

import rmi.Controle;
import Dados.ResultadoBusca;
import Dados.Site;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Classe que inicia a leitura por site onde cada site é a soma do site + produto
 * Ex: mercacdo.com/search/carne
 *     mercacdo.com/search/arroz
 */
public class LeitorSite extends Thread {
    
    private Site site;
    private Controle controle;
    private String produto;

    public LeitorSite(Site site,String produtoAtual,Controle controle){
        this.site = site;
        this.controle = controle;
        this.produto = produtoAtual;
    }
    
    @Override
    public void run(){
        String enderecoInicial = this.site.getEndereco();
        
        // Tratar marca com espaços
        String produtoInterno = (this.produto.replaceAll(" ", this.site.getQuebraDeLinha())); 
        enderecoInicial = enderecoInicial.replaceAll("replace", produtoInterno);

        this.controle.manipula.salvaDados("Site - " + enderecoInicial + " Iniciado!");
        
        
        this.efetuaLeitura(enderecoInicial);
    }
    
    // Efetua a leitura de uma url (site), a partir de duas bibliotecas do java reecbe um conteudo
    // HTML de uma pagina web, a partir do corpo desta pagina busca os dados com base nos dados do site
    private void efetuaLeitura(String endereco){
        String inputLine;
        String linhaAtual;
        Boolean dentroDoBody;
        Boolean dentroConteudo;
        Boolean encontrouDescricao = null;
        ResultadoBusca resultado = new ResultadoBusca(this.site.getNome());
        
        try {
            this.controle.requisitaLugarSemaforo();
            //Se precisar de proxy
            /* Properties props = System.getProperties();
             props.put("http.proxyPort","3128"); //proxy port
             props.put("http.proxyHost","proxy.unimestre.com"); //the proxy server name or IP
             props.put("http.proxySet", "true");*/

            // Usa classes do java pra criar uma url e buscar a pagina
            URLConnection connection = new URL(endereco).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    connection.getInputStream(),
                            this.site.getCodificacao()
                    )
            );

            dentroDoBody = false;
            dentroConteudo = false;
            encontrouDescricao = false;
            
            //Le o HTML, processando para encontrar os preços
            while ((inputLine = in.readLine()) != null){
                
                //Esta dentro do Body, deve começar a ler
                if(inputLine.contains("<body") ){
                    dentroDoBody = true;
                }

                if(dentroDoBody){
                    
                    inputLine = this.tratarLinha(inputLine);
                    if(inputLine.contains(this.site.getTagProdutos()) ){                  
                        dentroConteudo = true;
                    }
                    
                    if(dentroConteudo && !inputLine.equals("")){
                        
                        if(inputLine.contains(this.site.getTagNome()) || encontrouDescricao == false){
                            inputLine = this.removeHtml(inputLine);
                            encontrouDescricao = false;
                            if(!inputLine.trim().equals("")){
                                encontrouDescricao = true;
                                resultado.setDescricaoProduto(inputLine);
                            }
                        }
                        
                        if(inputLine.contains(this.site.getTagPreco())){
                            inputLine = this.removeHtml(inputLine);
                            inputLine = this.apenasNumeros(inputLine);
                            inputLine = inputLine.replace(",", ".");
                            resultado.setPrecoProduto(inputLine);
                            
                            //reset
                            this.controle.adicionarResultado(produto + ';' + this.site.getNome(), resultado);
                            resultado = new ResultadoBusca(this.site.getNome());
                            encontrouDescricao = false;
                        }
                    }
                }
            }
            
        } catch (Exception ex) {
          this.controle.manipula.salvaDados("Erro ao acessar site; " + endereco + "Iniciado!");
        } finally {
            this.controle.liberaLugarSemaforo();
        }
    }

    // Trata a linha usando replace para tags inuteis, tags html e numros
    // Alem de retirar caracteres especiais, sobranco apenas palvras
    private String tratarLinha(String str){
        String regexHtmlEntites = "&(.+?);";
        
        str = str.replaceAll(regexHtmlEntites, "");        
        str = str.replaceAll("\t", "");
        str = str.replaceAll("\n", "");
        str = str.replaceAll("\\...", "");
        str = str.replaceAll(":", "");
        str = str.replaceAll(";", "");
        str = str.replaceAll("\\.", "");
        str = str.replaceAll("/", "");
        str = str.replaceAll("\\}", "");
        str = str.replaceAll("\\{", "");
        str = str.replaceAll("\\)", "");
        str = str.replaceAll("\\(", "");
        
        return str.trim();
    }
    
    private String removeHtml(String str){
        String regexTags = "<(.+?)>";
        return str.replaceAll(regexTags, "");
    } 
    
    private String apenasNumeros(String str){
        String regexTags = "[^0-9,]";
        return str.replaceAll(regexTags, "");
    }
}
