package edu.mum.cs545.rs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named
@Path("flight")
public class FlightWebService {

	@Inject
	private FlightService flightService;

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String createFlight(Flight flight)	{
		String result  = "";
				
		try {
			flightService.create(flight);
			result = "Flight created successfully.";
		}
		catch(Exception e) {
			result = "Failed to create flight" + e.getMessage();
		}
		
		return result;
	}
	
	@Path("delete/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteFlight(@PathParam("id") long id) {
		String result  = "";
		Flight flt = null;
		try {
			flt = new Flight();
			flt.setId(id);
			flt = flightService.find(flt);
			flightService.delete(flt);
			result = "Flight deleted successfully.";
		}
		catch(Exception e) {
			result = "Failed to delete flight";
		}
		
		return result;
	}
	
	
	@Path("update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateFlight(Flight flight) {
		String result = "";
		try {
			flightService.update(flight);
			result = "Updated successfully";
		}
		catch(Exception e) {
			result = "Updated failure";
		}
		
		return result;
	}
	
	@Path("find")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Flight findFlight(Flight flight) {
		Flight f = null;
		
		try {
			f = flightService.find(flight);
		}
		catch(Exception e) {
		}
		
		return f;
	}
	
	@Path("findbyno")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findFlightByNumber(@QueryParam("flightno") String flightno) {
		List<Flight> flights = null;
		
		try {
			flights = flightService.findByNumber(flightno);
		}
		catch(Exception e) {
		}
		
		return flights;
	}
	
	@Path("findbyairline")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findFlightByAirline(Airline airline) {
		List<Flight> flights = null;
		
		try {
			flights = flightService.findByAirline(airline);
		}
		catch(Exception e) {
		}
		
		return flights;
	}
	
	@Path("findbyairport")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findFlightByOrigin(Airport airport) {
		List<Flight> flights = null;
		
		try {
			flights = flightService.findByOrigin(airport);
		}
		catch(Exception e) {
		}
		
		return flights;
	}
	
	@Path("findbydestination")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findFlightByDestination(Airport airport) {
		List<Flight> flights = null;
		
		try {
			flights = flightService.findByDestination(airport);
		}
		catch(Exception e) {
		}
		
		return flights;
	}
	
	@Path("findbyarrivalairplane")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findFlightByArrival(Airplane airplane) {
		List<Flight> flights = null;
		
		try {
			flights = flightService.findByArrival(airplane);
		}
		catch(Exception e) {
		}
		
		return flights;
	}
	
	@Path("findbyarrivaltime")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findFlightByArrival(@QueryParam("flightno") String strDatetime) {
		List<Flight> flights = null;
		
		try {
			SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
			flights = flightService.findByArrival(sf.parse(strDatetime));
		}
		catch(Exception e) {
		}
		
		return flights;
	}
	
	@Path("findbyarrivalbetween")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findFlightByArrivalBetween(@QueryParam("datefrom") String dateFrom,
											 @QueryParam("dateto") String dateTo) {
		List<Flight> flights = null;
		
		try {
			SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
			flights = flightService.findByArrivalBetween(sf.parse(dateFrom),sf.parse(dateTo));
		}
		catch(Exception e) {
		}
		
		return flights;	
	}
	
	@Path("findbydeparturetime")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findFlightByDeparture(@QueryParam("datedeparture") String dateDeparture) {
		List<Flight> flights = null;
		
		try {
			SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
			flights = flightService.findByDeparture(sf.parse(dateDeparture));
		}
		catch(Exception e) {
		}
		
		return flights;
	}
	
	@Path("findbydeparturebetween")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findFlightByDepartureBetween(@QueryParam("datefrom") String dateFrom,
											 @QueryParam("dateto") String dateTo) {
		List<Flight> flights = null;
		
		try {
			SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
			flights = flightService.findByDepartureBetween(sf.parse(dateFrom),sf.parse(dateTo));
		}
		catch(Exception e) {
		}
		
		return flights;	
	}
	
	@Path("findall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findAll() {
		List<Flight> flights = null;
		
		try {
			flights = flightService.findAll();
		}
		catch(Exception e) {
		}
		
		return flights;	
	}
}
