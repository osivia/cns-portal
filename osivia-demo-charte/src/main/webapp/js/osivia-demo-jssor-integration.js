jQuery(document).ready(function ($) {
	var slideshowTransitions = [
	// Fade Fly in R
	{
		$Duration : 1200,
		$During : {
			$Left : [ 0.3, 0.7 ]
		},
		$FlyDirection : 2,
		$Easing : {
			$Left : $JssorEasing$.$EaseInCubic,
			$Opacity : $JssorEasing$.$EaseLinear
		},
		$ScaleHorizontal : 0.3,
		$Opacity : 2,
		$Outside : true
	}
	// Fade Fly out L
	, {
		$Duration : 1200,
		$SlideOut : true,
		$FlyDirection : 1,
		$Easing : {
			$Left : $JssorEasing$.$EaseInCubic,
			$Opacity : $JssorEasing$.$EaseLinear
		},
		$ScaleHorizontal : 0.3,
		$Opacity : 2,
		$Outside : true
	} ];
	
	var captionTransitions = [];
    captionTransitions["L"] = { $Duration: 800, $FlyDirection: 1, $Easing: $JssorEasing$.$EaseInCubic };
    captionTransitions["R"] = { $Duration: 800, $FlyDirection: 2, $Easing: $JssorEasing$.$EaseInCubic };
    captionTransitions["T"] = { $Duration: 800, $FlyDirection: 4, $Easing: $JssorEasing$.$EaseInCubic };
    captionTransitions["B"] = { $Duration: 800, $FlyDirection: 8, $Easing: $JssorEasing$.$EaseInCubic };
    captionTransitions["TL"] = { $Duration: 800, $FlyDirection: 5, $Easing: $JssorEasing$.$EaseInCubic };
    captionTransitions["TR"] = { $Duration: 800, $FlyDirection: 6, $Easing: $JssorEasing$.$EaseInCubic };
    captionTransitions["BL"] = { $Duration: 800, $FlyDirection: 9, $Easing: $JssorEasing$.$EaseInCubic };
    captionTransitions["BR"] = { $Duration: 800, $FlyDirection: 10, $Easing: $JssorEasing$.$EaseInCubic };
	
	var options = {
			$AutoPlay: true,                                    //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
            $AutoPlaySteps: 1,                                  //[Optional] Steps to go for each navigation request (this options applys only when slideshow disabled), the default value is 1
            $AutoPlayInterval: 4000,                            //[Optional] Interval (in milliseconds) to go for next slide since the previous stopped if the slider is auto playing, default value is 3000
            $PauseOnHover: 0,                               	//[Optional] Whether to pause when mouse over if a slider is auto playing, 0 no pause, 1 pause for desktop, 2 pause for touch device, 3 pause for desktop and touch device, default value is 3

            $ArrowKeyNavigation: true,   			            //[Optional] Allows keyboard (arrow key) navigation or not, default value is false
            $SlideDuration: 500,                                //[Optional] Specifies default duration (swipe) for slide in milliseconds, default value is 500
            $MinDragOffsetToSlide: 20,                          //[Optional] Minimum drag offset to trigger slide , default value is 20
            $SlideWidth: 600,                                 	//[Optional] Width of every slide in pixels, default value is width of 'slides' container
            $SlideHeight: 300,                                	//[Optional] Height of every slide in pixels, default value is height of 'slides' container
            $SlideSpacing: 0, 					                //[Optional] Space between each slide in pixels, default value is 0
            $DisplayPieces: 1,                                  //[Optional] Number of pieces to display (the slideshow would be disabled if the value is set to greater than 1), the default value is 1
            $ParkingPosition: 0,                                //[Optional] The offset position to park slide (this options applys only when slideshow disabled), default value is 0.
            $UISearchMode: 1,                                   //[Optional] The way (0 parellel, 1 recursive, default value is 1) to search UI components (slides container, loading screen, navigator container, direction navigator container, thumbnail navigator container etc).
            $PlayOrientation: 1,                                //[Optional] Orientation to play slide (for auto play, navigation), 1 horizental, 2 vertical, default value is 1
            $DragOrientation: 1,                                //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)
			
//			$SlideshowOptions: {								//[Optional] Options to specify and enable slideshow or not
//                $Class: $JssorSlideshowRunner$,                 //[Required] Class to create instance of slideshow
//                $Transitions: slideshowTransitions,           	//[Required] An array of slideshow transitions to play slideshow
//                $TransitionsOrder: 1,                           //[Optional] The way to choose transition to play slide, 1 Sequence, 0 Random
//                $ShowLink: true									//[Optional] Whether to bring slide link on top of the slider when slideshow is running, default value is false
//            },
            
            $CaptionSliderOptions: {                            //[Optional] Options which specifies how to animate caption
                $Class: $JssorCaptionSlider$,                   //[Required] Class to create instance to animate caption
                $CaptionTransitions: captionTransitions,       //[Required] An array of caption transitions to play caption, see caption transition section at jssor slideshow transition builder
                $PlayInMode: 1,                                 //[Optional] 0 None (no play), 1 Chain (goes after main slide), 3 Chain Flatten (goes after main slide and flatten all caption animations), default value is 1
                $PlayOutMode: 3                                 //[Optional] 0 None (no play), 1 Chain (goes before main slide), 3 Chain Flatten (goes before main slide and flatten all caption animations), default value is 1
            },
            
            $ThumbnailNavigatorOptions: {
                $Class: $JssorThumbnailNavigator$,              //[Required] Class to create thumbnail navigator instance
                $ChanceToShow: 2,                               //[Required] 0 Never, 1 Mouse Over, 2 Always
                $ActionMode: 0,                                 //[Optional] 0 None, 1 act by click, 2 act by mouse hover, 3 both, default value is 1
                $DisableDrag: true,                      		//[Optional] Disable drag or not, default value is false
                $Orientation: 2                                 //[Optional] Orientation to arrange thumbnails, 1 horizental, 2 vertical, default value is 1
            },
            
            $NavigatorOptions: {                                //[Optional] Options to specify and enable navigator or not
                $Class: $JssorNavigator$,                       //[Required] Class to create navigator instance
                $ChanceToShow: 2,                               //[Required] 0 Never, 1 Mouse Over, 2 Always
                $ActionMode: 1,                                 //[Optional] 0 None, 1 act by click, 2 act by mouse hover, 3 both, default value is 1
                $Lanes: 1,                                      //[Optional] Specify lanes to arrange items, default value is 1
                $SpacingX: 10,	                      			//[Optional] Horizontal space between each item in pixel, default value is 0
                $SpacingY: 0                      				//[Optional] Vertical space between each item in pixel, default value is 0
            },
			
            $DirectionNavigatorOptions: {
                $Class: $JssorDirectionNavigator$,              //[Requried] Class to create direction navigator instance
                $ChanceToShow: 1                                //[Required] 0 Never, 1 Mouse Over, 2 Always
            }
        };
	
	
    var jssor_slider = new $JssorSlider$('slider_container', options);

    // You can remove responsive code if you don't want the slider scales while window resizes
    function ScaleSlider() {
        var parentWidth = jssor_slider.$Elmt.parentNode.clientWidth;
        if (parentWidth)
            jssor_slider.$SetScaleWidth(Math.min(parentWidth, 600));
        else
            window.setTimeout(ScaleSlider, 30);
    }

    ScaleSlider();
    
    if (!navigator.userAgent.match(/(iPhone|iPod|iPad|BlackBerry|IEMobile)/)) {
        $(window).bind('resize', ScaleSlider);
    }
});


