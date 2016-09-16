<?php include_once('cabecalho.php') ?>

    <script src="<?php echo $_SESSION['arrCaminhos']['angular']; ?>faleconosco.js"></script>
</head>

<body ng-app="AppContato" ng-controller="ContatoController">

  <div class="container">
      <div class="center-block">

          <div class="row">

               <div class="row"><br></div>
               <div class="row"><br></div>


              <div class="col-sm-12">

               <!-- HEADER -->
               <?php include_once('header.php') ?>

               <div class="row">
                   <div class="panel">
                       <div class="panel-body">

                           <h5 class="negrito">
                               <b>Fale Conosco</b>
                           </h5>


                           <div ng-show="sn_exibir_erro_cadastro" ng-init="false" class="text-danger" style="display:none;">
                               Login ja existente, tente novamente
                           </div>

                           <form id="form" name="form" method="post" 
                              action="../efetua_cadastro.php" enctype="application/x-www-form-urlencoded" novalidate>

                              <div class="col-md-12">
                                 
                                 <div class="col-md-1"></div>
                                 <div class="col-md-4">
                                    <label for="f1">Nome:</label>
                                    <input type="text" class="form-control" id="nm_pessoa" ng-model="nm_pessoa" >
                                 </div>

                                 <div class="col-md-7"></div>
                              </div>

                              <div class="col-md-12"><br></div>

                              <div class="col-md-12">
                                 
                                 <div class="col-md-1"></div>
                                 <div class="col-md-4">
                                    <label for="f1">Email:</label>
                                    <input type="text" class="form-control" id="ds_login_novo" ng-model="ds_login_novo">
                                 </div>

                                 <div class="col-md-7"></div>
                              </div>


                              <div class="col-md-12"><br></div>

                              <div class="col-md-12">
                                 
                                 <div class="col-md-1"></div>
                                 <div class="col-md-4">
                                    <label for="f1">Mensagem:</label>
                                     <textarea class="form-control" rows="3"></textarea>
                                 </div>

                                 <div class="col-md-7"></div>
                              </div>

                              <div class="col-md-12"><br></div>

                              <div class="col-md-12">
                                 <div class="col-md-1"></div>
                                 <div class="col-md-4">
                                    <button type="button" class="btn btn-info" ng-click="cadastrar()">Enviar</button>
                                 </div>
                                 <div class="col-md-7"></div>
                              </div>

                           </form>

                       </div>
                   </div>
               </div>
           </div>
      </div>
   </div>
</body>
<?php include_once('rodape.php') ?>