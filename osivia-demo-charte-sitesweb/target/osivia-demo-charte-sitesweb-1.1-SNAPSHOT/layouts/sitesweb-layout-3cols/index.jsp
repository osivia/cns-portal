<%@ taglib prefix="p" uri="portal-layout"%>
<!DOCTYPE html>

<html>
<head>
<p:region regionName='header-metadata' />

<p:headerContent />
<p:theme themeName="osivia-demo-charte" />
</head>

<body class="with-drawer">
	<!-- Barre d'outils -->
	<p:region regionName="toolbar" />

	<header class="container">
		<p:region regionName="headBand" />
	</header>

	<div class="wrapper-outer">
		<div class="wrapper-inner">


			<div class="container">
				<!-- Notifications -->
				<p:region regionName="notifications" />

				<div class="row">
					<!-- Navigation -->
					 <div id="drawer">
					
						<div class="col-sm-4">
							<p:region regionName="colNav" />
						</div>
					
					</div>

					<div class="col-sm-8">
						<div class="row hidden-xs">
							<div class="col-sm-8">
								<!-- Breadcrumb -->
								<p:region regionName="breadcrumb" />
							</div>

							<div class="col-sm-4">
								<!-- Recherche -->
								<p:region regionName="search" />
							</div>
						</div>

						<div class="row">
							<div class="col-sm-8">
								<!-- Title -->
								<p:region regionName="blocTitre" />
								<!-- Left content -->
								<p:region regionName="colGauche" cms="true" />
							</div>

							<div class="col-sm-4">
								<!-- Right content -->
								<p:region regionName="colDroite" cms="true" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer class="container">
		<p:region regionName="footer" />
	</footer>

	<!-- AJAX scripts -->
	<p:region regionName="AJAXScripts" />
	<!-- AJAX footer -->
	<p:region regionName="AJAXFooter" />
	<!-- Page settings -->
	<p:region regionName="pageSettings" />
</body>
</html>
