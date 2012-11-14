<form action="/search" class="form-inline" method="post">
						<fieldset>
							<div class="row-fluid">
								<div class="span12">
									<div class="control-group">
										<label class="control-label span3" for="focusedInput">From</label>
										<input type="text" class="span9" placeholder="City or Airport" value="<%=(String)request.getAttribute("origin")%>" name="origin">
									</div>
									<div class="control-group">
										<label class="control-label span3" for="focusedInput">To</label>
										<input type="text" class="span9 toCity" value="<%=(String)request.getAttribute("destination") %>" placeholder="City or Airport" data-items="4" data-provide="typeahead" name="destination">
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
                	                    <label class="control-label span5" for="focusedInput">Depart Date</label>
										<input type="text" id="dp1" placeholder="Depart Date" class="span7" name="departureDate" value=<%=(String)request.getAttribute("departureDate") %>>
        								<script>
        									$('#dp1').datepicker({
												format: 'mm-dd-yyyy'
											})
											 .on('changeDate', function(ev){								
												$('#dp1').datepicker('hide');
											}); 
											
											
											
											
											
										</script>
                                    </div>
									<div class="control-group">
                	                    <label class="control-label span5" for="focusedInput">Return Date</label>
                                        <input type="text" id="dp2" placeholder="Return Date" class="span7" value="<%=(String)request.getAttribute("returnDate")%>"name="returnDate">
                                        <script>
                                        	$('#dp2').datepicker({
       		                                	format: 'mm-dd-yyyy'
 	                                    	})
 	                                    	.on('changeDate', function(ev){								
												$('#dp2').datepicker('hide');
											});     
                                        </script>
                                    </div>
							</div><!-- span12 -->
						</div><!-- row-fluid -->
						<div class="row-fluid">
								<label class="control-label span4" for="focusedInput">Passengers</label>
								<div class="controls span8">
									<select name="numPassengers" class="input-medium focused">
										<%	
											Integer selNumPax = Integer.parseInt((String)request.getAttribute("numPassengers"));
											for(Integer numPax=1;numPax<20;numPax++){ %>
										<option <%if(numPax==selNumPax)out.print("selected="); %>><%=numPax %></option>
										<%} %>
									</select>
								</div>
						</div>
						<div class="control-group">
                           	<div class="controls">
                               	<label class="radio">
                                   	<input name="oneWayOrReturn" type="radio" name="flightOpt" value="oneWay" <%if(request.getAttribute("oneWayOrReturn").equals("oneWay")) out.print("checked"); %>> One Way
                               	</label>
                               	<label class="radio">
                                   	<input name="oneWayOrReturn" type="radio" name="flightOpt" value="return" <%if(request.getAttribute("oneWayOrReturn").equals("return")) out.print("checked"); %>> Return
                               	</label>
                           	</div>
                       	</div>
						<div class="row-fluid">             
							<div class="span2 pull-right">
                        		<button type="submit" class="btn pull-right">Submit</button>
							</div>
                        </div>   
					</fieldset>
                </form>