<%@ page import="java.util.*, ufly.entities.Flight" %>



<div class="row-fluid">
	 <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      var data = new google.visualization.DataTable();
      function drawChart() {

        // Create the data table.
        
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Mushrooms', 3],
          ['Onions', 1],
          ['Olives', 1],
          ['Zucchini', 1],
          ['Pepperoni', 2]
        ]);

        // Set chart options
        var options = {'title':'How Much Pizza I Ate Last Night',
                       'width':400,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
    <div class="span12">
        <h4>Flight Statistics</h4>
		<div class="row-fluid">
			<div class="span12">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Flight</th>
							<th>Average Booked Flights</th>
						</tr>
					</thead>
					<tbody>
						<% 
						Set<String> flightnumber =  (Set<String>) request.getAttribute("flightnumber");	
						Iterator<String> it = flightnumber.iterator();
						while (it.hasNext())
						{
							String nextflightnumber = it.next();
							int avg = Flight.getBookedFlightsByFlightNum(nextflightnumber)/Flight.getTotalFlightsByFlightNum(nextflightnumber);
							out.println("<tr>");
							out.println("<td>"+nextflightnumber+"</td>");
							out.println("<td>"+avg+"</td>");
							out.println("</tr>");
						}
						%>
					</tbody>
				</table>
				
				
				<div id ="example" class= "modal hide fade in" style="display: none; ">
							<div class="modal-header">
								<a class="close" data-dismiss="modal">×</a>
								<h3>Graph</h3>
							</div>
							<div  id = "chart_div" class="modal-body">
								<h4>Text in a modal</h4>		        
							</div>
							<div class="modal-footer">
								<a href="#" class="btn btn-success">Call to action</a>
								<a href="#" class="btn" data-dismiss="modal">Close</a>
							</div>
						</div>
				<a data-toggle="modal" href="#example" class="btn btn-primary btn-large">View Graph</a>		
			</div><!-- span10 -->
		</div><!--/span row-->
	</div><!--/span12-->
</div><!--/row-->
