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
	<div style="position: fixed; left: 30px">
		<form action="home" method="GET">
			<button id="back-to-auctions-btn-id"
				class="btn btn-primary pull-left">
				<i class="fa fa-angle-left"></i> Back to auctions
			</button>
		</form>
	</div>
	<br>
	<br>
	<br>
	<br>
	<h3 class="text-center">
		<span class="label label-primary">Bids for auction #${selectedAuctionId}</span>
	</h3>
	<div class="container">
		<spring:eval
			expression="loggedUser.getUserTypeId() != T(packt.java.spring.mvc.dreamcar.enums.UserTypeEnum).AuctionOwner"
			var="isProvider" />
		<c:choose>

			<c:when test="${isProvider}">
				<%@include file="createBid.jsp"%>
			</c:when>
		</c:choose>
		<br> <br>
		<table class="table table-condensed col-md-5"
			style="background-color: white; box-shadow: 1px 1px 1px #999;">
			<thead class="bg-primary">
				<tr>
					<th>ID</th>
					<th>Amount</th>
					<th>Bidder</th>
					<th>Company</th>
					<th>Date</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${bids != null}">
						<c:forEach items="${bids}" var="bid">
							<tr>
								<td>#${bid.getBidId()} </td>
								<td>${bid.getAmount()}</td>
								<td>${bid.getBidderUsername()} </td>
								<td>${bid.getCompanyName()} </td>
								<td>${bid.getCreatedDate().toString()}</td>
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