<?php 
session_start();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>
            <?php echo $_SESSION['titulo']; ?>
        </title>

        <script src="<?php echo $_SESSION['arrCaminhos']['js']; ?>jquery-1.11.3.min.js"></script>

        <!-- bootstrap -->
        <link rel="stylesheet" href="<?php echo $_SESSION['arrCaminhos']['css']; ?>bootstrap.min.css">
        <link rel="stylesheet" href="<?php echo $_SESSION['arrCaminhos']['css']; ?>bootstrap-theme.min.css">

        <script src="<?php echo $_SESSION['arrCaminhos']['js']; ?>bootstrap.min.js"></script>
        <script src="<?php echo $_SESSION['arrCaminhos']['js']; ?>angular.min.js"></script>
        <script src="<?php echo $_SESSION['arrCaminhos']['js']; ?>jquery.maskedinput.js"></script>

        <!-- pagina -->
        <link rel="stylesheet" href="<?php echo $_SESSION['arrCaminhos']['css']; ?>estilo.css">