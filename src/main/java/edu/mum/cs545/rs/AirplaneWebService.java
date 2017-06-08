package edu.mum.cs545.rs;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;

@Named
@Path("airplane")
public class AirplaneWebService {

	@Inject
	private AirplaneService airplaneService;

//	@GET
//	public String helloWorld(@DefaultValue("Gorgeous") @QueryParam("name") String name) {
//		return "Hello " + name + "!";
//	}
//
//	@Path("airline")
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
	public String createAirplane(@QueryParam("serialno") String serialnr, 
								 @QueryParam("model") String model,
								 @QueryParam("capacity") int capacity) {
		String result = "";
		Airplane airplane = airplaneService.findBySrlnr(serialnr);
		if (airplane != null) {
			result = "The name of airplane choosen existed, please use another name.";
		}
		else {
			airplaneService.create(new Airplane(serialnr, model, capacity));
			result = "The No. " + serialnr + " airplane was created successfully.";
		}
		return result;
	}
	
	@Path("delete")
	@GET
	public String deleteAirline(@QueryParam("serialno") String serialnr) {
		String result = "";
		Airplane airplane = airplaneService.findBySrlnr(serialnr);
		if (airplane == null) {
			result = "The No. " + serialnr + " airplane was not found.";
		}
		else {
			airplaneService.delete(airplane);
			result = "The No. " + serialnr + " airplane was deleted successfully.";
		}
		return result;
	}
}
