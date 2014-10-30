<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
String httpError =  request.getParameter("httpCode") ;
if (httpError != null)	{
	try	{
	int httpCode = Integer.parseInt(httpError);
	response.setStatus( httpCode);
	} catch( Exception e){
		
	}
}
	
String msg = "Une erreur est survenue";
if( "404".equals (httpError))
	msg = "Ressource non trouvée";
if( "403".equals (httpError))
	msg = "Accès interdit";
%>

<html lang="fr" xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
	<head>
	   	<title>Toutatice</title>
	   	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	   	<link type="text/css" href="/osivia-portal-custom-web-assets/common-css/common.css" id="settings_css" rel="stylesheet">
	   	<script src="/portal-ajax/dyna/prototype.js" type="text/javascript"></script>
		<script src="/portal-ajax/dyna/effects.js" type="text/javascript"></script>
		<script src="/portal-ajax/dyna/dragdrop.js" type="text/javascript"></script>
		<script src="/portal-ajax/dyna/dyna.js" type="text/javascript"></script>
		<link type="text/css" href="/portal-ajax/dyna/style.css" id="dyna_css" rel="stylesheet">
		
		
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/accueil.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/core_css.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/design01.css" type="text/css" title="" media="screen" />
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/design02.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/design03.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/jquery.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/layout.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/extras-layout.css" type="text/css" title="" media="screen"/>
		<!-- <link rel="stylesheet" href="/themes/atomo/css/print.css" type="text/css" title="" media="screen" /> -->
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/SearchHighlighting.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/transmenu.css" type="text/css" title="" media="screen"/>
	
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/menu.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/filariane-recherche.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/portlet-fragment.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/portlet-contact.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/actus.css" type="text/css" title="" media="screen"/>
		<link rel="stylesheet" href="/acrennes-sites-charte/themes/atomo/css/toolbar_greta.css" type="text/css" title="" media="screen"/> 
		
		
		
	</head>
	
	<body id="body">
		
		<div id="dashboardnav"></div>
		
		<div id="wrap">
			
			<div id="header">
				<div class="dyna-region"><div id="pageSettings"></div></div>			 
				<div id="navigation"></div>
			</div>
		
			<div id="content">
			    <div style="clear: both;"></div>
				<div id="portlets">&nbsp;
					<div class="portlet-centre">
						<div id="error-container">
							<%= msg %> <!-- <%= request.getParameter("err") %> --> <!-- <%= httpError %> --><br/><br/>
                 			<a href="javascript:history.back()">Retour à  la page précédente</a>
                 		</div>	
					</div>

					<div style="clear: both;"></div>					
			    </div>
			</div>
		
			<div id="footer">
				<img style="border: 0px none;" alt="footer" src="/acrennes-sites-charte/themes/atomo/images/bg-footer.gif">
				<div style="display: none;" id="accessibilite">
					<a target="_blank" title="page valide en XHTML 1.0." href="http://validator.w3.org/check/referer"><img src="/acrennes-sites-charte/themes/atomo/images/xhtml.gif" alt="page valide en XHTML 1.0."></a>
					<a target="_blank" title="feuille de style valide en CSS 2." href="http://jigsaw.w3.org/css-validator/check/referer"><img src="/acrennes-sites-charte/themes/atomo/images/css.gif" alt="feuille de style valide en CSS 2."></a>
				</div>
			</div>
		
		</div>

	</body>
</html>