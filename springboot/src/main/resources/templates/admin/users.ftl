<#include "/header.ftl"/>
<div class="clearboth"></div>
<#if (!update)>
	<form method="post" action="<@spring.url relativeUrl="/admin/add_user"/>">
		<div class="form-group">
			<@spring.bind path="user"/>
			<@spring.formInput "user.name" 'class="form-control" placeholder="Name" required'/>
			<@spring.formInput "user.email" 'class="form-control" placeholder="E-mail" required'/>
			<@spring.formPasswordInput "user.password" 'class="form-control" placeholder="Password" required'/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Ok" class="btn btn-success">
		</div>
	</form>
</#if>
<#if (update)>
	<form method="post" action="<@spring.url relativeUrl="/admin/udpate_user"/>">
		<div class="form-group">
			<@spring.bind path="user"/>
			<@spring.formHiddenInput "user.id"/>
			<@spring.formInput "user.name" 'class="form-control" placeholder="Name" required'/>
			<@spring.formInput "user.email" 'class="form-control" placeholder="E-mail" required'/>
			<@spring.formSingleSelect "user.role", roles, 'class="form-control" placeholder="Role" required'/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Ok" class="btn btn-success">
			<a href="<@spring.url relativeUrl="/admin/users"/>"><input type="button" value="Annule" class="btn btn-primary"></a>
		</div>
	</form>
</#if>
<#if (changepass)>
<h5>Modification mot de pass de ${password.email?html }</h5>
<#if (errorpass)>
	<p style="color:red;">L'ancien mot de passe ne correspond pas</p>
</#if>
	<form method="post" action="<@spring.url relativeUrl="/admin/update_pass"/>">
		<div class="form-group">
			<@spring.bind path="password"/>
			<@spring.formHiddenInput "password.userId"/>
			<@spring.formPasswordInput "password.oldPassword" 'class="form-control" placeholder="Ancien password" required'/>
			<@spring.formPasswordInput "password.newPassword" 'class="form-control" placeholder="Nouveau password" required'/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Ok" class="btn btn-success">
			<a href="<@spring.url relativeUrl="/admin/users"/>"><input type="button" value="Annule" class="btn btn-primary"></a>
		</div>
	</form>
</#if>
<#if (users?size > 0)>
	<h1>User list</h1>
	<table class="table table-hover table-bordered table-striped">
		<tr>
			<th class="text-center">#</th>
			<th class="text-center">Name</th>
			<th class="text-center">E-mail</th>
			<th class="text-center">Role</th>
			<th class="text-center">Status</th>
			<th></th>
		</tr>
		<#list users as user>
			<tr>
				<td class="text-center">${ user.id }</td>
				<td class="text-center">${ user.name?html }</td>
				<td class="text-center">${ user.email?html }</td>
				<td class="text-center">${ user.role?html } </td>
				<td class="text-center">
					<#if (user.active)>
						Activé
						<a href="<@spring.url relativeUrl="/admin/user_status?id=${user.id}"/>" onclick="return confirm('Are you sure to disable this user?')" title="Désactiver"><i class="fa fa-power-off"></i></a>
					</#if>
					<#if (!user.active)>
						Inactivé
						<a href="<@spring.url relativeUrl="/admin/user_status?id=${user.id}"/>" onclick="return confirm('Are you sure to enable this user?')" title="Activer"><i class="fa fa-power-off"></i></a>
					</#if>
				</td>
				<td class="text-center">
					<a href="<@spring.url relativeUrl="/admin/users?update=true&id=${user.id}"/>" title="Modifier"> 
						<i class="fa fa-refresh"></i>
					</a> &nbsp;
					<a href="<@spring.url relativeUrl="/admin/users?changepass=true&id=${user.id}"/>" title="Modifier mot de passe">
						<i class="fa fa-key"></i>
					</a> &nbsp; 
					<a href="<@spring.url relativeUrl="/admin/del_user?id=${user.id}"/>" onclick="return confirm('Are you sure to delete this?')" title="Supprimer">
						<i class="fa fa-remove"></i>
					</a>
				</td>
			</tr>
		</#list>
	</table>
</#if>
<#include "/footer.ftl"/>
<script>
	var nav = document.getElementById("admin-menu");
	nav.className += " active";
</script>