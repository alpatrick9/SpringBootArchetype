<#include "/header.ftl"/>
<div class="clearboth"></div>

<form method="post" action="<@spring.url relativeUrl="/secure/add_todo"/>">
	<div class="form-group">
		<@spring.bind path= "todo" />
		<@spring.formHiddenInput "todo.id"/>
		<@spring.formInput "todo.name" 'class="form-control" required placeholder="Todo name"'/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="submit" value="Ok" class="btn btn-success">
	</div>
</form>

<#if (todos?size > 0)>
	<h1>Todo list</h1>
	<table class="table table-hover table-bordered table-striped">
		<tr>
			<th class="text-center">#</th>
			<th class="text-center">todo</th>
			<th></th>
		</tr>
		<#list todos as todo>
			<tr>
				<td class="text-center">${ todo.id }</td>
				<td class="text-center">${ todo.name }</td>
				<td class="text-center">
					<a href="<@spring.url relativeUrl="/secure/?id=${todo.id}"/>" > 
						<i class="fa fa-refresh"></i>
					</a> &nbsp; 
					<a href="<@spring.url relativeUrl="/secure/delete_todo?id=${todo.id}"/>" onclick="return confirm('Are you sure to delete this?')">
						<i class="fa fa-remove"></i>
					</a>
				</td>
			</tr>
		</#list>
	</table>
</#if>

<#include "/footer.ftl"/>
<script>
	var nav = document.getElementById("home-menu");
	nav.className += " active";
</script>