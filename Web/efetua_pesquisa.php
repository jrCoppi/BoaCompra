<?php
session_start();
include_once($_SESSION['arrCaminhos']['class'].'WSProcessa.php');
include_once($_SESSION['arrCaminhos']['dados'].'Mercado.php');
include_once($_SESSION['arrCaminhos']['dados'].'Produto.php');
include_once($_SESSION['arrCaminhos']['dados'].'Pesquisa.php');
include_once($_SESSION['arrCaminhos']['dados'].'Resultado.php');

$WSProcessa = WSProcessa::getInstance(
   $_SESSION['enderecoWebService']
);



$objMercado   = new Mercado();
$objProduto   = new Produto();
$objPesquisa  = new Pesquisa();
$objResultado = new Resultado();

$arrProdutos = $_POST['ds_produto'];

//Se ainda temos o que procurar efetua a leitura
if (!empty($arrProdutos)){
   $arrProdutosPesquisa = array();
   $arrProdutosNovos = array();

   $arrProdutosCopia = $arrProdutos;
   //Para cada um dos produtos processados verifica se já tem em base e tbm cadastrada
   foreach ($arrProdutosCopia as $chave => $produto) {

      if(($produto === null) || ($produto === '')){
         continue;
      }

      //Busca Id's
      $idPesquisa = $objProduto->verificaProdutoPesquisado($produto);
      $idProduto  = $objProduto->getCodigoProduto($produto);

      //Se encontrou uma pesquisa busca os dados de la
      if($idPesquisa != null){

         $arrDadosPesquisa = $objResultado->getResultadoPesquisa($idPesquisa,$idProduto);
         $arrProdutosPesquisa[$produto]['arrMercados'] = $arrDadosPesquisa;
         unset($arrProdutos[$chave]);
      }
   }

   if (!empty($arrProdutos)){
      $arrProdutos = implode(";", $arrProdutos);

      $arrProdutosNovos = $WSProcessa->efetuaLeitura($arrProdutos);

      //Cria uma nova pesquisa
      $id_pesquisa = $objPesquisa->adicionaPesquisa($_SESSION['idCliente']);

      foreach ($arrProdutosNovos as $produto => $produtoNovo) {
         
         //Primeiro adiciona o produto novo (se tiver)
         $id_produto = $objProduto->adicionaProduto($produto);

         $objPesquisa->adicionaPesquisaProduto($id_pesquisa,$id_produto);

         foreach ($produtoNovo['arrMercados'] as $key => $mercados) {

            //Não achou preço
            if($mercados['precoProduto'] == null){
               continue;
            }

            $id_mercado = $objMercado->getCodigoMercado($mercados['mercado']);

            //Adiciona o resultado da pesquisa
            $objResultado->adicionaResultado(
               $id_produto,
               $id_mercado,
               $id_pesquisa,
               $mercados['precoProduto'],
               $mercados['descricaoProduto']
            );
         }
      }
   }

   $arrProdutosNovos = array_merge($arrProdutosPesquisa,$arrProdutosNovos);
   //Inserir os novos produtos e juntar com os ja vistod


   //Seta os produtos desta pesquisa
   $_SESSION['produtos'] = $arrProdutosNovos;
}


//Vai para a listagem
header('Location: '. $_SESSION['arrCaminhos']['visual'] . 'resultado.php');
?>