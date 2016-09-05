<?php
session_start();

include_once('../../'. $_SESSION['arrCaminhos']['class'].'WSProcessa.php');

$WSProcessa = WSProcessa::getInstance(
   $_SESSION['enderecoWebService']
);

$arrHistoricoCliente = $WSProcessa->retornaHistoricoCliente($_SESSION['ds_login']);

$arrHistoricoCliente = explode(";", $arrHistoricoCliente);

echo json_encode($arrHistoricoCliente);
?>