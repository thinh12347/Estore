<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">

		<ul class="nav navbar-nav">
			<li><a href="/home/index">Trang chủ</a></li>
			<li><a href="/home/about">Giới thiệu</a></li>
			<li><a href="/home/contact">Liên hệ</a></li>
			<li><a href="/home/feedback">Góp ý</a></li>
			<li><a href="/home/faq">Hỏi và đáp</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <c:choose>
						<c:when test="${empty sessionScope.user}">
							Tài khoản
						</c:when>
						<c:otherwise>
								${user.fullname}
						</c:otherwise>
					</c:choose> <span class="caret"></span></a> <c:choose>
					<c:when test="${empty sessionScope.user }">
						<ul class="dropdown-menu">
							<li><a href="/account/login">Đăng nhập</a></li>
							<li><a href="/account/register">Đăng kí </a></li>
							<li><a href="/account/forgot">Quên mật khẩu</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="dropdown-menu">
							<li><a href="/account/logoff">Đăng xuất </a></li>
							<li><a href="/account/change">Đổi mật khẩu </a></li>
							<li><a href="/account/edit">Cập nhật tài khoản </a></li>
							<li><a href="/order/list">Đơn hàng </a></li>
							<li><a href="/order/items">Sản phẩm đã mua </a></li>
						</ul>
					</c:otherwise>
				</c:choose></li>
		</ul>
	</div>
</nav>
<script>
	$(function() {
		$('a[data-lang]').click(function() {
			var lang = $(this).attr("data-lang");
			$.ajax({
				url : '/home/language?lang=' + lang,
				success : function() {
					location.reload();
				}
			});
			return false;
		})
	})
</script>
