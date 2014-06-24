// Pagination
//var pages = [];
//
//function pageNavigation(page) {
//	$JQry(".pagination-item").removeClass("active");
//	$JQry(".pagination-menu li").removeClass("active");
//	$JQry("[data-page=" + page + "]").addClass("active");
//};
//
//function createPaginationMenu(items) {
//	// Menu
//	var menu = document.createElement("div");
//	menu.className = "pagination-menu visible-xs text-center";
//	
//	// UL
//	var ul = document.createElement("ul");
//	ul.className = "pagination";
//	menu.appendChild(ul);
//	
//	items.each(function(index) {
//		var item = $JQry(this);
//		var page = item.data("page");
//		pages.push(page);
//		var glyph = item.data("glyph");
//
//		// LI
//		var li = document.createElement("li");
//		li.setAttribute("data-page", item.data("page"));
//		if (item.hasClass("active")) {
//			li.className = "active";
//		}
//		ul.appendChild(li);
//		
//		// A
//		var a = document.createElement("a");
//		a.setAttribute("href", "#");
//		li.appendChild(a);
//		
//		// Text
//		var text;
//		if (glyph != null) {
//			text = document.createElement("span");
//			text.className = "glyphicon glyphicon-" + glyph;
//		} else {
//			text = document.createTextNode(page);
//		}
//		a.appendChild(text);
//	});
//	
//	$JQry("body").append(menu);
//}
//
//function displayPaginationMenu() {
//	$JQry(".pagination-menu").removeClass("hidden").delay(5000).addClass("hidden");
//}
//
//
//$JQry(document).ready(function($) {
//	if ($(".pagination-item").length > 1) {
//		// Creating pagination menu
//		createPaginationMenu($(".pagination-item"));
//
//		// Pagination menu event handler
//		$(".pagination-menu a").click(function(event) {
//			event.stopPropagation();
//			
//			var parent = $(this).parent();
//			if (!parent.hasClass("active")) {
//				pageNavigation(parent.data("page"));
//			}
//		});
//	}
//});
//
//$JQry(window).on("swiperight", function() {
//	var page = $JQry(".pagination-item.active").data("page");
//	var index = pages.indexOf(page);
//	if (index > 0) {
//		pageNavigation(pages[index - 1]);
//	}
//});
//
//$JQry(window).on("swipeleft", function() {
//	var page = $JQry(".pagination-item.active").data("page");
//	var index = pages.indexOf(page);
//	if ((index >= 0) && (index < (pages.length - 1))) {
//		pageNavigation(pages[index + 1]);
//	}
//});
