package ufly.entities;

public class SeatingArrangement {
	private int numRows;
	private int numColumns;
	private int numRowsFirstClass;
	private int numRowsBusinessClass;
	// Economy is numRows -firstclass-businessclass

	private Seat[][] seats;
	private int SeatsLeft;
}
