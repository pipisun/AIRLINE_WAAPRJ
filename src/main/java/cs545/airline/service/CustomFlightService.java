package cs545.airline.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Flight;
import cs545.airline.model.CustomFlightQuery;

@Named
@ViewScoped
public class CustomFlightService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private FlightService flightService;

	private CustomFlightQuery flightQuery;
	private List<Flight> listFlight;

	@PostConstruct
	public void findAllFlight() {
		flightQuery = new CustomFlightQuery();
		listFlight = flightService.findAll();
	}

	public void queryFlight() {
		listFlight = flightService.queryFlight(flightQuery.getAirlineName(), flightQuery.getOriginAirportCode(), flightQuery.getDestinationAirportCode());
	}

	public List<Flight> getListFlight() {
		return listFlight;
	}

	public void setListFlight(List<Flight> listFlight) {
		this.listFlight = listFlight;
	}

	public CustomFlightQuery getFlightQuery() {
		return flightQuery;
	}

	public void setFlightQuery(CustomFlightQuery flightQuery) {
		this.flightQuery = flightQuery;
	}

}
