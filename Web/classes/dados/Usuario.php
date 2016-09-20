<?php
include_once('Conexao.php');
/**
 * Clase com as consultas do usuário
 * @package dados
 */
class Usuario 
{
   public function __construct(){}

   public function verificaLoginUsuario($ds_login, $ds_senha){
      $sql = 'SELECT id_pessoa FROM pessoa WHERE ds_login = ' . $ds_login . ' AND ds_senha = ' . $ds_senha;

      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      if($resultado == null){
         return null;
      }

      return $resultado[0]['id_pessoa'];
   }
}