<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
<jsp:include page="../includes/head.jsp" />
</head>


<body>
	<jsp:include page="../includes/header.jsp" />

	<div id="page-content" class="container">


		<div class="media">
			<div class="media-body hidden-xs">
				<!-- Breadcrumb -->
				<p:region regionName="breadcrumb" />
			</div>

			<div class="media-right simple-menubar">
				<!-- Menubar -->
				<p:region regionName="menubar" />
			</div>
		</div>

		<!-- Notifications -->
		<p:region regionName="notifications" />

		<p:region regionName="maximized" />
	</div>

	<jsp:include page="../includes/footer.jsp" />
</body>

</html>
