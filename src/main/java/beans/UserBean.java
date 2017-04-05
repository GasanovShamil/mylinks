package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="users", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"userId", "email"})})
public class UserBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId", nullable=false, unique=true)
	private int userId;
	
	@Column(name="name", nullable=false, length=100)
	private String name;	
	
	@Column(name="surname", nullable=false, length=100)
	private String surname;	
	
	@Column(name="email", nullable=false, unique=true, length=100)
	private String email;	
	
	@Column(name="password", nullable=false, length=100)
	private String password;

	@Column(name="confirmToken", nullable=false, length=100)
	private String confirmToken;
	
	@Column(name="isConfirmed", nullable=false)
	private boolean isConfirmed;
	
	@Column(name="isAdmin", nullable=false)
	private boolean isAdmin;
	
	public UserBean() {
	}

	public UserBean(String name, String surname, String email, String password,String confirmToken,boolean isConfirmed, boolean isAdmin){
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.confirmToken = confirmToken;
		this.isConfirmed = isConfirmed;
		this.isAdmin = isAdmin;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getConfirmToken() {
		return confirmToken;
	}

	public void setConfirmToken(String confirmToken) {
		this.confirmToken = confirmToken;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

}
