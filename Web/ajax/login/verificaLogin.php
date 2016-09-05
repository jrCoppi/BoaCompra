<?php
session_start();

include_once('../../'. $_SESSION['arrCaminhos']['class'].'WSProcessa.php');

$WSProcessa = WSProcessa::getInstance(
   $_SESSION['enderecoWebService']
);

$ds_post    = file_get_contents("php://input");
$objRequest = json_decode($ds_post);

$ds_login   = $objRequest->ds_login;
$ds_senha   = $objRequest->ds_senha;

$idCliente = $WSProcessa->loginCliente($ds_login,$ds_senha);

if($idCliente != -1){
   $_SESSION['idCliente'] = $idCliente;
   $_SESSION['ds_login'] = $ds_login;
}

echo json_encode($idCliente);
?>