package edu.mum.cs545.rs;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

@Named
@Path("airline")
public class AirlineWebService {

	@Inject
	private AirlineService airlineService;
	
	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String createAirline(Airline airline) {
		String result  = "";
		
		try {
			airlineService.create(airline);
			result = "Airline created successfully.";
		}
		catch(Exception e) {
			result = "Failed to create airline";
		}
		
		return result;
	}
	
	@Path("delete/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAirline(@PathParam("id") long id) {
		Airline f = null;
		String result = "";
		try {
			f = new Airline();
			f.setId(id);
			f = airlineService.find(f);
			airlineService.delete(f);
			result = "Airline deleted successfully.";
		}
		catch(Exception e) {
			result = "Failed to delete airline";
		}
		
		return result;
	}
	
	@Path("update/{id}/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String updateAirline(@PathParam("id") long id, @PathParam("name") String name) {
		Airline f = null;
		try {
			f = new Airline(name);
			f.setId(id);
			airlineService.update(f);
		}
		catch(Exception e) {
		}
		
		return "success";
	}
	
	@Path("find")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Airline findAirline(Airline airline) {
		Airline a = null;
		
		try {
			a = airlineService.find(airline);
		}
		catch(Exception e) {
		}
		
		return a;
	}
	
	@Path("findbyname")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Airline findAirlineByName(@QueryParam("name") String name) {
		Airline a = null;
		
		try {
			a = airlineService.findByName(name);
		}
		catch(Exception e) {
		}
		
		return a;
	}
	
	@Path("findbyflight")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airline> findAirlinesByFlight(Flight flight) {
		List<Airline> as = null;
		
		try {
			as = airlineService.findByFlight(flight);
		}
		catch(Exception e) {
		}
		
		return as;
	}
	
	@Path("findall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airline> findAllAirlines() {
		List<Airline> as = null;
		
		try {
			as = airlineService.findAll();
		}
		catch(Exception e) {
		}
		
		return as;
	}
}
