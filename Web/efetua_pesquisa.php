<?php
session_start();
include_once($_SESSION['arrCaminhos']['class'].'WSProcessa.php');

$WSProcessa = WSProcessa::getInstance(
   $_SESSION['enderecoWebService']
);

$arrProdutos = $_POST['ds_produto'];

//Inicia variavel de retorno para por na sessão
$arrRetorno = array();
$_SESSION['arrProdutosAtuais'] = $arrRetorno;

//Verifica se ja foi pesquisado nesta sessão
$produtosJaExistentes = $_SESSION['arrProdutos'];
foreach ($produtosJaExistentes as $produto => $dados) {
   
   foreach ($arrProdutos as $key => $produtoNovo) {

      //Se o array ja foi pesquisado não faz denovo e joga no retorno
      if($produtoNovo == $produto){
         unset($arrProdutos[$key]);
         $arrRetorno[$produto] = $produtosJaExistentes[$produto];
      }

      if($produtoNovo == ""){
         unset($arrProdutos[$key]);
      }
   }
}

$_SESSION['arrProdutosAtuais'] = $arrRetorno;

//Salva no histórico
foreach ($arrProdutos as $key => $produtoNovo) {
   $WSProcessa->gravaHistoricoCliente($produtoNovo,$_SESSION['ds_login']);
}


//Se ainda temos o que procurar efetua a leitura
if (!empty($arrProdutos)){

   $arrProdutos = implode(";", $arrProdutos);
   $arrProdutosNovos = $WSProcessa->efetuaLeitura($arrProdutos);

   $_SESSION['arrProdutos'] = array_merge($_SESSION['arrProdutos'],$arrProdutosNovos);
   
   //Seta os produtos desta pesquisa
   $_SESSION['arrProdutosAtuais'] = array_merge($arrRetorno,$arrProdutosNovos);
}


//Vai para a listagem
header('Location: '. $_SESSION['arrCaminhos']['visual'] . 'resultado.php');
?>