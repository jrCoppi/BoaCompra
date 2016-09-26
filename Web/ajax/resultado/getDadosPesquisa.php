<?php
session_start();

$arrRetorno = $_SESSION['produtos'];


function correcaoUTF8(&$valor) {
   if (is_string($valor)) {
      $valor = utf8_encode($valor);
   }
}
array_walk_recursive($arrRetorno, 'correcaoUTF8');

$produtos = json_encode($arrRetorno);

echo $produtos;
?>