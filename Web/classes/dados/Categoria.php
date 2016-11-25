<?php
include_once('Conexao.php');
/**
 * Clase com as consultas do usuário
 * @package dados
 */
class Categoria extends Conexao {

   public function __construct(){}

   public function getDadosCategoria(){

      $sql = "SELECT id_categoria,ds_categoria FROM categoria";

      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      return $resultado;
   }
}