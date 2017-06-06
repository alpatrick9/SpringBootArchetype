<#import "/spring.ftl" as spring />

<#assign style><@spring.url relativeUrl="/css/styles.css"/></#assign>
<#assign bootstrap><@spring.url relativeUrl="/css/bootstrap/bootstrap.css"/></#assign>
<#assign fontawesome><@spring.url relativeUrl="/css/font-awesome.min.css"/></#assign>

<#assign home_url><@spring.url relativeUrl="/secure/"/></#assign>
<#assign admin_url><@spring.url relativeUrl="/admin/users"/></#assign>
<#assign logout_url><@spring.url relativeUrl="/logout"/></#assign>
<#assign login_url><@spring.url relativeUrl="/login"/></#assign>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	
	<title>${title}</title>
	
	<link href="${style}" rel="stylesheet">
	<link href="${bootstrap}" rel="stylesheet">
	<link rel="stylesheet" href="${fontawesome}">

</head>

<body>
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="${home_url}">Spring MVC Todo list</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li id="home-menu"><a href="${home_url}">Home</a></li>
					<li id="admin-menu"><a href="${admin_url}">Admin</a></li>
				</ul>
				<div id="logout">
					<#if (Session.email?exists)>
						<a href="${logout_url}">Se déconnecter</a>
						<div>Session de ${Session.email}</div>
					</#if>
					<#if (!Session.email?exists)>
						<a href="${login_url}">Se loger</a>
					</#if>
				</div>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container">