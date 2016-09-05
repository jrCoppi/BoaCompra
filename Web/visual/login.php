<?php include_once('cabecalho.php') ?>

    <script src="<?php echo $_SESSION['arrCaminhos']['angular']; ?>login.js"></script>
</head>

<body ng-app="AppLogin" ng-controller="LoginController">

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
                      <div class="panel panel-default">
                          <div class="panel-body panel_bg">

                              <h5 class="negrito">
                                  <b>Efetue seu login:</b>
                              </h5>

                              <div ng-show="sn_exibir_erro_login" ng-init="false" class="text-danger">
                                  Login e senha incorretos, tente novamente
                              </div>

                              <div class="form-inline">
                                  <div class="form-group">
                                      <label for="f1">Login:</label>
                                      <input type="text" class="form-control" ng-model="ds_login">
                                  </div>

                                  <div class="form-group">
                                      <label for="exampleInputPassword1">Senha:</label>
                                      <input type="password" class="form-control" id="ds_senha" ng-model="ds_senha">
                                  </div>

                                  <button type="button" class="btn btn-danger" ng-click="tentaLogar()">Fazer Login</button>
                              </div>

                          </div>
                      </div>
                  </div>
              </div>

          </div>
      </div>

  </div>


</body>
<?php include_once('rodape.php') ?>