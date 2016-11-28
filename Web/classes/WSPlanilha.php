<?php
include_once('WebService.php');

/**
 * Classe para comunicação com WS
 */
class WSPlanilha extends WebService
{
   private static $instance = null;
   private $chave;

   /**
    * Construtor da classe
    * @param string $wsdl
    * @param string $chave
    * @param array $options
    */
   public function __construct($wsdl, array $options = array())
   {
      try{
         parent::__construct($wsdl, $options);
      }  catch(Exception $e) {
         die($e->getMessage());
      }
   }

   /**
    * Retorna instância da classe
    * @param String $url
    * @param String $encoding
    * @return type
    */
   public static function getInstance($url = null, $encoding = 'UTF-8')
   {

      if (WSPlanilha::$instance == null) {
         WSPlanilha::$instance = new WSPlanilha(
            $url,
            array(
               'encoding' => $encoding
            )
         );
      }
      return WSPlanilha::$instance;
   }

   /**
    * Função WS para efetuar leitura
    */
   public function planilha($arquivo = '')
   {
      //$byte_array = unpack('C*', $arquivo);


      $dadosWs = $this->InterpretarPlanilhaExcel();

      var_dump($dadosWs);
      die();

      return $arrProdutos;
   }

   /**
    * Aceitar chamadas genéricas de funções,
    * inserindo como padrão em todas as chamadas o campo "chave"
    * @param string $name
    * @param array $arguments
    */
   public function __call($name, $arguments)
   {
      return $this->callSoap($name, $arguments);
   }
}
