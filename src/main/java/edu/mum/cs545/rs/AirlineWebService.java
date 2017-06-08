package edu.mum.cs545.rs;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;

@Named
@Path("airline")
public class AirlineWebService {

	@Inject
	private AirlineService airlineService;
	private FlightService flightService;
	
//	@GET
//	public String helloWorld(@DefaultValue("Gorgeous") @QueryParam("name") String name) {
//		return "Hello " + name + "!";
//	}

//	@Path("create")
//	@GET
//	public String getAirlineTest() {
//		String result = "Nil!";
//		Airline airline = airlineService.findByName("oneworld");
//		if (airline != null) {
//			result = "This is an airline: " + airline.getName();
//		}
//		return result;
//	}
	
	@Path("create")
	@GET
	public String createAirline(@QueryParam("name") String name) {
		String result = "";
		Airline airline = airlineService.findByName(name);
		if (airline != null) {
			result = "The name of airline choosen existed, please use another name.";
		}
		else {
			airlineService.create(new Airline(name));
			result = "The " + name + " airline was created successfully.";
		}
		return result;
	}
	
	@Path("delete")
	@GET
	public String deleteAirline(@QueryParam("name") String name) {
		String result = "";
		Airline airline = airlineService.findByName(name);
		if (airline == null) {
			result = "The " + name + " airline was not found.";
		}
		else {
			airlineService.delete(airline);
			result = "The " + name + " airline was deleted successfully.";
		}
		return result;
	}
	
	@Path("update")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Airline updateAirline(@QueryParam("name") String name) {
		String result = "";
		Airline airline = airlineService.findByName(name);
		if (airline == null) {
			result = "The " + name + " airline was not found.";
		}
		else {
			result = "The " + name + " airline was updated successfully.";
		}
		return airlineService.update(airline);
	}
	
	@Path("find")
	@GET
	public String findAirline(@QueryParam("name") String name) {
		String result = "";
		Airline airline = airlineService.findByName(name);
		if (airline == null) {
			result = "The " + name + " airline was not found.";
		}
		else {
			
			result = "The " + name + " airline was found.";
		}
		return result;
	}
	
	@Path("findbyname")
	@GET
	public String findAirlineByName(@QueryParam("name") String name) {
		String result = "";
		Airline airline = airlineService.findByName(name);
		if (airline == null) {
			result = "The " + name + " airline was not found.";
		}
		else {
			result = "The " + name + " airline was found.";
		}
		return result;
	}
	
	@Path("findbyflightno")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airline> findAirlinesByFlight(@QueryParam("flightno") String flightno) {
		List<Airline> airlines = new ArrayList<>();
		List<Flight> flights = flightService.findByNumber(flightno);
		for(Flight flight : flights) {
			airlines.add(flight.getAirline());
		}
		return airlines;
	}
	
	@Path("findall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airline> findAllAirlines() {
		return airlineService.findAll();
	}
}
