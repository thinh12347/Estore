<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<div class="row">
	<div class="col-sm-5 text-center">
		<img class="detail-img" src="/static/images/product/${prod.image }">
	</div>
	<div class="col-sm-7">
		<ul class="detail-info">
			<li>Tên: ${prod.name }</li>
			<li>Giá: <f:formatNumber value="${prod.unitPrice }"
					pattern="#,###00"></f:formatNumber>
			</li>
			<li>Ngày sản xuất: <f:formatDate value="${prod.productDate}"
					pattern="dd-MM-yyyy" />
			</li>
			<li>Danh mục: ${prod.category.nameVN }</li>

			<li>Giảm giá:<f:formatNumber value="${prod.discount }"
					type="percent"></f:formatNumber>
			</li>
			<li>Lượt xem: ${prod.viewCount }</li>
			<li>Đặc biệt: ${prod.special?'Có':'Không' }</li>
		</ul>

		<div class="pull-right" data-id="${prod.id }">
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

	</div>
</div>
<div class="text-justify">${prod.description }</div>


<ul class="nav nav-tabs">
	<li class="active"><a data-toggle="tab" href="#tab1">CÙNG LOẠI</a></li>
	<li><a data-toggle="tab" href="#tab2">YÊU THÍCH</a></li>
	<li><a data-toggle="tab" href="#tab3">ĐÃ XEM</a></li>
</ul>

<div class="tab-content">
	<div id="tab1" class="tab-pane fade in active">

		<c:forEach var="p" items="${list }">
			<a href="/product/detail/${p.id}"> <img alt="" class="thumb-img"
				src="/static/images/product/${p.image }">
			</a>
		</c:forEach>

	</div>
	<div id="tab2" class="tab-pane fade">
		<div>
			<c:forEach var="p" items="${favo}">
				<a href="/product/detail/${p.id}"> <img alt="" class="thumb-img"
					src="/static/images/product/${p.image }">
				</a>
			</c:forEach>
		</div>
	</div>
	<div id="tab3" class="tab-pane fade">
		<div>
			<c:forEach var="p" items="${viewed}">
				<a href="/product/detail/${p.id}"> <img alt="" class="thumb-img"
					src="/static/images/product/${p.image }">
				</a>
			</c:forEach>
		</div>
	</div>
</div>
<jsp:include page="dialog.jsp"></jsp:include>