/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Junior-PC
 */
public class Manipula implements Serializable {
    private static final String diretorio = "C:/temp/trabalho";
    private static Manipula instance;
    
    
    //Criamos o diretorio raiz caso não exista
    private Manipula() {
        File diretorioDest = new File(diretorio);
        if (!diretorioDest.exists()){
            diretorioDest.mkdir();
        } 
    }
    
    public static Manipula getInstance(){
        if (instance == null)
            instance = new Manipula();
        return instance;
    }
        
    //Cria o arquivo da marco em disco caso não exista
    //caso exista adiciona o noo link na proxima linha
     public void salvaDados(String texto){
        try {
            File diretorioDest = new File(diretorio + "/Servidor.txt");
            PrintWriter arqTexto;
            if(!existeMarca("servidor")){
                arqTexto = new PrintWriter(diretorioDest);
            } else {
                FileOutputStream fo = new FileOutputStream(diretorioDest, true);
                arqTexto = new PrintWriter(fo);
            }
            arqTexto.println(texto);
            arqTexto.close();

        } catch (FileNotFoundException ex) {
            //
        } 
     }
     
     //retorna se a marca existe
     public boolean existeMarca(String marca){
        File diretorioDest = new File(diretorio + "/" + marca + ".txt");
        return (diretorioDest.exists());
     }
}
