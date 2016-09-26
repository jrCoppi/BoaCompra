package rmi;

import Arquivo.Manipula;
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
    public String leitura(String produto) {
        try {            
            this.manipula.salvaDados("Antes leitura");
            
            //Dispara 3 servidores apra comparar
            Comunicacao comunica = (Comunicacao)Naming.lookup("/" + "localhost" + "/ServidorLeitura1"); 

            String[] arrProdutos = produto.split(";");
            
            ArrayList<String> entrada = new ArrayList<>();
            for (String produtoArr : arrProdutos) {
                entrada.add(produtoArr);
            }

            HashMap<String,ArrayList<ResultadoBusca>> retorno  = comunica.efetuaLeitura(entrada);
            
            // Joga arraylist numa classe container, criar um hash dessa classe, e fazer a conversão via exemplo 
            XStream xStream = new XStream(new DomDriver());
            String xml = xStream.toXML(retorno);
        
            this.manipula.salvaDados(xml);
            
            return xml;
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
        return "";
    }
}
