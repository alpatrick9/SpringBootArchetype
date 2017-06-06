<#include "/header.ftl"/>
<div class="clearboth"></div>
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
				<td class="text-center">${ user.name }</td>
				<td class="text-center">${ user.email }</td>
				<td class="text-center">
					<#list user.roles as role>
						${role.role} -	
					</#list>
				</td>
				<td class="text-center">
					<#if (user.active)>
						Activé
					</#if>
					<#if (!user.active)>
						Inactivé
					</#if>
				</td>
				<td class="text-center">
					<a href="" > 
						<i class="fa fa-refresh"></i>
					</a> &nbsp; 
					<a href="<@spring.url relativeUrl="/admin/del_user?id=${user.id}"/>" onclick="return confirm('Are you sure to delete this?')">
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