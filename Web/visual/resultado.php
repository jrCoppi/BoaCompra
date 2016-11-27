<?php include_once('cabecalho.php');
?>
    <script src="<?php echo $_SESSION['arrCaminhos']['angular']; ?>resultado.js"></script>
    <!--link href="<?php echo $_SESSION['arrCaminhos']['css']; ?>bootstrap-table.min.css" rel="stylesheet"/>
    <script src="<?php echo $_SESSION['arrCaminhos']['js']; ?>jquery.min.js"></script>
    <script src="<?php echo $_SESSION['arrCaminhos']['js']; ?>bootstrap-table.min.js"></script-->
</head>
<body ng-app="AppResultado" ng-controller="ResultadoController">


   <input type="hidden" ng-model="arrProdutos" value="<?php echo $arrProdutos; ?>" ng-init="arrProdutos = <?php echo $arrProdutos; ?>;" />

   <div class="container">
      <div class="center-block">
         <div class="row">

            <div class="row"><br></div>
            <div class="row"><br></div>


            <div class="col-sm-12">

               <div class="row page-header">
                   <p>
                       <h3 class="text-left negrito" style="font-family: 'Droid Sans',sans-serif;font-style: bold;">
                           <b>Sistema Boa Compra</b>
                       </h3>
                   </p>
               </div>

               <div class="row"><br></div>

               <div class="row">
                    <h4 class="text-left negrito" style="font-family: 'Droid Sans',sans-serif;font-style: bold;">
                        <b>Resultado da Pesquisa:</b>
                    </h4>

                    <div class="row"><br></div>

                    <table class="table table-bordered table-striped" ng-repeat="(descricao,produto) in arrProdutos" caption="a">
                        <caption> <b><span style="color:red;">{{descricao}}</span></b></caption>
                        <thead>
                          <tr>
                            <td style="width: 20%;">
                              <a href="" ng-click="sortType = 'mercado'; sortReverse = !sortReverse">Mercado</a>
                              <span ng-show="sortType == 'mercado'" class="fa fa-caret-up"></span>
                              <span ng-show="sortType == 'mercado'" class="fa fa-caret-down"></span>
                            </td>
                            <td style="width: 60%;">
                              <a href="" ng-click="sortType = 'descricaoProduto'; sortReverse = !sortReverse">Descrição Produto</a>
                              <span ng-show="sortType == 'mercado'" class="fa fa-caret-up"></span>
                              <span ng-show="sortType == 'mercado'" class="fa fa-caret-down"></span>
                            </td>
                            <td  style="width: 10%;">
                              <a href="" ng-click="sortType = 'precoProduto'; sortReverse = !sortReverse">Preço</a>
                              <span ng-show="sortType == 'mercado'" class="fa fa-caret-up"></span>
                              <span ng-show="sortType == 'mercado'" class="fa fa-caret-down"></span>
                            </td>
                            <td  style="width: 10%;">
                              <a href="" ng-click="sortType = 'pontuacao'; sortReverse = !sortReverse">Acerto</a>
                              <span ng-show="sortType == 'mercado'" class="fa fa-caret-up"></span>
                              <span ng-show="sortType == 'mercado'" class="fa fa-caret-down"></span>
                            </td>
                          </tr>
                        </thead>
                        
                        <tbody>
                          <tr ng-repeat="mercados in produto.arrMercados| orderBy:sortType:sortReverse">
                            <td>{{ mercados.mercado }}</td>
                            <td>{{ mercados.descricaoProduto }}</td>
                            <td>R$ {{ mercados.precoProduto }}</td>
                            <td class="progress-bar" role="progressbar" aria-valuenow="{{ mercados.pontuacao }}" aria-valuemin="0" aria-valuemax="100" style="width: {{ mercados.pontuacao }}%;">{{ mercados.pontuacao }}%</td>
                          </tr>
                        </tbody>
                        
                     </table>

                    <h4 class="text-left negrito" style="font-family: 'Droid Sans',sans-serif;font-style: bold;">
                      <a href="#" ng-click="novaPesquisa()" class="hover">
                        Realizar nova pesquisa
                      </a>
                    </h4>
               </div>

            </div>
         </div>
      </div>
   </div>



   <!--table data-toggle="table" data-url="https://api.github.com/users/wenzhixin/repos?type=owner&sort=full_name&direction=asc&per_page=100&page=1" data-sort-name="stargazers_count" data-sort-order="desc">
      <thead> 
         <tr> 
            <th data-field="name" data-sortable="true"> Name </th> 
            <th data-field="stargazers_count" data-sortable="true"> Stars </th> 
            <th data-field="forks_count" data-sortable="true"> Forks </th> 
            <th data-field="description" data-sortable="true"> Description </th> 
         </tr>
      </thead>
   </table-->
</body>
<?php include_once('rodape.php') ?>