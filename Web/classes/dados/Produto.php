<?php
include_once('Conexao.php');
/**
 * Clase com as consultas do usuário
 * @package dados
 */
class Produto extends Conexao {

   public function __construct(){}

   public function verificaCategoriaProduto($ds_produto){
     /* $sql = "SELECT id_categoria FROM categorias WHERE ds_login = '" . $ds_login . "' AND ds_senha = md5('" . $ds_senha . "');";

      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      if($resultado == null){
         return null;
      }

      return $resultado[0]['id_pessoa'];*/
   }

   public function verificaProdutoPesquisado($ds_produto){


      $codigoProduto = $this->getCodigoProduto($ds_produto);

      //Não tem, cadastra e retorna nulo
      if($codigoProduto == null){
         $this->adicionaProduto($ds_produto);
         return null;
      }

      //Verifica se foi usado numa pesquisa no dia 

      $sql = "SELECT id_pesquisa FROM produto_pesquisa WHERE id_produto = '" . $codigoProduto . "'";
      
      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      if($resultado == null){
         return null;
      }

      return $resultado[0]['id_pesquisa'];
   }


   /**
    * Busca o codigo do produto a ser buscado
    * @param type $ds_produto 
    * @return type
    */
   public function getCodigoProduto($ds_produto){
      $ds_produto = strtolower($ds_produto);

      $sql = "SELECT id_produto FROM produto WHERE ds_produto = '" . utf8_decode($ds_produto) . "'";

      $resultado = Conexao::getInstance()->realizaConsulta($sql);

      //se não tem cria um
      if($resultado == null){
         return null;
      }

      return $resultado[0]['id_produto'];
   }

   /**
    * Adiciona um novo produto
    * @param type $ds_produto 
    * @return type
    */
   public function adicionaProduto($ds_produto){
      //ve se não tem ja cadastrado
      $codigoProduto = $this->getCodigoProduto($ds_produto);

      //Não tem, cadastra e retorna nulo
      if($codigoProduto != null){
         return $codigoProduto;
      }
      
      $arrDados = array();
      $arrDados['ds_produto'] = '"'.$ds_produto.'"';

      return Conexao::getInstance()->insert($arrDados,'produto');
   }
}