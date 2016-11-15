<?php
session_start();

include_once('../../'. $_SESSION['arrCaminhos']['dados'].'Regiao.php');

$objRegiao = new Regiao();
$arrDados = $objRegiao->getDadosRegiao();

echo json_encode($arrDados);
?>