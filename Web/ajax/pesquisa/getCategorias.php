<?php
session_start();

include_once('../../'. $_SESSION['arrCaminhos']['dados'].'Categoria.php');

$objCategoria = new Categoria();
$arrDados = $objCategoria->getDadosCategoria();

echo json_encode($arrDados);
?>