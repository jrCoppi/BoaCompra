<?php
/**
 * Realiza conexao com o banco de dados e consultas
 * @package dados
 */
class Conexao
{
   private static $instance = null;
   private $conexao = null;

   private function __construct(){      
      $this->iniciaConexao();
   }

   // Instancia
   public static function getInstance(){

      if( Conexao::$instance == null ){
         Conexao::$instance = new Conexao();
      }

      return Conexao::$instance;
   }

   public function getConexao(){
      if($this->conexao == false){
         $this->iniciaConexao();
      }

      // Verifica se a conexão ainda esta ativa
      if(mysqli_ping($this->conexao) == false){
         $this->iniciaConexao();
      }

      return $this->conexao;
   } 

   // Tenta iniciar a conexao com a base
   private function iniciaConexao(){

      $arrDadosConexao = $_SESSION['arrDadosConexao'];
      $tentativasConexao = 0;
      $conexaoInterna = false;

      //Aumenta time limit
      set_time_limit($tentativasConexao * 40);

      // Tenta conectar 
      while ($tentativasConexao < $arrDadosConexao['nrTentativasConexao'] && $conexaoInterna == false){
         $conexaoInterna = mysql_connect(
            $arrDadosConexao['ipConexao'],
            $arrDadosConexao['hostConexao'],
            $arrDadosConexao['passConexao']
         );   
         $tentativasConexao++;
         mysql_selectdb($arrDadosConexao['baseConexao']);
      }

      if($conexaoInterna == false){
         echo "Erro.";
         die();
      }

      $this->conexao = $conexaoInterna;
   }

   // Realiza e retorna uma consulta
   public function realizaConsulta($consulta){
      $arrResult = null;

      //Funcao usada no array_walk_recursive
      if(function_exists('codificar') == false){
         function codificar(&$item, $key){
            if(is_string($item) == true){
               $item = utf8_encode($item);   
            }
         } 
      }

      $resultSet = $this->executaQuery($consulta);

      if($resultSet == false){
         return null;
      }

      //Joga os valores num array
      while ($row = mysqli_fetch_assoc($resultSet)) {
         $arrResult[] = $row;
      }

      if(is_array($arrResult) == true){
         array_walk_recursive($arrResult, 'codificar');
      }

      return $arrResult;
   }

   public function insert($arrDados,$tabela){

      $strEnunciadoInsert = 'INSERT INTO '. $tabela . ' (';
      $strEnunciadoCampos = ' VALUES (';

      foreach ($arrDados as $key => $campo) {
         $strEnunciadoInsert .= $key . ',';
         $strEnunciadoCampos .= $campo . ',';
      }

      $strEnunciadoInsert = substr_replace($strEnunciadoInsert, ')', -1);
      $strEnunciadoCampos = substr_replace($strEnunciadoCampos, ')', -1);

      $consulta = $strEnunciadoInsert . $strEnunciadoCampos;

      $resultado = $this->executaQuery($consulta);

      return $resultado;
   }

   public function executaQuery($consulta){
      if($this->conexao == false){
         $this->iniciaConexao();
      }
      
      return $this->conexao->mysql_query($consulta);
   }
}
?>