<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<c:url value="/resources/css/AuctionStyle.css" />"
	rel="stylesheet">

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">

<link href="https://use.fontawesome.com/releases/v5.0.8/css/all.css"
	rel="stylesheet">

<script src="/jquery/2.1.1/jquery.min.js"></script>
<script src="/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<title>Welcome to Reverse Auction WEB Application</title>
</head>
<body style="background-color: #556a80;">
	<div style="position: fixed; right: 30px">
		<h2>
			<span class="label label-default">Hello,
				${loggedUser.getUsername()} <i class="fa fa-user"></i>
			</span>
		</h2>
		<hr />
		<div>
			<form action="/dreamcar/logout" method="GET">
				<button class="btn btn-primary pull-right">Logout</button>
			</form>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<h3 class="text-center">
		<span class="label label-primary">Available Auctions</span>
	</h3>
	<div class="container">
		<spring:eval
			expression="loggedUser.getUserTypeId() != T(packt.java.spring.mvc.dreamcar.enums.UserTypeEnum).Provider"
			var="isAuctionOwner" />
		<c:choose>
			<c:when test="${isAuctionOwner}">

				<button id="new-auction-btn-id" class="btn btn-default"
					onclick="togglePanelCreate(this)">
					<i id="new-auction-arrow-toggle-id" class="fa fa-angle-down"></i>
					New Auction
				</button>
			
				<br>

				<%@include file="createAuction.jsp"%>
				<%@include file="createComponent.jsp"%>
			</c:when>
		</c:choose>
		<br> 
		<table class="table table-condensed col-md-5"
			style="background-color: white; box-shadow: 1px 1px 1px #999;">
			<thead class="bg-primary">
				<tr>
					<th>ID</th>
					<th>End date</th>
					<th>Status</th>
					<th>Owner</th>
					<th>Currency</th>
					<th>Quantity</th>
					<th>Component</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${auctions != null}">
						<c:forEach items="${auctions}" var="auction">
							<tr>
								<td>#${auction.getAuctionId()}</td>
								<td>${auction.getEndDate().toString()}</td>
								<td>${auction.getStatusId().toString()}</td>
								<td>${auction.getOwner().getUsername().toString()}</td>
								<td>${auction.getCurrencyType().toString()}</td>
								<td>${auction.getQuantity().toString()}</td>
								<td>${auction.getComponent().getName()}</td>
								<td>
									<form
										action="bids"
										method="GET">
										<input type="text" value="${auction.getAuctionId()}" name="selectedAuctionId" class="hidden" />
										<button class="btn btn-info btn-sm">Go to Bids</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
	</div>
</body>


<script src="../resources/js/auctionCreate.js"></script>
</html>