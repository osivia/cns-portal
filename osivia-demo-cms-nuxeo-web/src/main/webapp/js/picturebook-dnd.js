// Picture book drag'n'drop
// Required JQuery UI with interactions modules

$JQry(function() {
	// Picturebook draggable items
	$JQry(".picturebook .draggable").draggable({
		addClasses : false,
		connectToFancytree : true,
		cursorAt : {top : 0, left : 0},
		helper : function(event) {
			var $target = $JQry(event.target);
			
			var $draggable = $target.closest(".draggable");
			var sourceId = $draggable.data("id");
			var sourceType = $draggable.data("type");

			// Helper
			var $helper = $JQry(document.createElement("div"));
			$helper.addClass("draggable-helper thumbnail clearfix");
			$helper.data("id", sourceId);
			$helper.data("type", sourceType);
			$helper.append($draggable.find("img").clone());
			return $helper;
		},
		opacity : 0.8,
		revert : "invalid",
		revertDuration : 200
	});
	
	
	// File Upload
	$JQry(".picturebook .file-upload").fileupload({
		autoUpload : false,
		dataType : "json",
		disableImageResize : false,
		dropZone : ".drop-zone",
		
		add : function(e, data) {
			var $this = $JQry(this);
			var $root = $this.closest(".picturebook");
			var $panel = $root.find(".file-upload .panel");
			var $list = $root.find(".file-upload .file-upload-list");
			
			var $cancelGlyph = $JQry(document.createElement("i")).addClass("glyphicons halflings ban-circle");
			var $cancelText = $JQry(document.createElement("span")).text($panel.find(".cancel").first().text());
			

			// Display panel
			$panel.removeClass("hidden");
			
			// Context
			data.context = $JQry(document.createElement("li"))
			data.context.addClass("template-upload list-group-item")
			data.context.appendTo($list);
			
			$JQry.each(data.files, function(index, file) {
				// List item
				var $listItem = $JQry(document.createElement("div"))
				$listItem.addClass("clearfix");
				$listItem.appendTo(data.context);
				
				// Upload button
				var $uploadButton =  $JQry(document.createElement("button"))
				$uploadButton.addClass("start hidden");
				$uploadButton.click(function() {
					data.submit();
				});
				$uploadButton.appendTo($listItem);
				
				// Cancel button
				var $cancelButton = $JQry(document.createElement("button"));
				$cancelButton.addClass("cancel btn btn-default pull-right");
				$cancelButton.append($cancelGlyph);
				$cancelButton.append($cancelText);
				$cancelButton.appendTo($listItem);
				
				// Item content
				var $content = $JQry(document.createElement("p"));
				$content.text(file.name);
				$content.appendTo($listItem);
			});
		},

		stop : function(e, data) {
			var $this = $JQry(this);
			var $root = $this.closest(".picturebook");
			
			// Hide panel
			var $panel = $root.find(".file-upload .panel");
			$panel.addClass("hidden");
			
			// Refresh
			var url = $root.data("refreshurl");
			updatePortletContent(this, url);
		},

		progressall : function(e, data) {
			var progress = parseInt(data.loaded / data.total * 100, 10) + "%";
			$JQry(".picturebook .progress-bar").css("width", progress);
		}
	});
});
