package cs545.airline.dao;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import edu.mum.gf.workaround.JpaUtil;

@Named
@ApplicationScoped
public class FlightDao {

//	@PersistenceContext(unitName = "cs545")
//	private static EntityManager entityManager;
//  Couldn't figure out another way to inject the persistence context
	private EntityManager entityManager = JpaUtil.getEntityManager();

	public void create(Flight flight) {
		entityManager.getTransaction().begin();
		entityManager.persist(flight);
		entityManager.getTransaction().commit();
	}

	public Flight update(Flight flight) {
		entityManager.getTransaction().begin();
		Flight f = entityManager.merge(flight);
		entityManager.getTransaction().commit();
		return f;
	}

	public void delete(Flight flight) {
		entityManager.getTransaction().begin();
		entityManager.remove(flight);
		entityManager.getTransaction().commit();
	}

	public Flight findOne(long id) {
		return entityManager.find(Flight.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByFlightnr(String flightnr) {
		Query query = entityManager.createQuery("select f from Flight f where f.flightnr=:flightnr", Flight.class);
		query.setParameter("flightnr", flightnr);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByOrigin(long airportId) {
		Query query = entityManager.createQuery("select f from Flight f join f.origin o where o.id=:airportId",
				Flight.class);
		query.setParameter("airportId", airportId);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByDestination(long airportId) {
		Query query = entityManager.createQuery("select f from Flight f join f.destination d where d.id=:airportId",
				Flight.class);
		query.setParameter("airportId", airportId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByAirplane(long airplaneId) {
		Query query = entityManager.createQuery("select f from Flight f join f.airplane a where a.id=:airplaneId",
				Flight.class);
		query.setParameter("airplaneId", airplaneId);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByAirline(long airlineId) {
		Query query = entityManager.createQuery("select f from Flight f join f.airline a where a.id=:airlineId",
				Flight.class);
		query.setParameter("airlineId", airlineId);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByDeparture(Date date, Date time) {
		Query query = entityManager.createQuery(
				"select distinct f from Flight f where f.departureDate=:departureDate and f.departureTime=:departureTime",
				Flight.class);
		query.setParameter("departureDate", date, TemporalType.DATE);
		query.setParameter("departureTime", time, TemporalType.TIME);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByDepartureBetween(Date dateFrom, Date dateTo, Date timeFrom, Date timeTo) {
		Query query = entityManager.createQuery(
				"select distinct f from Flight f where f.arrivalDate between :DateFrom and :DateTo and f.arrivalTime beteween :TimeFrom and :TimeTo",
				Flight.class);
		query.setParameter("DateFrom", dateFrom, TemporalType.DATE);
		query.setParameter("TimeFrom", dateFrom, TemporalType.TIME);
		query.setParameter("DateTo", dateTo, TemporalType.DATE);
		query.setParameter("TimeTo", dateTo, TemporalType.TIME);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByArrival(Date date, Date time) {
		Query query = entityManager.createQuery(
				"select distinct f from Flight f where f.arrivalDate=:arrivalDate and f.arrivalTime=:arrivalTime",
				Flight.class);
		query.setParameter("arrivalDate", date, TemporalType.DATE);
		query.setParameter("arrivalTime", time, TemporalType.TIME);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findByArrivalBetween(Date dateFrom, Date dateTo, Date timeFrom, Date timeTo) {
		Query query = entityManager.createQuery(
				"select distinct f from Flight f where f.arrivalDate between :arrivalDateFrom and :arrivalDateTo and f.arrivalTime beteween :arrivalTimeFrom and :arrivalTimeTo",
				Flight.class);
		query.setParameter("arrivalDateFrom", dateFrom, TemporalType.DATE);
		query.setParameter("arrivalTimeFrom", dateFrom, TemporalType.TIME);
		query.setParameter("arrivalDateTo", dateTo, TemporalType.DATE);
		query.setParameter("arrivalTimeTo", dateTo, TemporalType.TIME);

		return query.getResultList();
	}

	public List<Flight> findAll() {
		return entityManager.createQuery("select f from Flight f", Flight.class).getResultList();
	} 
	
	@SuppressWarnings("unchecked")
	public List<Flight> queryFlight(long airlineid, long originairportid, long destairportid) {
		Query query = null;
		String strQuery = "";
		if(airlineid != 0 && originairportid == 0 && destairportid == 0) {
			strQuery = "SELECT f FROM Flight f where f.airline.id=:airlineid";
			query = entityManager.createQuery(strQuery, Flight.class);
			query.setParameter("airlineid", airlineid);
		}
		else if(airlineid == 0 && originairportid != 0 && destairportid == 0) {
			strQuery = "SELECT f FROM Flight f where f.origin.id=:originairportid";
			query = entityManager.createQuery(strQuery, Flight.class);
			query.setParameter("originairportid", originairportid);
		}
		else if(airlineid == 0 && originairportid == 0 && destairportid != 0) {
			strQuery = "SELECT f FROM Flight f where f.destination.id=:destairportid";
			query = entityManager.createQuery(strQuery, Flight.class);
			query.setParameter("destairportid", destairportid);
		}
		else if(airlineid != 0 && originairportid != 0 && destairportid != 0) {
			strQuery = "SELECT f FROM Flight f where f.airline.id=:airlineid and f.origin.id=:originairportid and f.destination.id=:destairportid";
			query = entityManager.createQuery(strQuery, Flight.class);
			query.setParameter("airlineid", airlineid);
			query.setParameter("originairportid", originairportid);
			query.setParameter("destairportid", destairportid);
		}
		
		List<Flight> fltList = query.getResultList();
		
//		if(fltList.size() == 1)
//			fltList.add((Flight)query.getSingleResult());
		
		return fltList;
	} 
}
