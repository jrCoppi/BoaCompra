<?php
include_once('Conexao.php');
/**
 * Clase com as consultas do usuário
 * @package dados
 */
class Usuario extends Conexao {

   public function __construct(){}

   public function verificaLoginUsuario($ds_login, $ds_senha){
      $sql = "SELECT id_pessoa FROM pessoa WHERE ds_login = '" . $ds_login . "' AND ds_senha = md5('" . $ds_senha . "');";

      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      if($resultado == null){
         return null;
      }

      return $resultado[0]['id_pessoa'];
   }

   public function efetuaCadastroCliente($arrDados){

      $novoId = Conexao::getInstance()->insert($arrDados, 'pessoa');
      
      return $novoId;
   }
}