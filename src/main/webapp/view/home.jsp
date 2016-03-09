<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Shopping Assistant</title>
<base href="<%=basePath%>" />
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script type="text/javascript" src="js/wsevent.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
</head>
<jsp:include page="fragments/header.jsp" />

<body>
	<div class="container">
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<c:choose>
			<c:when test="${empty items}">
				<h1>Please add the item</h1>
			</c:when>

			<c:otherwise>
				<h1>All Items</h1>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#ITEM</th>
							<th>Sale Price</th>
							<th>Size</th>
							<th>Color</th>
							<th>Status</th>
						</tr>
					</thead>

					<c:forEach var="item" items="${items}">
						<tr>
							<td>${item.item_id}</td>
							<td>${item.sale_price}</td>
							<td>${item.size}</td>
							<td>${item.color}</td>
							<td>${status}</td>
							<td><spring:url
									value="/item/${item.item_id}/delete.mx" var="deleteUrl" /> <spring:url
									value="/item/${item.item_id}/update.mx" var="updateUrl" />
								<button id="${item.item_id}" class="btn btn-info"
									onclick="query('${item.item_id}')">
									<c:choose>
										<c:when test="${item.status}">Stop
										</c:when>
										<c:otherwise>Query
										</c:otherwise>
									</c:choose>
								</button>
								<button class="btn btn-primary"
									onclick="location.href='${updateUrl}'">Update</button>
								<button class="btn btn-danger"
									onclick="location.href='${deleteUrl}'">Delete</button></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		<h1>Item Ticker</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Item ID</th>
					<th>Color</th>
					<th>Original Price</th>
					<th>Sale Price</th>
					<th>Time</th>
				</tr>
			</thead>
			<tbody id="price"></tbody>
		</table>
	</div>

	<jsp:include page="fragments/footer.jsp" />
</body>
</html>