package cs545.airline.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cs545.airline.model.Airline;
import edu.mum.gf.workaround.JpaUtil;

@Named
@ApplicationScoped
public class AirlineDao {

//	@PersistenceContext(unitName = "cs545")
//	private static EntityManager entityManager;
//  Couldn't figure out another way to inject the persistence context
	private EntityManager entityManager = JpaUtil.getEntityManager();

	public void create(Airline airline) {
		entityManager.getTransaction().begin();
		entityManager.persist(airline);
		entityManager.getTransaction().commit();
	}

	public void create(String name) {
		Airline airline = new Airline(name);
		entityManager.getTransaction().begin();
		entityManager.persist(airline);
		entityManager.flush();
		entityManager.getTransaction().commit();
	}
	
	public Airline update(Airline airline) {
		entityManager.getTransaction().begin();
		Airline al = entityManager.merge(airline);
		entityManager.getTransaction().commit();
		return al;
	}
	
	public Airline update(long id, String name) {
		Airline airline = new Airline(name);
		airline.setId(id);
		entityManager.getTransaction().begin();
		Airline al = entityManager.merge(airline);
		entityManager.getTransaction().commit();
		return al;
	}

	public void delete(Airline airline) {
		
		Airline toremove = entityManager.find(Airline.class, airline.getId());
		if (toremove != null) {
			entityManager.refresh(toremove);
			entityManager.getTransaction().begin();
			entityManager.remove(toremove);
			entityManager.getTransaction().commit();
		}
	}

	public void delete(long id) {
		Airline airline = new Airline();
		airline.setId(id);
		entityManager.getTransaction().begin();
		entityManager.remove(airline);
		entityManager.getTransaction().commit();
	}
	
	public Airline findOne(long id) {
		return entityManager.find(Airline.class, id);
	}

	public Airline findOneByName(String name) {
		Query query = entityManager.createQuery("select a from Airline a where a.name=:name", Airline.class);
		query.setParameter("name", name);

		return (Airline) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Airline> findByFlight(long flightId) {
		Query query = entityManager
				.createQuery("select distinct a from Airline a join a.flights f where f.id=:flightId", Airline.class);
		query.setParameter("flightId", flightId);

		return query.getResultList();
	}

	public List<Airline> findAll() {
		return entityManager.createQuery("select a from Airline a", Airline.class).getResultList();
	}
}
