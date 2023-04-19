package user;

public class UsersDTO {
	
	private int idx;
	private String id;
	private String name;
	private String nickname;
	private String email;
	private String address;
	private String phone;
	private int getIdx() {
		return idx;
	}
	private void setIdx(int idx) {
		this.idx = idx;
	}
	private String getId() {
		return id;
	}
	private void setId(String id) {
		this.id = id;
	}
	private String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	private String getNickname() {
		return nickname;
	}
	private void setNickname(String nickname) {
		this.nickname = nickname;
	}
	private String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	private String getAddress() {
		return address;
	}
	private void setAddress(String address) {
		this.address = address;
	}
	private String getPhone() {
		return phone;
	}
	private void setPhone(String phone) {
		this.phone = phone;
	}

}
