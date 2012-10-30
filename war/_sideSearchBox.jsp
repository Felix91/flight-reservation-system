<form action="listings.html" class="form-inline">
						<fieldset>
							<div class="row-fluid">
								<div class="span12">
									<div class="control-group">
										<label class="control-label span3" for="focusedInput">To</label>
										<input type="text" class="span9 toCity" value="YVR" placeholder="City or Airport" data-items="4" data-provide="typeahead">
										<script>
											var city = ["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota","North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"];
											$('.toCity').typeahead({
												source: city
											});
										</script>
										<script>
											# $('.typeahead').typeahead();
											
											#	# This example does an AJAX lookup and is in CoffeeScript
											#	  $('.typeahead').typeahead(
											#	    # source can be a function
											#	    source: (typeahead, query) ->
											#	      # this function receives the typeahead object and the query string
											#	      $.ajax(
											#	        url: "/lookup/?q="+query
											#	        # i'm binding the function here using CoffeeScript syntactic sugar,
											#	        # you can use for example Underscore's bind function instead.
											#	        success: (data) =>
											#	          # data must be a list of either strings or objects
											#	          # data = [{'name': 'Joe', }, {'name': 'Henry'}, ...]
											#	          typeahead.process(data)
											#	      )
											#	    # if we return objects to typeahead.process we must specify the property
											#	    # that typeahead uses to look up the display value
											#	    property: "name"
											#	  )
										</script>
									</div>
									<div class="control-group">
										<label class="control-label span3" for="focusedInput">From</label>
										<input type="text" class="span9" placeholder="City or Airport">
									</div>
									<div class="control-group">
                	                    <label class="control-label span3" for="focusedInput">Depart</label>
										<input type="text" id="dp1" placeholder="Depart Date" class="span9">
        								<script>
        									$('#dp1').datepicker({
												format: 'mm-dd-yyyy'
											});
										</script>
                                    </div>
									<div class="control-group">
                	                    <label class="control-label span3" for="focusedInput">Return</label>
                                        <input type="text" id="dp2" placeholder="Return Date" class="span9">
                                        <script>
                                        	$('#dp2').datepicker({
       		                                	format: 'mm-dd-yyyy'
 	                                    	});     
                                        </script>
                                    </div>
							</div><!-- span12 -->
						</div><!-- row-fluid -->
						<div class="row-fluid">
								<label class="control-label span4" for="focusedInput">Passengers</label>
								<div class="controls span8">
									<select class="input-medium focused">
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
									</select>
								</div>
						</div>
						<div class="row-fluid">             
							<div class="span2 pull-right">
                        		<button type="submit" class="btn pull-right">Submit</button>
							</div>
                        </div>   
					</fieldset>
                </form>