package cs545.airline.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Flight;
import cs545.airline.model.Airline;
import cs545.airline.model.CustomAirline;
import cs545.airline.model.CustomFlightQuery;

@Named
@ViewScoped
public class CustomAirlineService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private AirlineService airlineService;

	private Airline airline;
	private List<Airline> listAirline;
	private String name;
	private long id;

 
	@PostConstruct
	public void findAllAirline() {
		airline = new Airline();
 		listAirline = airlineService.findAll();
	}
	
	public void addAirline() {
		airline.setId(System.currentTimeMillis());
		airline.setName(name);
		
		airlineService.create(airline);
		listAirline = airlineService.findAll();
	}
	
	public String deleteAirline(long id){
		airline.setId(id);
		airlineService.delete(airline);
		listAirline = airlineService.findAll();
		return "toAirlineList";
	}
	
	public String updatePage(long id){
		this.id = id;
		listAirline = airlineService.findAll();
		return "toAirlineList";
	}
	
	public String updateAirline(long id,String name){
		airline.setId(id);
		airline.setName(name);
		airlineService.update(airline);
		this.id = 0;
		return "toAirlineList";
	}
	

	public List<Airline> getListAirline() {
		return listAirline;
	}

	public void setListAirline(List<Airline> listAirline) {
		this.listAirline = listAirline;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
