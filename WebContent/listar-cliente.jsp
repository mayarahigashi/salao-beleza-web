<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-tap-highlight" content="no">
<meta name="description"
	content="Materialize is a Material Design Admin Template,It's modern, responsive and based on Material Design by Google. ">
<meta name="keywords"
	content="materialize, admin template, dashboard template, flat admin template, responsive admin template,">
<title>Clientes</title>

<!-- Favicons-->
<link rel="icon" href="images/favicon/favicon-32x32.png" sizes="32x32">
	<!-- Favicons-->
<link rel="apple-touch-icon-precomposed"
	href="images/favicon/apple-touch-icon-152x152.png">
	<!-- For iPhone -->
<meta name="msapplication-TileColor" content="#00bcd4">
<meta name="msapplication-TileImage"
	content="images/favicon/mstile-144x144.png">
	<!-- For Windows Phone -->


	<!-- CORE CSS-->
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection">
	<!-- Custome CSS-->
<link href="css/custom-style.css" type="text/css" rel="stylesheet"
	media="screen,projection">
	<!-- CSS for full screen (Layout-2)-->
<link href="css/style-fullscreen.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link
	href="http://cdn.datatables.net/1.10.6/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">



	<!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
<link href="css/prism.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link href="js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="js/plugins/data-tables/css/jquery.dataTables.min.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="js/plugins/chartist-js/chartist.min.css" type="text/css"
	rel="stylesheet" media="screen,projection">
