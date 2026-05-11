package classes;

public class Officer {
	private int id;
	private String name;

	public Officer() {
	}

	public Officer(String name) {
		this();
		setName(name);
	}

	public Officer(int id, String name) {
		this(name);
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Officer " + name + " - [ID: " + id + "]";
	}
}
