package edu.mum.cs545.rs;

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

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirportService;

@Named
@Path("airport")
public class AirportWebService {

	@Inject
	private AirportService airportService;

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String createAirport(Airport airport) {
		String result  = "";
		
		try {
			airportService.create(airport);
			result = "Airport created successfully.";
		}
		catch(Exception e) {
			result = "Failed to create airport";
		}
		
		return result;
	}
	
	@Path("delete/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAirport(@PathParam("id") long id) {
		String result  = "";
		Airport ap = null;
		try {
			ap = new Airport();
			ap.setId(id);
			ap = airportService.find(ap);
			airportService.delete(ap);
			result = "Airport deleted successfully.";
		}
		catch(Exception e) {
			result = "Failed to delete airport";
		}
		
		return result;
	}
	
	
	@Path("update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateAirport(Airport airport) {
		String result = "";
		try {
			airportService.update(airport);
			result = "Updated successfully";
		}
		catch(Exception e) {
			result = "Updated failure";
		}
		
		return result;
	}
	
	@Path("findbycode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Airport findAirportByCode(@QueryParam("airportcode") String airportcode) {
		Airport a = null;
		
		try {
			a = airportService.findByCode(airportcode);
		}
		catch(Exception e) {
		}
		
		return a;
	}
	
	@Path("findbyarrival")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> findAirportByArrival(Flight flight) {
		List<Airport> as = null;
		
		try {
			as = airportService.findByArrival(flight);
		}
		catch(Exception e) {
		}
		
		return as;
	}
	
	@Path("findbydeparture")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> findAirportByDeparture(Flight flight) {
		List<Airport> as = null;
		
		try {
			as = airportService.findByDeparture(flight);
		}
		catch(Exception e) {
		}
		
		return as;
	}
	
	@Path("findbycity")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> findByCity(@QueryParam("city") String city) {
		List<Airport> as = null;
		
		try {
			as = airportService.findByCity(city);
		}
		catch(Exception e) {
		}
		
		return as;
	}
	
	@Path("findbycountry")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> findByCountry(@QueryParam("country") String country) {
		List<Airport> as = null;
		
		try {
			as = airportService.findByCountry(country);
		}
		catch(Exception e) {
		}
		
		return as;
	}
	
	@Path("findbyname")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> findByName(@QueryParam("name") String name) {
		List<Airport> as = null;
		
		try {
			as = airportService.findByName(name);
		}
		catch(Exception e) {
		}
		
		return as;
	}
	
	@Path("findall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> findAll() {
		List<Airport> as = null;
		
		try {
			as = airportService.findAll();
		}
		catch(Exception e) {
		}
		
		return as;
	}
}
