package customer;

public class CustomerDTO {
	
	private int id,gradeId;
	private String password, name,address,phone,gender;
	
	public CustomerDTO(int id, int gradeId, String name, String address, String phone, String gender, String password) {
		this.id = id;
		this.gradeId = gradeId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.password = password;
	}
	
	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.gradeId = customer.getGradeId();
		this.name = customer.getName();
		this.address = customer.getAddress();
		this.phone = customer.getPhone();
		this.gender = customer.getGender();
		this.password = customer.getPassword();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
