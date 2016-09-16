<?php include_once('cabecalho.php') ?>

    <script src="<?php echo $_SESSION['arrCaminhos']['angular']; ?>inicial.js"></script>
</head>

<body ng-app="AppLogin" ng-controller="LoginController">

   <div class="container center-block">  
      <div class="row">

         <div class="row"><br></div>
         <div class="col-sm-12">

            <!-- HEADER -->
            <?php include_once('header.php') ?>
            <!-- HEADER -->

            <div class="row"><br></div>

            <div class="content" style="text-align:center;">
                <h1>Sistema Boa Compra</h1>
                <img src="img/logo.png"></img>
                <br>
                <h3>Sua compra com economia e eficiência</h3>
            </div>

            <div class="row"><br></div>
            <div class="row"><br></div>

            <!-- LOGIN -->
            <div class="row panel-body login_bg" style="text-align:center;">
               <b>Novo no site?&nbsp;&nbsp;
               <a type="button" class="btn btn-primary" href="cadastro.php">Cadastre-se agora</a>
               </b> 
            </div>

            <!-- LOGIN -->

            <!-- RODAPE -->
            

         </div>

      </div>
   </div>
   <?php include_once('rodape.php') ?>
</body>

</html>



    
