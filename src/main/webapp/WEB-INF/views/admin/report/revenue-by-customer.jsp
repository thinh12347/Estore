<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<h2 class="alert alert-success">Doanh thu theo khách hàng</h2>
<ul class="nav nav-tabs">
	<li class="active"><a data-toggle="tab" href="#tab1">Chart</a></li>
	<li><a data-toggle="tab" href="#tab2">Data</a></li>

</ul>
<div class="tab-content">

	<div id="tab1" class="tab-pane fade in active">
		<jsp:include page="_inventory_customer_chart.jsp"></jsp:include>
	</div>
	<div id="tab2" class="tab-pane fade">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Khách hàng</th>
					<th>Số lượng</th>
					<th>Doang thu</th>
					<th>Nhỏ nhất</th>
					<th>Lớn nhất</th>
					<th>Trung bình</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="e" items="${data }">
					<tr>
						<td>${e[0] }</td>
						<td>${e[1] }</td>
						<td><f:formatNumber value="${e[2] }" pattern="#,###.00"></f:formatNumber>VNĐ</td>
						<td><f:formatNumber value="${e[3] }" pattern="#,###.00"></f:formatNumber>VNĐ</td>
						<td><f:formatNumber value="${e[4]}" pattern="#,###.00"></f:formatNumber>VNĐ</td>
						<td><f:formatNumber value="${e[5] }" pattern="#,###.00"></f:formatNumber>VNĐ</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</div>
<jsp:include page="_inventory_customer_chart.jsp"></jsp:include>
