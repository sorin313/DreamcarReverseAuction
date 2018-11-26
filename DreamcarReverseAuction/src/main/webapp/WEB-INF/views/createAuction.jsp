<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<body>


	<div id="toggle-panel-auction-create-Id" class="panel panel-default col-md-4 create-panel-close">

		<div class="panel-heading">Create Auction</div>
		<div class="panel-body">
			<form action="createAuction" method="post">
				<div class="form-group">
					<label id="currencyLabel">Currency:</label> <select
						class="form-control" required path="currency" name="currencyType"
						id="currencyType">
						<option value="">Select currency</option>

						<c:forEach items="${currencies}" var="currency">
							<option id="${currency}" value="${currency}">${currency}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group">
					<label for="targetPriceLabel">Target Price</label>
					<input type="number" class="form-control" id="targetPrice" name="targetPrice" required
						placeholder="Example: 200 ($)" min="1" step="any">
				</div>
					
				<div class="form-group">
					<label id="componentLabel">Component:</label> 
					<div id="new-component-btn-id" class="btn btn-sm btn-default"
					onclick="togglePanelCreate(this)">
					<i id="new-component-arrow-toggle-id" class="fa fa-angle-right"></i>
					New Component
					</div>
					
					<select
						class="form-control" required path="component" name="componentId"
						id="componentId">
						<option value="">Select component</option>
						<c:forEach items="${components}" var="component">
							<option id="${component.getComponentId()}" value="${component.getComponentId()}">${component.getName()}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group">
					<label for="quantityLabel">Quantity (Number of components)</label>
					<input type="number" class="form-control" id="quantity" name="quantity" required
						placeholder="Example: 30 (components)" min="1">
				</div>

				<div class="input-group date" data-provide="datepicker">
					<label id="endDateLabel">EndDate:</label> <input required
						type="date" id="endDate" name="endDate" class="form-control"
						onchange="checkMinEndDate()" 
						placeholder="Specify end date">
				</div>
				<br>
				<button id="createAuctionId" class="btn btn-success">
					<i class="fa fa-plus"></i> Create Auction
				</button>
			</form>
		</div>
	</div>

</body>
</html>