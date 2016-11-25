/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import Dados.Produto;
import Dados.ResultadoBusca;
import rmi.Comunicacao;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servidor para leitura
 */
public class ServidorLeitura extends UnicastRemoteObject implements Comunicacao {
    private Integer idServidor;
    private Controle controle;
    
    public ServidorLeitura(Integer id) throws RemoteException {
        //super();
        this.idServidor = id;
        //Cada servidor tem seu controle para evitar erros
        this.controle = new Controle();
    }
   
    //Inicia os servidores
    public static void main(String[] args) {
        //teste();
        try {
           ServidorLeitura serv1 = new ServidorLeitura(1);
           ServidorLeitura serv2 = new ServidorLeitura(2);
           ServidorLeitura serv3 = new ServidorLeitura(3);
           Naming.rebind("/localhost/ServidorLeitura1", serv1);
           Naming.rebind("/localhost/ServidorLeitura2", serv2);
           Naming.rebind("/localhost/ServidorLeitura3", serv3);
           System.out.println("Servidor no ar");
        } catch (Exception ex) {
         //  controleInt.manipula.salvaDados("Exception: " + ex.getMessage());
        }
    }
    
    public static void teste(){
        try {
            ServidorLeitura serv1 = new ServidorLeitura(1);
            String produto = "coca;bono;doritos";
            String categoria = "1;3;2";

            String[] arrProdutos = produto.split(";");
            String[] arrCategorias = categoria.split(";");
            
            ArrayList<Produto> entrada = new ArrayList<>();
            Produto produtoAtual;
            for (int i = 0; i < arrProdutos.length; i++) {
                produtoAtual = new Produto(arrProdutos[i], arrCategorias[i]);
                entrada.add(produtoAtual);
            }
            serv1.efetuaLeitura(entrada);
        } catch (RemoteException ex) {
            Logger.getLogger(ServidorLeitura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public HashMap<String, ArrayList<ResultadoBusca>> efetuaLeitura(ArrayList<Produto> Produto) throws RemoteException {
         // Efetua o procesasmento dos dadoss
        controle.setListaProdutos(Produto);
        //Dispara leitura e processamento
        controle.disparaPrograma();
        //classifica 
        controle.classificaResultados();
        
        return controle.getHashResultado();
    }

    //Ordena para cada produto pelo menor preço encontrado (dependendo da flag)
    @Override
    public HashMap<String, ArrayList<ResultadoBusca>> ordernaResultado(String flag) throws RemoteException {
        HashMap<String,ArrayList<ResultadoBusca>> resultado = this.controle.getHashResultado();
      
        //Faz uma copia do irignal para não dar erro na iteração
        HashMap<String,ArrayList<ResultadoBusca>> novoResultado = new HashMap<>();
        novoResultado.putAll(resultado);
        //Numero de mercados cadastrados
        Integer[] arrOrdenacao;
        ArrayList<ResultadoBusca> listaMercadosProduto;
        ArrayList<ResultadoBusca> listaOrdenadaMercadosProduto = new ArrayList<>();

        //Para cada produto
        Set<String> produtos = resultado.keySet();  
        for (String produto : produtos)  
        {  
            //Ordena com base na flag, indo resgistro por registro guardando a ordem dos indices a serem gravados no fim
            listaMercadosProduto = resultado.get(produto);
            arrOrdenacao = new Integer[this.controle.getNumeroMercados()];
            for (int i = 0; i < listaMercadosProduto.size(); i++) {
                if(listaMercadosProduto.get(i) == null){
                    break;
                }
                //Preenche o array com os indices do arraylist
                arrOrdenacao[i] = i;
            }
            
            //Ordena os registros via algoritimo de bolha, passando 1 a 1 e ordenando pelo preço
            arrOrdenacao = this.ordenaArray(listaMercadosProduto, arrOrdenacao);

            //Com o array reordenado cria um novo arraylist com os valores na ordem
            listaOrdenadaMercadosProduto = new ArrayList<>();
            for (int i = 0; i < arrOrdenacao.length; i++) {
                listaOrdenadaMercadosProduto.add(listaMercadosProduto.get(arrOrdenacao[i]));
            }
            
            //Substitui no hashmap
            novoResultado.remove(produto);
            novoResultado.put(produto, listaOrdenadaMercadosProduto);
        }
        return novoResultado;        
    }
    
    ///Aplica algoritimo da bolha para reordenar os indices do array de acordo com os preços
    private Integer[] ordenaArray(ArrayList<ResultadoBusca> listaMercadosProduto, Integer[] arrOrdenacao)
    {
	int aux;
        Double precoProdutoProximo;
        Double precoProdutoAtual;
        
	for(int i = 0; i<this.controle.getNumeroMercados(); i++){
                
		for(int j = 0; j<(this.controle.getNumeroMercados()-1); j++){

                    precoProdutoAtual = Double.parseDouble(listaMercadosProduto.get(arrOrdenacao[j]).getPrecoProduto());
                    precoProdutoProximo =  Double.parseDouble(listaMercadosProduto.get(arrOrdenacao[j+1]).getPrecoProduto());

			if(precoProdutoAtual > precoProdutoProximo){
				aux = arrOrdenacao[j];
				arrOrdenacao[j] = arrOrdenacao[j+1];
				arrOrdenacao[j+1] = aux;
			}
		}
	}
        
        return arrOrdenacao;
    }
}
