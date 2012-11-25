package ufly.frs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufly.entities.*;

@SuppressWarnings("serial")
public class AjaxServer extends UflyServlet {
	private enum AjaxCallType{
		modifySeat,
		modifyMeal
	};
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException,IOException
	{
		AjaxCallType ajaxCall= AjaxCallType.valueOf(req.getParameter("ajaxCall"));
		switch(ajaxCall){
		case modifySeat:
			modifySeat(req.getParameter("seat"),req.getParameter("bookingNumber"));
			break;
		case modifyMeal:
			modifyMeal(req.getParameter("meal"),req.getParameter("bookingNumber"));
			break;
		}
		
	}
	private void modifySeat(String seat,String bookingNumber){
		FlightBooking fb=FlightBooking.getFlightBooking(Long.valueOf(bookingNumber));
		Integer rowNumber = Integer.valueOf(seat.substring(0, seat.length()-1));
		Character col = seat.charAt(seat.length()-1);
		Seat s=fb.getBookedFlight().getSeatingArrangement().getSeatByRowCol(rowNumber, col);
		fb.changeSeat(s);
	}
	private void modifyMeal(String mealstr,String bookingNumber){
		Meal m= Meal.valueOf(mealstr);
		FlightBooking fb = FlightBooking.getFlightBooking(Long.valueOf(bookingNumber));
		fb.changeMealChoice(m);
		return;
	}
}
