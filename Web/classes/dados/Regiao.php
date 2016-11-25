<?php
include_once('Conexao.php');
/**
 * Clase com as consultas do usuário
 * @package dados
 */
class Regiao extends Conexao {

   public function __construct(){}

   public function getDadosRegiao(){

      $sql = "SELECT id_regiao,ds_regiao FROM regiao";

      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      return $resultado;
   }

   public function getMercadosFromRegiao($id_regiao){

      $sql = "SELECT id_mercado FROM mercado where id_regiao = ".$id_regiao;

      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      return $resultado;
   }
}