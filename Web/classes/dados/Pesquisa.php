<?php
include_once('Conexao.php');
/**
 * Clase com as consultas do usuário
 * @package dados
 */
class Pesquisa extends Conexao {

   public function __construct(){}

   public function adicionaPesquisa($id_pessoa){
      
      $arrDados = array();
      $arrDados['id_pessoa'] = $id_pessoa;
      $arrDados['dt_pesquisa'] = "'" . date('Y-m-d H:i:s') . "'";

      return Conexao::getInstance()->insert($arrDados,'pesquisa');
   }

   public function adicionaPesquisaProduto($id_pesquisa,$id_produto){
      
      $arrDados = array();
      $arrDados['id_pesquisa'] = $id_pesquisa;
      $arrDados['id_produto'] = $id_produto;

      return Conexao::getInstance()->insert($arrDados,'produto_pesquisa');
   }
}