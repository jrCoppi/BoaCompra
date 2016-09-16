var AppCadastro = angular.module('AppCadastro', []);


AppCadastro.controller(
    'CadastroController',
    [ '$scope', '$http', 'filterFilter', createController ]
);


/**
 * Constroller da página
 */
function createController( $scope, $http, filterFilter ) {
   $scope.nm_pessoa = '';
   $scope.ds_cpf = '';
   $scope.ds_login_novo = '';
   $scope.ds_senha_novo = '';
   $scope.ds_senha_novo_confirma = '';
   $scope.dt_nascimento = '';


   $scope.cadastrar = function(){
      if($scope.nm_pessoa == '' || $scope.ds_cpf == '' || $scope.ds_login_novo == '' || 
         $scope.ds_senha_novo == ''  || $scope.dt_nascimento == ''){
         alert('Preencha todos os campos.');
         return '';
      }

      if($scope.ds_senha_novo != $scope.ds_senha_novo_confirma){
         alert('A senha deve ser igual a de confirmação.');
      }

      var arrDados = {
         nm_pessoa : $scope.nm_pessoa,
         ds_cpf : $scope.ds_cpf,
         ds_login_novo  : $scope.ds_login_novo,
         dt_nascimento  : $scope.dt_nascimento,
         ds_senha_novo  : $scope.ds_senha_novo
      };

      $http.post(
          '../ajax/login/cadastro.php',
          arrDados
      )
      .success(
          function(idCliente){

            // se data for true, deu erro de login
            if (idCliente == "-1") {
                $scope.sn_exibir_erro_cadastro = true;
                return '';
            }

            document.location = 'pesquisa.php';
          }
      );
   }
}

jQuery('document').ready(function(){
   jQuery("#ds_cpf").mask("999.999.999-99");
   jQuery("#dt_nascimento").mask("99/99/9999");
});