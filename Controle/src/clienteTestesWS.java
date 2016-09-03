/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import webservice.WSProcessaInterface;

/**
 * Teste do WebService
 */
public class clienteTestesWS {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:9876/webservice?wsdl");
        QName qname = new QName("http://webservice/","WSProcessaImplService");
        Service ws = Service.create(url, qname);
        WSProcessaInterface process = ws.getPort(WSProcessaInterface.class);
 
        System.out.println("Produto: " + process.leitura("coca;nescau;guarana"));
    }
}
