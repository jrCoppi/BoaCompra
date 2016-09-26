/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import Dados.ResultadoBusca;
import rmi.Comunicacao;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Servidor para leitura
 */
public class ServidorLeitura extends UnicastRemoteObject implements Comunicacao {
    private Controle controle;
    
    public ServidorLeitura() throws RemoteException {
        super();
        //Cada servidor tem seu controle para evitar erros
        this.controle = new Controle();
    }
   
    //Inicia os servidores
    public static void main(String[] args) {
      //  Controle controleInt = Controle.getInstance();
        try {
           ServidorLeitura serv1 = new ServidorLeitura();
           Naming.rebind("/localhost/ServidorLeitura1", serv1);
           System.out.println("Servidor no ar");
        } catch (Exception ex) {
         //  controleInt.manipula.salvaDados("Exception: " + ex.getMessage());
        } 
    }

    @Override
    public HashMap<String, ArrayList<ResultadoBusca>> efetuaLeitura(ArrayList<String> Produto) throws RemoteException {
         // Efetua o procesasmento dos dados
        controle.setListaProdutos(Produto);
        //Dispara leitura e processamento
        controle.disparaPrograma();
        
        return controle.getHashResultado();
    }
}
