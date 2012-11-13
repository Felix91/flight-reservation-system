<%@ page import="java.util.*" %>

<html>
<head>	
	<jsp:include page="/_header" />
</head>
<body>
	<jsp:include page="/_navbar" />
	<div class="container-fluid">
		<div id="content"><!-- start content -->
			<!-- Add content here -->
    		<%	Vector<HashMap> flights=(Vector)request.getAttribute("flightInfo");
    			for(int i=0;i<flights.size();i++){
    				HashMap<String,Object>flight=flights.get(i);
    			
    		%>
    		FlightNo=<%=(String)flight.get("flightNo")%><br>
    		<input type="hidden" name="flightNo[<%=i %>]" value=<%=(String)flight.get("flightNo") %>>
    		Date=<%=(Date)flight.get("Date")%><br>
    		<input type="hidden" name="Date[<%=i %>]" value=<%=(Date)flight.get("Date") %>>
    		
    		PassName=<input name="PassName[<%=i %>]" >
			
    		
    		<select name="seat[<%=i %>]">
    		<%
    				String[] seats=(String[])flight.get("seats");
	    			for(int j=0;j<seats.length;j++)
	    			{
	    	%>
	    			<option value="<%=seats[j] %>"><%=seats[j] %></option>
	    	<%		
	    			}
    			}
    		%>
    		</select>
    	</div><!-- end content -->
    
    	<div id="footer"><!-- start footer -->
    		<jsp:include page="/_footer" />
    	</div><!-- end footer -->
	</div>
	
</body>
</html>