</head>
<body>
	<!-- Start Page Loading -->
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<!-- End Page Loading -->

	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START HEADER -->
	<header id="header" class="page-topbar">

		<!-- start header nav-->
		<div class="navbar-fixed">
			<nav class="pink darken-3">
				<div class="nav-wrapper">
					<ul class="left">
						<li class="no-hover"><a href="#" data-activates="slide-out"
							class="menu-sidebar-collapse btn-floating btn-flat btn-medium waves-effect waves-light pink darken-3"><i
								class="mdi-navigation-menu"></i></a></li>
					</ul>
					<div class="header-search-wrapper hide-on-med-and-down">
						<i class="mdi-action-search"></i> <input type="text" name="Search"
							class="header-search-input z-depth-2" placeholder="Buscar" />
					</div>
					<ul class="right hide-on-med-and-down">
						<li><a href="javascript:void(0);"
							class="waves-effect waves-block waves-light toggle-fullscreen"><i
								class="mdi-action-settings-overscan"></i></a></li>
						<li><a href="javascript:void(0);"
							class="waves-effect waves-block waves-light"><i
								class="mdi-social-notifications"></i></a></li>

					</ul>
				</div>
			</nav>
		</div>
		<!-- end header nav-->

	</header>
	<!-- END HEADER -->

	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START MAIN -->
	<div id="main">
		<!-- START WRAPPER -->
		<div class="wrapper">

			<!-- START LEFT SIDEBAR NAV-->
			<aside id="left-sidebar-nav">
				<ul id="slide-out" class="side-nav leftside-navigation">
					<li class=" pink darken-3">
						<div class="row">
							<div class="col col s4 m4 l4">
								<img src="images/avatar.jpg" alt=""
									class="circle responsive-img valign profile-image">
							</div>
							<div class="col col s8 m8 l8">
								<p class="user-roal">Administrator</p>
							</div>
						</div>
					</li>
					<li class="bold active"><a href="index.jsp"
						class="waves-effect waves-cyan"><i
							class="mdi-action-dashboard"></i> Dashboard</a></li>
					<li class="bold"><a href="agenda.jsp"
						class="waves-effect waves-cyan"><i
							class="mdi-editor-insert-invitation"></i> Agenda</a></li>
					<li class="no-padding">
						<ul class="collapsible collapsible-accordion">
							<li class="bold"><a
								class="collapsible-header waves-effect waves-cyan"><i
									class="mdi-action-invert-colors"></i> Clientes</a>
								<div class="collapsible-body">
									<ul>
										<li><a href="css-typography.html">Lista de Clientes</a></li>
										<li><a href="css-icons.html">Cadastrar Clientes</a></li>
									</ul>
								</div></li>
							<li class="bold"><a
								class="collapsible-header waves-effect waves-cyan"><i
									class="mdi-action-invert-colors"></i> Profissionais</a>
								<div class="collapsible-body">
									<ul>
										<li><a href="css-typography.html">Lista de
												Profissionais</a></li>
										<li><a href="css-icons.html">Cadastrar Profissionais</a>
										</li>
									</ul>
								</div></li>

							<li class="divider"></li>

							<li><a href="#"><i class="mdi-action-face-unlock"></i>
									Perfil</a></li>

							<li><a href="#"><i class="mdi-hardware-keyboard-tab"></i>
									Sair</a></li>

						</ul>
					</li>
				</ul>

			</aside>
			<!-- END LEFT SIDEBAR NAV-->

			<!-- START CONTENT -->
			<section id="content">

				<!--breadcrumbs start-->
				<div id="breadcrumbs-wrapper" class=" grey lighten-3">
					<!-- Search for small screen -->
					<div class="header-search-wrapper grey hide-on-large-only">
						<i class="mdi-action-search active"></i> <input type="text"
							name="Search" class="header-search-input z-depth-2"
							placeholder="Explore Materialize">
					</div>
					<div class="container">
						<div class="row">
							<div class="col s12 m12 l12">
								<h5 class="breadcrumbs-title">LISTA DE CLIENTES</h5>
								<ol class="breadcrumb">
									<li><a href="index.jsp">Dashboard</a></li>
									<li class="active">Clientes</li>
								</ol>
							</div>
						</div>
					</div>
				</div>
				<!--breadcrumbs end-->

				<!--start container-->
				<div class="container">
				<br/>
					<!--DataTables example-->
					<div id="table-datatables">
						<div class="row">
							<div class="col s12 m12 l12">
								<table id="data-table-simple" class="responsive-table display"
									cellspacing="0">
									<thead>
										<tr>
											<th>Nome</th>
											<th>Cpf</th>
											<th>Telefone</th>
										</tr>
									</thead>

									<tfoot>
										<tr>
											<th>Nome</th>
											<th>Cpf</th>
											<th>Telefone</th>
										</tr>
									</tfoot>

									<tbody>
										<tr>
											<td>Larissa</td>
											<td>418.572.098-00</td>
											<td>(11) 97360-7008</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
				<!--end container-->
			</section>
			<!-- END CONTENT -->

		</div>
		<!-- END WRAPPER -->

	</div>
	<!-- END MAIN -->
	<!-- //////////////////////////////////////////////////////////////////////////// -->

	<!-- START FOOTER -->
	<footer class="page-footer pink darken-3">
		<div class="footer-copyright">
			<div class="container">
				<span class="right"> Desenvolvido por <a
					class="grey-text text-lighten-4" href="http://geekslabs.com/">Sal�o
						de Beleza</a></span>
			</div>
		</div>
	</footer>
	<!-- END FOOTER -->


	<!-- ================================================
    Scripts
    ================================================ -->

	<!-- jQuery Library -->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<!--materialize js-->
	<script type="text/javascript" src="js/materialize.js"></script>
	<!--prism-->
	<script type="text/javascript" src="js/prism.js"></script>
	<!--scrollbar-->
	<script type="text/javascript"
		src="js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<!-- data-tables -->
	<script type="text/javascript"
		src="js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="js/plugins/data-tables/data-tables-script.js"></script>
	<!-- chartist -->
	<script type="text/javascript"
		src="js/plugins/chartist-js/chartist.min.js"></script>

	<!--plugins.js - Some Specific JS codes for Plugin Settings-->
	<script type="text/javascript" src="js/plugins.js"></script>

</body>

</html>