<?php include_once('cabecalho.php') ?>

    <script src="<?php echo $_SESSION['arrCaminhos']['angular']; ?>cadastro.js"></script>
</head>

<body ng-app="AppCadastro" ng-controller="CadastroController">

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
                               <b>Cadastro</b>
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
                                    <input type="text" class="form-control" id="nm_pessoa" ng-model="nm_pessoa" required>
                                 </div>

                                 <div class="col-md-2"></div>

                                 <div class="col-md-4">
                                    <label for="f1">CPF:</label>
                                    <input type="text" class="form-control ui-inputmask" id="ds_cpf" ng-model="ds_cpf" required>
                                 </div>

                                 <div class="col-md-1"></div>
                              </div>

                              <div class="col-md-12"><br></div>

                              <div class="col-md-12">
                                 
                                 <div class="col-md-1"></div>
                                 <div class="col-md-4">
                                    <label for="f1">Data de Nascimento:</label>
                                    <input type="text" class="form-control" id="dt_nascimento" ng-model="dt_nascimento" required>
                                 </div>

                                 <div class="col-md-2"></div>

                                 <div class="col-md-4">
                                    <label for="f1">Login:</label>
                                    <input type="text" class="form-control" id="ds_login_novo" ng-model="ds_login_novo" required>
                                 </div>

                                 <div class="col-md-1"></div>
                                  
                              </div>

                              <div class="col-md-12"><br></div>

                              <div class="col-md-12">
                                 
                                 <div class="col-md-1"></div>
                                 <div class="col-md-4">
                                    <label for="f1">Senha:</label>
                                    <input type="password" class="form-control" id="ds_senha_novo" ng-model="ds_senha_novo" required>
                                 </div>

                                 <div class="col-md-2"></div>

                                 <div class="col-md-4">
                                    <label for="f1">Confirmação da Senha:</label>
                                    <input type="password" class="form-control" id="ds_senha_novo_confirma" ng-model="ds_senha_novo_confirma" required>
                                 </div>

                                 <div class="col-md-1"></div>
                                  
                              </div>

                              <div class="col-md-12"><br></div>

                              <div class="col-md-12">
                                 <div class="col-md-10">
                                 </div>
                                 <div class="col-md-2">
                                    <button type="button" class="btn btn-info" ng-click="cadastrar()">Enviar</button>
                                 </div>
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