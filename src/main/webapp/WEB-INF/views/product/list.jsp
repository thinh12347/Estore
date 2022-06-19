<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:forEach var="p" items="${list }">
	<div class="col-sm-4">
		<div class="thumbnail pro-icon-wraper">
			<a href="/product/detail/${p.id }"> <img class="estore-prod"
				src="/static/images/product/${p.image }"></a>
			<div class="caption">
				<p>${p.name }</p>
				<div class="pull-right" data-id="${p.id }">
					<button class="btn btn-sm btn-danger">
						<i class="glyphicon glyphicon-shopping-cart btn-add-to-cart"></i>
					</button>
					<button class="btn btn-sm btn-warning btn-star">
						<i class="glyphicon glyphicon-star"></i>
					</button>
					<button class="btn btn-sm btn-success btn-open" data-toggle="modal"
						data-target="#myModal">
						<i class="glyphicon glyphicon-envelope"></i>
					</button>
				</div>
				<p>
					<c:choose>
						<c:when test="${p.discount == 0}">
								${p.unitPrice }đ
						</c:when>
						<c:when test="${p.discount > 0 }">
							<span class="giacu">${p.unitPrice } </span> &nbsp
							<span class="error">${ p.unitPrice - (p.unitPrice *p.discount) }đ</span>
							
						</c:when>
					</c:choose>
				</p>
			</div>
			<c:choose>
				<c:when test="${p.special == true}">
					<img class="pro-icon" style="width: 50px; height: 50px;"
						src="/static/images/special-icon.jpg">
				</c:when>
				<c:when test="${p.discount > 0 }">
					<img class="pro-icon" style="width: 50px; height: 50px;"
						src="/static/images/promo-icon.jpg">
				</c:when>
			</c:choose>
		</div>

	</div>
</c:forEach>
<jsp:include page="dialog.jsp"></jsp:include>