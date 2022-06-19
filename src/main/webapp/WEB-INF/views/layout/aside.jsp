<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<c:set var="cart" value="${sessionScope['scopedTarget.cartService'] }" />
<div class="panel panel-default">
	<div class="panel-heading">
		Giỏ hàng
	</div>
	<div class="panel-body">
		<img alt="" id="cart-img" src="/static/images/cart.jpg "
			class="col-sm-5">
		<ul class="col-sm-7">
			<li><b id="cart-cnt">${cart.count }</b> Sản phẩm</li>
			<li><b id="cart-amt"><f:formatNumber value="${cart.amount}"
						pattern="#,###.000" /></b> VNĐ</li>
			<li><a href="/cart/view">Xem giỏ hàng</a></li>
		</ul>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		Tìm kiếm
	</div>
	<div class="panel-body">
		<form action="/product/list-by-keywords" method="post">
			<input value="${param.keywords }" name="keywords"
				class="form-control" placeholder="keywords">
		</form>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		Danh mục
	</div>
	<div class="list-group">
		<c:set value="${ pageContext.response.locale.language}" var="lang"></c:set>
		<c:forEach var="c" items="${cates }">
			<a href="/product/list-by-category/${c.id }" class="list-group-item">${(lang=='vi')?c.nameVN:c.name}</a>
		</c:forEach>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		Đặc biệt
	</div>
	<div class="list-group">
		 <a href="/product/list-by-special/0"
			class="list-group-item">Bán chạy</a>
		<a href="/product/list-by-special/1" class="list-group-item">Xem nhiều</a> <a
			href="/product/list-by-special/2" class="list-group-item">Giảm giá</a> <a href="/product/list-by-special/3"
			class="list-group-item">Đặc biệt
		</a>
	</div>
</div>
<style id="cart-css">
</style>