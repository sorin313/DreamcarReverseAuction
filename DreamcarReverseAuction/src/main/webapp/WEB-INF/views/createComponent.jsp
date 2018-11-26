<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<body>

	<div id="toggle-panel-component-create-Id" class="panel panel-default col-md-3 create-panel-close">

		<div class="panel-heading">Create Component</div>
		<br>
		<div class="validationMessages" style="color:red; display:block" class="container">		
					${componentExists}
		</div>
		<div class="form-group form">
		<div class="panel-body">
			<form action="createComponent" method="post">
				<div class="form-group">
					<label for="componentNameLabel">Component Name</label>
					<input type="text" class="form-control" id="componentName" required
						name="componentName" placeholder="Example: Tier">
				</div>
				
				<button id="createComponentId" class="btn btn-success">
					<i class="fa fa-plus"></i> Create Component
				</button>
			</form>
		</div>
		</div>
	</div>
	
</body>
</html>