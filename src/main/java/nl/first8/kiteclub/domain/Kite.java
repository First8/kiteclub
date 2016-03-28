package nl.first8.kiteclub.domain;

public class Kite {
	private int id;
	private int owner;
	private String name;
	private String image;

	public Kite() {
		// for RESTEasy
	}

	public Kite(int id, int owner, String name, String image) {
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return String.format("(%d) %s (%d)", getId(), getName(), getOwner());
	}
}
