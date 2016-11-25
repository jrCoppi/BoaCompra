<?php include_once('cabecalho.php')
#Buscar como criar autocomplete com o histórico, ds_produto[] ??
?>
    <script src="<?php echo $_SESSION['arrCaminhos']['angular']; ?>pesquisa.js"></script>
</head>
<body ng-app="AppPesquisa" ng-controller="PesquisaController">

  <div class="container">
      <div class="center-block">

          <div class="row">

               <div class="row"><br></div>
               <div class="row"><br></div>


              <div class="col-sm-12">

                 <!-- HEADER -->
                 <?php include_once('header.php') ?>
                 <!-- HEADER -->

                 <div class="row"><br></div>

                 <div class="row">
                      <h3 class="text-left negrito" style="font-family: 'Droid Sans',sans-serif;font-style: bold;">
                          <b>Pesquisar:</b>
                      </h3>

                      <div class="row"><br></div>


                      <form id="form" name="form" method="post"
                         action="../efetua_pesquisa.php" enctype="application/x-www-form-urlencoded" novalidate>

                         <div id="container">
                            <div class="form-inline">
                               <div class="form-group">
                                  <label for="f1">Região:&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                  <select id="id_regiao" name="id_regiao" class="form-control" title="Região" ng-model="id_regiao" required>

                                     <option value="">Selecione</option>
                                     <option ng-repeat="regiao in arrListaRegiao"  value="{{regiao.id_regiao}}">
                                       {{regiao.ds_regiao}}
                                     </option>
                                  </select>
                               </div>
                            </div>

                            <div><br></div>

                            <div class="form-inline">
                               <div class="form-group">
                                  <label for="f1">Produto:&nbsp;&nbsp;</label>
                                  <input type="text" class="form-control" name="ds_produto[]" id="ds_produto" ng-model="ds_produto" >
                               </div>

                               <div class="form-group">
                                  <label for="f1">&nbsp;&nbsp;Categoria:&nbsp;&nbsp;</label>
                                  <select id="id_categoria" name="id_categoria[]" class="form-control" title="Região" ng-model="id_categoria" required>

                                     <option value="-1">Selecione</option>
                                     <option ng-repeat="categoria in arrListaCategoria"  value="{{categoria.id_categoria}}">
                                       {{categoria.ds_categoria}}
                                     </option>
                                  </select>
                               </div>

                               <button type="button"  id="incluir" class="btn btn-link glyphicon glyphicon-plus" onClick="adicionaProduto()"></button>
                            </div>
                         </div>

                         <div class="row"><br></div>

                         <div class="form-inline">
                           <button type="submmit" class="btn btn-success">Enviar</button>
                           <button type="button" class="btn btn-danger" onClick="limpaProdutos()">Limpar</button>
                         </div>

                      </form>

                      <div class="row"><br></div>

                 </div>
              </div>

          </div>
      </div>

  </div>


</body>
<?php include_once('rodape.php') ?>