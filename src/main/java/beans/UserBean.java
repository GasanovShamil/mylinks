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
	   uniqueConstraints={@UniqueConstraint(columnNames={"userId", "login"})})
public class UserBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userId", nullable=false, unique=true)
	private int userId;
	
	@Column(name="name", nullable=false, length=100)
	private String name;	
	
	@Column(name="surname", nullable=false, length=100)
	private String surname;	
	
	@Column(name="login", nullable=false, unique=true, length=100)
	private String login;	
	
	@Column(name="password", nullable=false, length=100)
	private String password;

	@Column(name="isAdmin", nullable=false)
	private boolean isAdmin;
	
	public UserBean() {
	}

	public UserBean(String name, String surname, String login, String password, boolean isAdmin){
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

}
