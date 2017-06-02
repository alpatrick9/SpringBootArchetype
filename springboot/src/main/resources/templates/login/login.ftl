<#import "/spring.ftl" as spring />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Connection</title>
	<link href="<@spring.url relativeUrl="/css/login-styles.css"/>" rel="stylesheet">
	<link href="<@spring.url relativeUrl="/css/bootstrap/bootstrap.css"/>" rel="stylesheet">
	<link rel="stylesheet" href="<@spring.url relativeUrl="/css/font-awesome.min.css"/>">
</head>
<body>
<!-- Where all the magic happens -->
<!-- LOGIN FORM -->
<div class="text-center" style="padding:50px 0">
	<div class="logo">Se connecter</div>
	<!-- Main Form -->
	<div class="login-form-1">
		<form id="login-form" class="text-left" name='loginForm' action="<@spring.url relativeUrl="/authenticate"/>" method='POST'>
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<label for="lg_username" class="sr-only">Nom d'tilisateur</label>
						<input type="text" class="form-control" id="lg_username" name="email" placeholder="Nom d'utilisateur">
					</div>
					<div class="form-group">
						<label for="lg_password" class="sr-only">Mot de passe</label>
						<input type="password" class="form-control" id="lg_password" name="password" placeholder="Mot de passe">
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</div>
				<button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
			</div>
		</form>
	</div>
	<#if (error)>
		<p style="color: red">Erreur d'authentification: nom utilisateur
			ou mot de passe erroné</p>
	</#if>

	<#if (logout)>
		<p style="color: blue">Vous êtes deconnecté</p>
	</#if>
	<!-- end:Main Form -->
</div>
</body>
</html>