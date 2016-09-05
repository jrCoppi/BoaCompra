<?php
session_start();

//Inclui as configurações inicial
include_once('configuracoes.php');

header('Location: '. $_SESSION['arrCaminhos']['visual'] . 'inicial.php');
?>