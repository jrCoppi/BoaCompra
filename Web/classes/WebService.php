<?php
/**
 * Classe para chamads WebService
 */
class WebService
{
   private $client;
   private $conectado;
   private $url;

   /**
    * Construtor da classe
    * @param string $wsdl
    * @param array $options
    */
   public function __construct($wsdl, array $options = array())
   {
      /** 
      *  Verifica se a classe de SOAPClient para conexão com WebService existe, se não existir são desse método
      */
      if (!class_exists('SOAPClient')) {
         $this->setConectado(false);
         die("Não possui a extensão SOAP");    
      }

      /**
       * Habilitamos o tratamento de erro do próprio soap para identificar conexões que falharam
       */
      use_soap_error_handler(true);
      ini_set('default_socket_timeout', '180');
      
      try {
         // Tenta efetuar a conexão se ocorrer algum erro pega no catch
         $this->setUrl($wsdl);
         $this->client = new SoapClient($wsdl, $options);
         $this->setConectado(true);
      } catch(SoapFault $fault) {
         // Seta como sem conexão
         $this->setConectado(false);        
         die("Web Service sem conexão");    
      } catch (Exception $ex){
         $this->setConectado(false);
         die('erro');   
      }
   }

   public function estaConectado()
   {
      return $this->conectado;
   }

   private function setConectado($conectado)
   {
      $this->conectado = $conectado;
      return $this;
   }

   public function getUrl()
   {
      return $this->url;
   }

   private function setUrl($url)
   {
      $this->url = $url;
      return $this;
   }

   /**
    * Aceitar chamadas genéricas de funções
    * @param string $name
    * @param array $arguments
    */
   public function __call($name, $arguments)
   {
      return $this->callSoap($name, $arguments);
   }

   /**
    * Realiza a chamada de função no webservice
    * @param string $function
    * @param array $arguments
    */
   protected function callSoap($function, $arguments)
   {

      $keys = array_keys($arguments);
      if ($keys[0] == 0) {
         $arguments = $arguments[0];
      }

      $retorno = $this->client->__call($function, $arguments);

      // Se o retorno vier em forma de xml efetuamos um tratamento
      if(simplexml_load_string($retorno) != false)
      {

         // Se o retorno for um xml tratamos ele para ser usado na loadxml como UTF-8
         if(mb_detect_encoding($retorno) != 'UTF-8'){
            $retorno = utf8_encode($retorno);
         }

         $retorno = simplexml_load_string($retorno);
         $retorno = json_encode($retorno);
         $retorno = json_decode($retorno, true);
         array_walk_recursive($retorno, array($this, 'correcaoUTF8'));
      }


      return $retorno;
   }


   private function correcaoUTF8(&$valor) {
      if (is_string($valor)) {
         $valor = utf8_decode($valor);
      }
   }

}
?>