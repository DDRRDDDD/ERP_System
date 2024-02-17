package customer;

public class Customer {
	
	private int id,gradeId;
	private String password, name,address,phone,gender;
	
	public Customer(int id, int gradeId, String name, String address, String phone, String gender, String password) {
		this.id = id;
		this.gradeId = gradeId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.password = password;
	}
	public Customer(CustomerDTO customerDto) {
		this.id = customerDto.getId();
		this.gradeId = customerDto.getGradeId();
		this.name = customerDto.getName();
		this.address = customerDto.getAddress();
		this.phone = customerDto.getPhone();
		this.gender = customerDto.getGender();
		this.password = customerDto.getPassword();
	}
	public int getId() {
		return id;
	}
	public int getGradeId() {
		return gradeId;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public String getGender() {
		return gender;
	}
	public String getPassword() {
		return password;
	}

}
