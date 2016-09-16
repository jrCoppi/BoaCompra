<?php
session_start();

include_once('../../'. $_SESSION['arrCaminhos']['class'].'WSProcessa.php');

$WSProcessa = WSProcessa::getInstance(
   $_SESSION['enderecoWebService']
);

$ds_post    = file_get_contents("php://input");
$objRequest = json_decode($ds_post);

$ds_login   = $objRequest->ds_login_novo;
$ds_senha   = $objRequest->ds_senha_novo;
$nm_pessoa  = $objRequest->nm_pessoa;
$ds_cpf     = $objRequest->ds_cpf;
$dt_nascimento  = $objRequest->dt_nascimento;

$idCliente = $WSProcessa->efetuaCadastroCliente($nm_pessoa,$ds_cpf,$ds_login,$ds_senha,$dt_nascimento);

if($idCliente != -1){
   $_SESSION['idCliente'] = $idCliente;
   $_SESSION['ds_login'] = $ds_login;
}

echo json_encode($idCliente);
?>