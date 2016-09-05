var AppResultado = angular.module('AppResultado', []);


AppResultado.controller(
    'ResultadoController',
    [ '$scope', '$http', 'filterFilter', createController ]
);


/**
 * Constroller da página
 */
function createController( $scope, $http, filterFilter ) {
   $scope.ds_produto = '';

   $scope.realizaPesquisa = function(){
      $http.post(
          '../ajax/resultado/getDadosPesquisa.php'
      )
      .success(
          function(data){

            $scope.arrProdutos = data;
          }
      );
   }

   $scope.novaPesquisa = function(){
      document.location = "pesquisa.php";
   }

   $scope.realizaPesquisa();
}