function loadSlide() {
	var slide = $JQry(".slide")[0];
	if (slide) {
		var ul = slide.parentNode;
		
		if (ul.getAttribute("u") == null) {
			ul.setAttribute("u", "slides");
			ul.setAttribute("style", "cursor: move; position: absolute; left: 0px; top: 0px; width: 600px; height: 200px; overflow: hidden;");
			var div = ul.parentNode;
			div.setAttribute("id", "slider_container");
			div.setAttribute("style", "position: relative; top: 0px; left: 0px; width: 600px; height: 200px;");
			
//			// ThumbnailNavigator Skin
//			var thumbnavigator = document.createElement("div");
//			thumbnavigator.setAttribute("u", "thumbnavigator");
//			thumbnavigator.setAttribute("class", "slider1-T");
//			thumbnavigator.setAttribute("style", "position: absolute; bottom: 0px; left: 0px; height:60px; width:600px;");
//			div.appendChild(thumbnavigator);
//			// Thumbnail filter
//			var filter = document.createElement("div");
//			filter.setAttribute("style", "filter: alpha(opacity=40); opacity:0.4; position: absolute; display: block; background-color: #ffffff; top: 0px; left: 0px; width: 100%; height: 100%;");
//			thumbnavigator.appendChild(filter);
//			// Thumbnail Item Skin
//			var slides = document.createElement("div");
//			slides.setAttribute("u", "slides");
//			thumbnavigator.appendChild(slides);
//			var slidesPrototype = document.createElement("div");
//			slidesPrototype.setAttribute("u", "prototype");
//			slidesPrototype.setAttribute("style", "position: absolute; width: 600px; height: 60px; top: 0; left: 0;");
//			slides.appendChild(slidesPrototype);
//			var thumbnailtemplate = document.createElement("thumbnailtemplate");
//			thumbnailtemplate.setAttribute("style", "font-family: verdana; font-weight: normal; position: absolute; width: 100%; height: 100%; top: 0; left: 0; color:#000; line-height: 60px; font-size:20px; padding-left:10px;");
//			slidesPrototype.appendChild(thumbnailtemplate);
			
			// Navigator container
			var navigator = document.createElement("div");
			navigator.setAttribute("u", "navigator");
			navigator.setAttribute("class", "jssorn01");
			navigator.setAttribute("style", "position: absolute; bottom: 16px; right: 10px;");
			div.appendChild(navigator);
			var navigatorPrototype = document.createElement("div");
			navigatorPrototype.setAttribute("u", "prototype");
			navigatorPrototype.setAttribute("style", "position: absolute; width: 12px; height: 12px;");
			navigator.appendChild(navigatorPrototype);
			
            // Arrow Left
			var arrowLeft = document.createElement("span");
			arrowLeft.setAttribute("u", "arrowleft");
			arrowLeft.setAttribute("class", "jssord05l");
			arrowLeft.setAttribute("style", "width: 40px; height: 40px; top: 90px; left: 8px;");
			div.appendChild(arrowLeft);
			
            // Arrow Right
			var arrowRight = document.createElement("span");
			arrowRight.setAttribute("u", "arrowright");
			arrowRight.setAttribute("class", "jssord05r");
			arrowRight.setAttribute("style", "width: 40px; height: 40px; top: 90px; right: 8px");
			div.appendChild(arrowRight);
			
		}
	}
}
