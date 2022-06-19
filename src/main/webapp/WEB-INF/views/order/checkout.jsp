<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<table class="table">
	<thead>
		<tr>
			<th>Mã sản phẩm</th>
			<th>Tên sản phẩm</th>
			<th>Giá</th>
			<th>Giảm giá</th>
			<th>Số lượng</th>
			<th>Tổng cộng</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="p"
			items="${sessionScope['scopedTarget.cartService'].items }">
			<tr>
				<td>${p.id }</td>
				<td>${p.name }</td>
				<td><f:formatNumber value="${p.unitPrice }"
						pattern="#,###.000" /></td>
				<td>${p.discount }</td>
				<td>${p.quantity }</td>
				<td><f:formatNumber value="${p.quantity * p.unitPrice * (1 - p.discount) }"
						pattern="#,###.000" /></td>

			</tr>
		</c:forEach>
	</tbody>
</table>
<h2>CHECKOUT</h2>
<h4 class="error">${message }</h4>
<form:form action="/order/checkout" modelAttribute="order">

	<div class="form-group">
		<label>Email</label>
		<form:input path="customer.id" class="form-control" readonly="true" />

	</div>
	<div class="form-group">
		<label>Tên</label>
		<form:input path="customer.fullname" class="form-control" readonly="true" />

	</div>
	<div class="form-group">
		<label>Ngày đặt hàng</label>
		<form:input path="orderDate" class="form-control" readonly="true" />
	</div>
	<div class="form-group">
		<label>Địa chỉ giao hàng</label>
		<form:input path="customer.Diachi" class="form-control" />
		<form:errors path="customer.Diachi" cssClass="error"></form:errors>
	</div>
	<div class="form-group">
		<label>Total Amount</label>
		<form:input path="amount" class="form-control" readonly="true" />
	</div>
	<div class="form-group">
		<label>Chú ý</label>
		<form:textarea path="description" rows="3" class="form-control" />
		
	</div>

	<div class="form-group">
		<button class="btn btn-success" >Mua hàng</button>
	</div>
</form:form>