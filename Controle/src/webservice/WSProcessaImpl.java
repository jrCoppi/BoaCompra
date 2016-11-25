package webservice;

import Arquivo.Manipula;
import javax.jws.WebService;
import rmi.RMIProcessa;

//Classe responsavel por ser a intermediaria entre o WebService para o RMI
@WebService(endpointInterface = "webservice.WSProcessaInterface")
public class WSProcessaImpl implements WSProcessaInterface {
    private RMIProcessa rmi;
    private Manipula manipula;

    public WSProcessaImpl() {
        this.rmi = RMIProcessa.getInstance();
        this.manipula = Manipula.getInstance(); 
    }

    //Efetua leitura a partir da chamada webservice usando RMI
    @Override
    public String leitura(String produto, String categoria) {
        return this.rmi.leitura(produto, categoria);
    }
}
