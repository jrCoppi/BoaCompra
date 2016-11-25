package rmi;

import Arquivo.Manipula;
import Dados.Produto;
import Dados.ResultadoBusca;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class RMIProcessa {
    private static RMIProcessa instance;
    private Manipula manipula;

    public static RMIProcessa getInstance(){
        if (instance == null)
            instance = new RMIProcessa();
        return instance;
    }

    public RMIProcessa() {
        this.manipula = Manipula.getInstance();
    }
    
    //Efetua leitura a partir do produto vindo do web-service
    public String leitura(String produto, String categoria) {
        try {            
            this.manipula.salvaDados("Antes leitura");
            
            //Dispara 3 servidores apra comparar
            Comunicacao comunica1 = (Comunicacao)Naming.lookup("/" + "localhost" + "/ServidorLeitura1"); 
            Comunicacao comunica2 = (Comunicacao)Naming.lookup("/" + "localhost" + "/ServidorLeitura2"); 
            Comunicacao comunica3 = (Comunicacao)Naming.lookup("/" + "localhost" + "/ServidorLeitura3"); 
            
            String[] arrProdutos = produto.split(";");
            String[] arrCategorias = categoria.split(";");
            
            ArrayList<Produto> entrada = new ArrayList<>();
            Produto produtoAtual;
            for (int i = 0; i < arrProdutos.length; i++) {
                produtoAtual = new Produto(arrProdutos[i], arrCategorias[i]);
                entrada.add(produtoAtual);
            }

            HashMap<String,ArrayList<ResultadoBusca>> retornoFinal;
            Comunicacao comunicaFinal;
            HashMap<String,ArrayList<ResultadoBusca>> retornos2  = comunica2.efetuaLeitura(entrada);
            HashMap<String,ArrayList<ResultadoBusca>> retornos1  = comunica1.efetuaLeitura(entrada);
            HashMap<String,ArrayList<ResultadoBusca>> retornos3  = comunica3.efetuaLeitura(entrada);
         
            //Verifica a igualdade entre os resultados do servidores
            retornoFinal = retornos1;
            comunicaFinal = comunica1;
            if(!this.comparaResultados(retornos1,retornos2)){
                
                retornoFinal = retornos2;
                comunicaFinal = comunica2;
                if(!this.comparaResultados(retornos2,retornos3)){
                    
                    retornoFinal = retornos3;
                    comunicaFinal = comunica3;
                    if(!this.comparaResultados(retornos1,retornos3)){
                        retornoFinal = retornos1;
                        comunicaFinal = comunica1;
                        //todos diferentes, avisar
                    }
                }
            }

            //Ordena os resultados com base no preço dos produtos
            try {
                retornoFinal = comunicaFinal.ordernaResultado("preco");
            } catch (Exception ex) {
                //
            }
            
            // Joga arraylist numa classe container, criar um hash dessa classe, e fazer a conversão via exemplo 
            XStream xStream = new XStream(new DomDriver());
            String xml = xStream.toXML(retornoFinal);
        
            this.manipula.salvaDados(xml);
            
            return xml;
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
        return "";
    }
    
    /**
     * Compara os resultados dos servidores para verificar se estão corretos
     * @param resultado1
     * @param resultado2
     * @return 
     */
    private boolean comparaResultados(HashMap<String,ArrayList<ResultadoBusca>> resultado1, HashMap<String,ArrayList<ResultadoBusca>> resultado2 ){
        ArrayList<ResultadoBusca> listaMercadosProdutoResultado1;
        ArrayList<ResultadoBusca> listaMercadosProdutoResultado2;
        String produtoAtual;
        Set<String> produtos = resultado1.keySet();  
        boolean encontrouProduto = false;
        
        for (String produto : produtos) {
            
            listaMercadosProdutoResultado1 = resultado1.get(produto);
            listaMercadosProdutoResultado2 = resultado2.get(produto);
            
            //Numero de mercados diferentes, existe diferença
            if(listaMercadosProdutoResultado2.size() != listaMercadosProdutoResultado1.size()){
                return false;
            }
            
            for (int i = 0; i < listaMercadosProdutoResultado1.size(); i++) {
                encontrouProduto = false;
                produtoAtual = listaMercadosProdutoResultado1.get(i).getDescricaoProduto();
                
                //Verifica se no segundo arraylist também temos o mesmo produto
                for (int j = 0; j < listaMercadosProdutoResultado2.size(); j++) {
                    if(produtoAtual == listaMercadosProdutoResultado2.get(j).getDescricaoProduto()){
                        encontrouProduto = true;
                        break;
                    }
                }
                
                //Encontrou diferença
                if(!encontrouProduto){
                    return false;
                }
            }
        }

        return true;
    }
}
