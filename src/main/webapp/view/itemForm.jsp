<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="fragments/header.jsp" />

<div class="container">

	<c:choose>
		<c:when test="${not exist}">
			<h1>Add User</h1>
		</c:when>
		<c:otherwise>
			<h1>Update User</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/items.mx" var="itemActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="itemForm" action="${itemActionUrl}">

		<spring:bind path="item_id">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Item ID</label>
				<div class="col-sm-10">
					<form:input path="item_id" type="text" class="form-control " id="item_id" placeholder="Item ID" />
					<form:errors path="item_id" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="sale_price">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Sale Price</label>
				<div class="col-sm-10">
					<form:input path="sale_price" class="form-control" id="sale_price" placeholder="Sale Price" />
					<form:errors path="sale_price" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="size">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Size</label>
				<div class="col-sm-10">
					<form:input path="size" class="form-control" id="size" placeholder="size" />
					<form:errors path="size" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="color">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Color</label>
				<div class="col-sm-10">
					<form:input path="color" class="form-control" id="color" placeholder="color" />
					<form:errors path="color" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${not exist}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="fragments/footer.jsp" />

</body>
</html>