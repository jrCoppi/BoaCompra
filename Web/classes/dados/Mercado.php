<?php
include_once('Conexao.php');
/**
 * Clase com as consultas do usuário
 * @package dados
 */
class Mercado extends Conexao {

   public function __construct(){}

   public function getCodigoMercado($ds_mercado){
      
      $ds_produto = strtolower($ds_produto);

      $sql = "SELECT id_mercado FROM mercado WHERE ds_mercado = '" . utf8_decode($ds_mercado) . "'";

      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      return $resultado[0]['id_mercado'];
   }
}