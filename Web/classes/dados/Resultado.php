<?php
include_once('Conexao.php');
/**
 * Clase com as consultas do usuário
 * @package dados
 */
class Resultado extends Conexao {

   public function __construct(){}

   public function getResultadoPesquisa($idPesquisa,$idProduto,$mercados){
      $ds_produto = strtolower($ds_produto);

      $sql = 
      "SELECT R.ds_produto_mercado as descricaoProduto, M.ds_mercado as mercado, R.nr_preco as precoProduto ".
      "FROM resultado R ".
      "INNER JOIN mercado M ON (M.id_mercado = R.id_mercado) ".
      #"INNER JOIN produto_pesquisa PP ON (PP.id_pesquisa = R.id_pesquisa) ".
      #INNER JOIN produto P ON (P.id_produto = PP.id_produto)
      "WHERE R.id_pesquisa = " . $idPesquisa . " AND".
      " R.id_produto = ". $idProduto . $mercados;

      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      //se não tem cria um
      if($resultado == null){
         return null;
      }

      return $resultado;
   }

   public function adicionaResultado($id_produto,$id_mercado,$id_pesquisa,$nr_preco,$ds_produto){
      
      $arrDados = array();
      $arrDados['id_produto']  = $id_produto;
      $arrDados['id_mercado']  = $id_mercado;
      $arrDados['id_pesquisa'] = $id_pesquisa;
      $arrDados['nr_preco'] = "'" .$nr_preco. "'";
      $arrDados['ds_produto_mercado'] = "'" .utf8_encode($ds_produto). "'";

      return Conexao::getInstance()->insert($arrDados,'resultado');
   }
}