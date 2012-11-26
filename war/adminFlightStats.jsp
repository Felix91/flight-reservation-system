<%@ page import="java.util.*, ufly.entities.Flight" %>

<html>
<head>	
	<jsp:include page="/_header" />
	<script type="text/javascript" src="/js/jquery.qrcode.min.js"></script>
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container">
		<div id="content"><!-- start content -->
			<h3>Flight Manager Airport Statistics</h3>
			<div class="row-fluid">
    			<div class="span12">
        			<script src="/js/amcharts.js" type="text/javascript"></script>
        <div style="width: 100%; height: 400px; overflow: hidden;" id="chartdiv"></div> 
						
							<script type="text/javascript">
							
							var chart;
							
							<% 
								Set<String> flightnumber =  (Set<String>) request.getAttribute("flightnumber");	
							%>
							

							var chartData = [
							
								<%						
								
								Iterator<String> it = flightnumber.iterator();
								while (it.hasNext())
								{
									String nextflightnumber = it.next();
									int avg = Flight.getBookedFlightsByFlightNum(nextflightnumber)/Flight.getTotalFlightsByFlightNum(nextflightnumber);
									out.println("{");
									out.println("flightnumber: \""+nextflightnumber+"\",");
									out.println("avg:"+avg+",");
									out.println("},");
								}
								out.println("{}");
								%>
								
							];

							AmCharts.ready(function () {
                // SERIAL CHART
			    chart = new AmCharts.AmSerialChart();
			    
			    chart.pathToImages = "http://www.amcharts.com/lib/images/";
			    chart.zoomOutButton = {
			        backgroundColor: '#000000',
			        backgroundAlpha: 0.15
			    };
			    chart.dataProvider = chartData;
			    chart.marginTop = 10;
			    chart.autoMarginOffset = 3;
			    chart.marginRight = 0;        
			    chart.categoryField = "flightnumber";
			
			    // AXES
			    // Category
			    var categoryAxis = chart.categoryAxis;
			    categoryAxis.gridAlpha = 0.07;
			    categoryAxis.axisColor = "#DADADA";
			    categoryAxis.startOnAxis = true;
			    categoryAxis.showLastLabel = false;
			
			    // Value
			    var valueAxis = new AmCharts.ValueAxis();
			    valueAxis.stackType = "regular"; // this line makes the chart "stacked"
			    valueAxis.gridAlpha = 0.07;
			    valueAxis.title = "Number of booked flights";
			    chart.addValueAxis(valueAxis);
			
			
			    // GRAPHS
			    // first graph
			    var graph = new AmCharts.AmGraph();
			    graph.type = "line";
			    graph.title = "Average bookings";
			    graph.valueField = "avg";
			    graph.lineAlpha = 1;
			    graph.fillAlphas = 0.6; // setting fillAlphas to > 0 value makes it area graph
			    chart.addGraph(graph);
			
			    // LEGEND
			    var legend = new AmCharts.AmLegend();
			    legend.position = "top";
			    chart.addLegend(legend);
			
			    // CURSOR
			    var chartCursor = new AmCharts.ChartCursor();
			    chartCursor.zoomable = false; // as the chart displayes not too many values, we disabled zooming
			    chartCursor.cursorAlpha = 0;
			    chart.addChartCursor(chartCursor);

                // WRITE
                chart.write("chartdiv");
            });
							</script>
				</div><!--/span12-->
			</div><!--/row-->
    	</div><!-- end content -->
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    	</div><!-- end footer -->
	</div>
	<!-- page generated at: <% //out.print(request.getAttribute("date")); %>-->
</body>
</html>