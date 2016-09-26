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
}


function adicionaProduto(){
   var listContainer = $('#container');

   $('#incluir').remove()

   listContainer.append('<div class="form-inline"> '+
                         '   <div class="form-group"> '+
                         '      <label for="f1">Produto:</label> '+
                         '      <input type="text" class="form-control" id="ds_produto" name="ds_produto[]" ng-model="ds_produto" > '+
                         '   </div> '+
                         '   <button type="button"  id="incluir" class="btn btn-link glyphicon glyphicon-plus" onClick="adicionaProduto()"></button> '+
                        ' </div>');
}

function limpaProdutos(){
   $( ".form-control" ).each(function() {
      $( this ).val("");
    }); 
}