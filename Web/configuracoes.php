<?php
date_default_timezone_set('America/Sao_Paulo');
$arrCaminhos = array();
$arrDadosConexao = array();

//var_dump();

// Incia as variaveis dos caminhos
// Visual
$arrCaminhos['visual']  = 'visual/';
$arrCaminhos['css']     = 'css/';
$arrCaminhos['js']      = 'js/';
$arrCaminhos['img']     = 'img/';
$arrCaminhos['angular'] = 'angular/';
$arrCaminhos['font']    = 'fonts/';

//Ajax
$arrCaminhos['ajax']    = 'ajax/';

//Classes
$arrCaminhos['class']   = 'classes/';
$arrCaminhos['dados']   = $arrCaminhos['class'] . 'dados/';

//Joga ambos na Session
$_SESSION['arrCaminhos']     = $arrCaminhos;
$_SESSION['titulo']  = 'Boa Compra';

$_SESSION['enderecoWebService'] = 'http://127.0.0.1:9876/webservice?wsdl';
$_SESSION['enderecoWebServicePlanilha'] = 'http://localhost:90/BoaCompraPlanilha/BoaCompraListaWS.asmx';
$_SESSION['arrProdutos'] = array();
$_SESSION['arrProdutosAtuais'] = array();

$_SESSION['arrDadosConexao'] = array();
$_SESSION['arrDadosConexao']['ipConexao'] = 'localhost';
$_SESSION['arrDadosConexao']['hostConexao'] = 'root';
$_SESSION['arrDadosConexao']['passConexao'] = 'root';
$_SESSION['arrDadosConexao']['baseConexao'] = 'processos';
$_SESSION['arrDadosConexao']['nrTentativasConexao'] = 1;
?>