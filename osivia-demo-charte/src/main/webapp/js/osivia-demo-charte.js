/**
 * Fixe la navigation lors du défilement.
 */
jQuery(document).ready(function($) {
    nav = $("nav");
 
    // Calcul de la marge entre le haut du document et la navigation
    fixedLimit = nav.offset().top - parseFloat(nav.css('marginTop').replace(/auto/,0)) - 25;
 
    // On déclenche un événement scroll pour mettre à jour le positionnement au chargement de la page
    $(window).trigger("scroll");
 
    $(window).scroll(function(event){
        // Valeur de défilement lors du chargement de la page
        windowScroll = $(window).scrollTop();
 
        // Mise à jour du positionnement en fonction du scroll
        if (windowScroll >= fixedLimit) {
        	nav.addClass("fixed");
        } else {
        	nav.removeClass("fixed");
        }
    });
});
