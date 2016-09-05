<?php
include_once('WebService.php');

/**
 * Classe para comunicação com WS
 */
class WSProcessa extends WebService
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

      if (WSProcessa::$instance == null) {
         WSProcessa::$instance = new WSProcessa(
            $url,
            array(
               'encoding' => $encoding
            )
         );
      }
      return WSProcessa::$instance;
   }


   /**
    * Função WS para cadastrar clientes
    */
   public function efetuaCadastroCliente($nome = '', $cpf = '', $login = '', $senha = '')
   {
      return $this->cadastraCliente(array(
         'nome'  => $nome,
         'cpf'   => $cpf,
         'login' => $login,
         'senha' => $senha
      ));
   }

   /**
    * Função WS para verificar login cliente
    */
   public function loginCliente($login = '', $senha = '')
   {
      return $this->verificaLoginCliente(array(
         'login' => $login,
         'senha' => $senha
      ));
   }

   /**
    * Função WS para efetuar leitura
    */
   public function efetuaLeitura($produto = '')
   {
      $dadosWs = $this->leitura(array(
         'produto' => $produto
      ));

      $arrProdutos = array();
      //Formata para usar na tela
      foreach ($dadosWs as $key => $dado) {

         if($dado['string'] != null){
            $indexProduto = $dado['string'];
            $arrProdutos[$indexProduto]['arrMercados'] = $dado['list']['Dados.ResultadoBusca'];
            break;
         }

         foreach ($dado as $keyProd => $listaProdutos) {
            $indexProduto = $listaProdutos['string'];
            $arrProdutos[$indexProduto]['arrMercados'] = $listaProdutos['list']['Dados.ResultadoBusca'];
         }
      }
      return $arrProdutos;
   }

   /**
    * Função WS para efetuar leitura
    */
   public function retornaHistoricoCliente($login = '')
   {
      return $this->getHistoricoCliente(array(
         'login' => $login
      ));
   }

   /**
    * Função WS para gravar historico
    */
   public function gravaHistoricoCliente($produto = '',$loginCliente = '')
   {
      return $this->setHistoricoCliente(array(
         'produto' => $produto,
         'login' => $loginCliente
      ));
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
