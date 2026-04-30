package draft1;

public class Officer {
	private int id;
	private String name;

	public Officer(String name) {
		super();
		this.name = name;
	}

	public Officer(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
		return "Officer [id=" + id + ", name=" + name + "]";
	}

}
