package cs545.airline.model;

public class CustomAirline {
	
	private long id;
	private String name;

	/* Constructors */
	public CustomAirline() {
	}

	public CustomAirline(String name) {
		this.name = name;
	}

	/* Getters & Setters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
