package cs545.airline.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;
import cs545.airline.model.CustomFlightQuery;
import cs545.airline.model.Flight;

@Named
@ApplicationScoped
@Transactional
public class AirlineService {

	// These services should be evaluated to reconsider which methods should be
	// public

	@Inject
	private AirlineDao airlineDao;
	
	private Airline airlineQuery;
	
//	public Airline getAirlineQuery() {
//		Airline a = new Airline();
//		return a;
//	}
//	
//	public void setFlightQuery(Airline airlineQuery) {
//		this.airlineQuery = airlineQuery;
//	}
	
	public void create(Airline airport) {
		airlineDao.create(airport);
	}

	public void delete(Airline airport) {
		airlineDao.delete(airport);
	}

	public Airline update(Airline airport) {
		return airlineDao.update(airport);
	}

	public Airline find(Airline airport) {
		return airlineDao.findOne(airport.getId());
	}

	public Airline findByName(String name) {
		return airlineDao.findOneByName(name);
	}

	public List<Airline> findByFlight(Flight flight) {
		return airlineDao.findByFlight(flight.getId());
	}

	public List<Airline> findAll() {
		return airlineDao.findAll();
	}
	
	public void createByName(String name) {
		airlineDao.create(name);
	}
	
	public Airline updateNameById(long id, String name) {
		return airlineDao.update(id, name);
	}
	
	public void deleteById(long id) {
		airlineDao.delete(id);
	}
	
	public Airline findOne(long id) {
		return airlineDao.findOne(id);
	}
	
}
