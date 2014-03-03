<%@ page import="org.jboss.portal.server.PortalConstants"%>
<%@ taglib uri="/WEB-INF/theme/portal-layout.tld" prefix="p"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<p:region regionName='header-metadata' />
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<meta lang="fr" content="seria" name="author">
	<!-- to correct the unsightly Flash of Unstyled Content. -->
	<script type="text/javascript"></script>
	<p:headerContent />
	<p:theme themeName="default" />
</head>

<body id="body">
	<p:region regionName='AJAXScripts'/>

	<div id="userShell">
		<div id="wrapper">
			<div id="conteneurToolbar">
				<p:region regionName='toolbar'/>
			</div>
			
			<div id="entetePage">
				<p:region regionName="headBand"/>
				<p:region regionName="pageSettings"/>
			</div>

			<div id="conteneur">
				<p:region regionName="colNav"/>
				<div class="colCentre">
					<div class="ligne1">
						<p:region regionName='breadcrumb'/>
						<p:region regionName='search'/>
					</div>
					<p:region regionName="blocTitre"/>
					<p:region regionName="notifications"/>
					<p:region regionName="colMilieu" cms="true"/>
				</div>
			</div>
		</div>

		<div id="piedPage">
			<p:region regionName='footer'/>
		</div>

		<p:region regionName='AJAXFooter'/>

	</div>
</body>
</html>
