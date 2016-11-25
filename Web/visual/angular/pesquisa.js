var AppPesquisa = angular.module('AppPesquisa', []);


AppPesquisa.controller(
    'PesquisaController',
    [ '$scope', '$http', 'filterFilter', createController ]
);


/**
 * Constroller da página
 */
function createController( $scope, $http, filterFilter ) {
   $scope.ds_produto = '';
   $scope.arrListaRegiao = null;
   $scope.arrListaCategoria = null;

   $scope.buscaRegioes = function(){
      $http.post(
          '../ajax/pesquisa/getRegioes.php'
      )
      .success(
          function(data){
            $scope.arrListaRegiao = data;
          }
      );
   }


   $scope.buscaCategorias = function(){
      $http.post(
          '../ajax/pesquisa/getCategorias.php'
      )
      .success(
          function(data){
            $scope.arrListaCategoria = data;
          }
      );
   }

   $scope.buscaRegioes();
   $scope.buscaCategorias();
}


function adicionaProduto(){
   var listContainer = $('#container');

   $('#incluir').remove()

   listContainer.append(
      '<div class="form-inline"> '+
      '   <div class="form-group"> '+
      '      <label for="f1">Produto:&nbsp;&nbsp;</label> '+
      '      <input type="text" class="form-control" name="ds_produto[]" id="ds_produto" ng-model="ds_produto" > '+
      '   </div> '+
      '   <div class="form-group"> '+
      '      <label for="f1">&nbsp;&nbsp;Categoria:&nbsp;&nbsp;</label> '+
      '      <select id="id_categoria" name="id_categoria[]" class="form-control" title="Região" ng-model="id_categoria" required> '+
      '         <option value="-1">Selecione</option> '+
      '         <option value="1">Refrigerante</option> '+
      '         <option value="2">Salgados</option>'+
      '         <option value="3">Bolacha</option> '+
      '         <option value="4">Bebidas</option> '+
      '      </select> '+
      '   </div> '+
      '   <button type="button"  id="incluir" class="btn btn-link glyphicon glyphicon-plus" onClick="adicionaProduto()"></button> '+
      '</div> '
   );
}

function limpaProdutos(){
   $( ".form-control" ).each(function() {
      $( this ).val("");
    });
}