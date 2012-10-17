package ufly.entities;

public class Flight {
	private Seat[][] seatingArragement;
	private Meal[] allowableMeals;
	private String flightNumber;
	
	
	public Seat[][] getSeatingArragement() {
		return this.seatingArragement;
	}

	/**
	 * @return the allowableMeals
	 */
	public Meal[] getAllowableMeals() {
		return allowableMeals;
	}

	/**
	 * @return the flightNumber
	 */
	public String getFlightNumber() {
		return flightNumber;
	}


	
}
