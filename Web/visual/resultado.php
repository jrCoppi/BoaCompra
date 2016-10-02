<?php include_once('cabecalho.php');
?>
    <script src="<?php echo $_SESSION['arrCaminhos']['angular']; ?>resultado.js"></script>
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

                    <div class="row col-md-12" >
                      
                      <div class="col-md-6 text-center" ng-repeat="(descricao,produto) in arrProdutos">
                         
                            <div style="font-family: 'Droid Sans',sans-serif;font-style: bold;color: red;">
                              <b>Produto: {{descricao}}</b>
                            </div>

                            <!-- div ng-repeat="mercados in produto.arrMercados"-->
                               
                               
                                  <div class="row">
                                    <b>Mercado:</b> {{produto.arrMercados.mercado}}<br>
                                    <b>Descrição Produto:</b> {{produto.arrMercados.descricaoProduto}}<br>
                                    <b>Preço:</b> R$ {{produto.arrMercados.precoProduto}}
                                  </div>
                               
                               <br>
                            <!--/div-->
                      </div>

                    </div>


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
</body>
<?php include_once('rodape.php') ?>