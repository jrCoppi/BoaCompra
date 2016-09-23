<?php
session_start();

include_once('../../'. $_SESSION['arrCaminhos']['dados'].'Usuario.php');


$ds_post    = file_get_contents("php://input");
$objRequest = json_decode($ds_post);

$ds_login   = $objRequest->ds_login_novo;
$ds_senha   = $objRequest->ds_senha_novo;
$nm_pessoa  = $objRequest->nm_pessoa;
$ds_cpf     = $objRequest->ds_cpf;
$dt_nascimento  = $objRequest->dt_nascimento;

$objUsuario = new Usuario();

$arrDados = array(
   'nm_pessoa' => '"' . utf8_decode($nm_pessoa) . '"',
   'ds_cpf' => '"' . utf8_decode($ds_cpf) . '"',
   'ds_login' => '"' . utf8_decode($ds_login) . '"',
   'ds_senha' => 'md5("' . utf8_decode($ds_senha) . '")',
   'dt_nascimento' => '"' . implode("-",array_reverse(explode("/",$dt_nascimento))) . '"'
);


$idCliente = $objUsuario->efetuaCadastroCliente($arrDados);

if($idCliente != -1){
   $_SESSION['idCliente'] = $idCliente;
   $_SESSION['ds_login'] = $ds_login;
}

echo json_encode($idCliente);
?>