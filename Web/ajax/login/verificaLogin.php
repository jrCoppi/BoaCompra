<?php
session_start();

include_once('../../'. $_SESSION['arrCaminhos']['dados'].'Usuario.php');

$ds_post    = file_get_contents("php://input");
$objRequest = json_decode($ds_post);

$ds_login   = $objRequest->ds_login;
$ds_senha   = $objRequest->ds_senha;

$objUsuario = new Usuario();
$idCliente = $objUsuario->verificaLoginUsuario($ds_login,$ds_senha);

if($idCliente != null){
   $_SESSION['idCliente'] = $idCliente;
   $_SESSION['ds_login'] = $ds_login;
}

echo json_encode($idCliente);
?>