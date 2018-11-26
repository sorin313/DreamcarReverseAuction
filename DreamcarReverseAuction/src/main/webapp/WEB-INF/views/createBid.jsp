<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<body>

	<div id="toggle-panel-bid-create-Id" class="panel panel-default col-md-3">

		<div class="panel-heading">Create Bid</div>
		<div class="form-group form">
		<div class="panel-body">
			<form action="bids/createBid" method="post">
				<div class="form-group">
					<label for="bidAmountLabel">Component Name</label>
					<input type="number" class="form-control" id="bidAmount" required
						name="bidAmount" placeholder="Example: 300 ($)" min="1" step="any">
				</div>
				<input type="text" class="hidden" value="${selectedAuctionId}" name="selectedAuctionId" id="selectedAuctionId"/>
				<button id="createBidId" class="btn btn-success">
					<i class="fa fa-plus"></i> Create Bid
				</button>
			</form>
		</div>
		</div>
	</div>
	
</body>
</html>