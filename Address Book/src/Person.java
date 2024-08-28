public class Person {
	private int id;
	private String name;
	private String gender;
	private String street;
	private String city;
	private String zip;
	
	public Person(int id, String name, String gender, String street, String city, String zip) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.street = street;
		this.city = city;
		this.zip = zip;

	}
	
	public Person(String name, String gender, String street, String city, String zip) {
		this.name = name;
		this.gender = gender;
		this.street = street;
		this.city = city;
		this.zip = zip;

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


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public static  Person toPerson(String person) {
		String[] values = person.split("##");
		if (values.length > 0) {
			return new Person(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4], values[5]);
		}else {
			return null;
		}
		
	}


	@Override
	public String toString() {
		return id + "##" + name + "##" + gender + "##" + street + "##" + city+ "##" + zip;
	}
	
}
