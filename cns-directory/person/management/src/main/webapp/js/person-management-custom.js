$JQry(function() {
	$JQry(".person-management select[name=entity]").change(function(event) {
		var $target = $JQry(event.target),
			$form = $target.closest("form"),
			$results = $form.find(".form-group.results");
		
		jQuery.ajax({
			url: $form.data("url"),
			async: true,
			cache: true,
			headers: {
				"Cache-Control": "max-age=60, public"
			},
			data: {
				filters: $form.serialize()
			},
			dataType: "html",
			success : function(data, status, xhr) {
				$results.html(data);
				
				$JQry(".person-management a.person").click(selectUser)
			}
		});
	})
});
