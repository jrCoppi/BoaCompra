<?php
session_start();
include_once($_SESSION['arrCaminhos']['class'].'WSProcessa.php');

$WSProcessa = WSProcessa::getInstance(
   $_SESSION['enderecoWebService']
);

$arrProdutos = $_POST['ds_produto'];

//Se ainda temos o que procurar efetua a leitura
if (!empty($arrProdutos)){

   $arrProdutos = implode(";", $arrProdutos);

   $arrProdutosNovos = $WSProcessa->efetuaLeitura($arrProdutos);

   //Seta os produtos desta pesquisa
   $_SESSION['produtos'] = $arrProdutosNovos;
}


//Vai para a listagem
header('Location: '. $_SESSION['arrCaminhos']['visual'] . 'resultado.php');
?>