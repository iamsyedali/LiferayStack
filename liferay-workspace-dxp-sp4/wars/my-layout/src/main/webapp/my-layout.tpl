<div class="my-layout" id="main-content" role="main">
	<div class="portlet-layout row" style="background-color: red;">
		<div class="col-md-6 portlet-column portlet-column-first" id="column-1" style="background-color: pink;">
			$processor.processColumn("column-1", "portlet-column-content portlet-column-content-first")
		</div>

		<div class="col-md-6 portlet-column portlet-column-last" id="column-2" style="background-color: green;">
			$processor.processColumn("column-2", "portlet-column-content portlet-column-content-last")
		</div>
	</div>
	<div class="portlet-layout row" >
		<div class="col-md-6 portlet-column portlet-column-first" id="column-3" style="background-color: yellow;">
			$processor.processColumn("column-3", "portlet-column-content portlet-column-content-first")
		</div>

		<div class="col-md-6 portlet-column portlet-column-last" id="column-4" style="background-color:aqua;">
			$processor.processColumn("column-4", "portlet-column-content portlet-column-content-last")
		</div>
	</div>
</div>