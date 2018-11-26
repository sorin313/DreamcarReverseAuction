<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<body>

	<div id="auction-details-panel" style="left: 12.5%"
		class="panel panel-info col-md-3">

		<div class="panel-heading" style="text: center">Auction Details</div>
		<div class="panel-body">
			<c:choose>
				<c:when test="${auctionDetail != null}">

					<spring:eval
						expression="loggedUser.getUserTypeId() == T(packt.java.spring.mvc.dreamcar.enums.UserTypeEnum).AuctionOwner"
						var="isAuctionOwner" />
					<c:choose>
						<c:when test="${isAuctionOwner}">
							<div id="targetPriceId">
								<label for="targetPriceLabel">Target Price: </label> <span>${auctionDetail.getTargetPrice()}</<span>
							</div>
						</c:when>
					</c:choose>


					<div id="auctionCurrencyType">
						<label for="auctionCurrencyTypeLabel">Currency Type: </label> <span>${auctionDetail.getCurrencyType()}</<span>
					</div>

					<div id="auctionComponentName">
						<label for="componentNameLabel">Component Name: </label> <span>${auctionDetail.getComponentName()}</span>
					</div>

					<div id="quantityId">
						<label for="componentQuantity">Quantity: </label> <span>${auctionDetail.getQuantity()}</span>
					</div>

					<div id="statusId">
						<label for="statusLabel">Status: </label> <span>${auctionDetail.getStatusId().toString()}</span>
					</div>

					<div id="startDateId">
						<label for="startDateLabel">Start Date: </label> <span>${auctionDetail.getStartDate().toString()}</span>
					</div>

					<div id="endDateId">
						<label for="endDateLabel">End Date: </label> <span>${auctionDetail.getEndDate().toString()}</span>
					</div>

					<c:choose>
						<c:when test="${auctionDetail.getWinner() != null}">
							<div id="winnerId">
								<label for="winnerLabel">Winner: </label> <span>${auctionDetail.getWinner().getUsername()}</span>
							</div>
						</c:when>
					</c:choose>
				</c:when>
			</c:choose>
		</div>
	</div>

</body>
</html>