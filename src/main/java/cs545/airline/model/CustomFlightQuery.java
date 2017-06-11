package cs545.airline.model;

import java.sql.Date;

public class CustomFlightQuery {
	
	private String departureFromTime;
	private String departureToTime;
	private Date   departureDate;
	
	
	private String originAirportCode;
	private String destinationAirportCode;
	private String airlineName;

	private String departureFromDate;
	private String departureToDate;
	
	public String getOriginAirportCode() {
		return originAirportCode;
	}
	public void setOriginAirportCode(String originAirportCode) {
		this.originAirportCode = originAirportCode;
	}
	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}
	public void setDestinationAirportCode(String destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public String getDepartureFromDate() {
		return departureFromDate;
	}
	public void setDepartureFromDate(String departureFromDate) {
		this.departureFromDate = departureFromDate;
	}
	public String getDepartureToDate() {
		return departureToDate;
	}
	public void setDepartureToDate(String departureToDate) {
		this.departureToDate = departureToDate;
	}
	public String getDepartureFromTime() {
		return departureFromTime;
	}
	public void setDepartureFromTime(String departureFromTime) {
		this.departureFromTime = departureFromTime;
	}
	public String getDepartureToTime() {
		return departureToTime;
	}
	public void setDepartureToTime(String departureToTime) {
		this.departureToTime = departureToTime;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
}
