/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

/**
 *
 * interface de comunicacao RMI
 */
import Dados.Produto;
import Dados.ResultadoBusca;
import java.rmi.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface Comunicacao extends Remote {
   public HashMap<String,ArrayList<ResultadoBusca>> efetuaLeitura(ArrayList<Produto> Produto) throws RemoteException;
   public HashMap<String,ArrayList<ResultadoBusca>> ordernaResultado(String flag)  throws RemoteException;
}

