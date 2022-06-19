<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h2>DANH SÁCH ĐƠN HÀNG</h2>
<table class="table">
	<thead>
		<tr>
			<th>Mã đơn hàng</th>
			<th>Ngày đặt hàng</th>
			<th>Tổng cộng</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="o" items="${orders}">
			<c:url var="updateLink" value="/order/update">
				<c:param name="orderId" value="${o.id}" />
			</c:url>
			<!-- construct an "delete" link with customer id -->
			<c:url var="deleteLink" value="/order/delete">
				<c:param name="orderId" value="${o.id}" />
			</c:url>
			<tr>
				<td>${o.id }</td>
				<td>${o.orderDate }</td>
				<td>${o.amount }</td>
				<td>
				<td><a href="/order/detail/${o.id}"
					class="btn btn-sm btn-info"><i
						class="glyphicon glyphicon-search"></i></a> <a href="${deleteLink}"
					class="btn btn-sm btn-warning"
					onclick="if (!(confirm('Bạn có muốn hủy đơn hàng?'))) return false">Hủy Đơn</a>
			</tr>
		</c:forEach>
	</tbody>
</table>