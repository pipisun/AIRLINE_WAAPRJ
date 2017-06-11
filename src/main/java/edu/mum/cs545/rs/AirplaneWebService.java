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
import cs545.airline.model.Flight;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.FlightService;

@Named
@Path("airplane")
public class AirplaneWebService {

	@Inject
	private AirplaneService airplaneService;

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String createAirplane(Airplane airplane) {
		String result  = "";
		
		try {
			airplaneService.create(airplane);
			result = "Airplane created successfully.";
		}
		catch(Exception e) {
			result = "Failed to create airplane:" + e.getMessage();
		}
		
		return result;
	}
	
	@Path("delete/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAirplane(@PathParam("id") long id) {
		String result  = "";
		Airplane f = null;
		
		try {
			f = new Airplane();
			f.setId(id);
			f = airplaneService.find(f);
			airplaneService.delete(f);
			
			result = "Airplane deleted successfully.";
		}
		catch(Exception e) {
			result = "Failed to delete airplane" + e.getMessage();
		}
		
		return result;
	}
	
	
	@Path("update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateAirplane(Airplane airplane) {
		String result = "";
		try {
			airplaneService.update(airplane);
			result = "Updated successfully";
		}
		catch(Exception e) {
			result = "Failed to update";
		}
		
		return result;
	}
	
	@Path("findbyserialno")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Airplane findAirlineBySerialNo(@QueryParam("serialno") String serialnr) {
		Airplane ap = null;
		
		try {
			ap = airplaneService.findBySrlnr(serialnr);
		}
		catch(Exception e) {
		}
		
		return ap;
	}
	
	@Path("findbyflight")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airplane> findAirlinesByFlightNo(Flight flight) {
		List<Airplane> aps = null;
		
		try {
			aps = airplaneService.findByFlight(flight);
		}
		catch(Exception e) {
		}
		
		return aps;
	}
	
	@Path("findbymodel")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airplane> findAirlinesByModel(@QueryParam("model") String model) {
		List<Airplane> aps = null;
		
		try {
			aps = airplaneService.findByModel(model);
		}
		catch(Exception e) {
		}
		
		return aps;
	}
	
	@Path("findall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airplane> findAllAirlines() {
		List<Airplane> aps = null;
		
		try {
			aps = airplaneService.findAll();
		}
		catch(Exception e) {
		}
		
		return aps;
	}
}
