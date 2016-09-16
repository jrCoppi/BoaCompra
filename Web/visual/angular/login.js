var AppLogin = angular.module('AppLogin', []);


AppLogin.controller(
    'LoginController',
    [ '$scope', '$http', 'filterFilter', createController ]
);


/**
 * Constroller da página
 */
function createController( $scope, $http, filterFilter ) {
   $scope.ds_login = '';
   $scope.ds_senha = '';
   $scope.nm_pessoa = '';
   $scope.ds_cpf = '';
   $scope.ds_login_novo = '';
   $scope.ds_senha_novo = '';

   $scope.tentaLogar = function(){
      if($scope.ds_login == '' || $scope.ds_senha == ''){
         alert('Preencha todos os campos.');
         return '';
      }

      var arrDados = {
          ds_login : $scope.ds_login,
          ds_senha : $scope.ds_senha
      };

      $http.post(
          '../ajax/login/verificaLogin.php',
          arrDados
      )
      .success(
          function(idCliente){

            // se data for true, deu erro de login
            if (idCliente == "-1") {
                $scope.sn_exibir_erro_login = true;
                return '';
            }

            document.location = 'pesquisa.php';
          }
      );
   }
}