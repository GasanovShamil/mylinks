package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "stats", uniqueConstraints = { @UniqueConstraint(columnNames = { "idStat" }) })
public class StatBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idStat", nullable=false, unique=true)
	private int idStat;
	
	@Column(name = "shortUrl", nullable = false, length = 100)
	private String shortUrl;
	
	@Column(name = "ipAddress", nullable = false, length = 15)
	private String ipAddress;
	
	@Column(name = "country", nullable = false, length = 100)
	private String country;
	
	
	public StatBean(){}
	
	public StatBean(String shortUrl,String ipAddress, String country){
		this.setShortUrl(shortUrl);
		this.setIpAddress(ipAddress);
		this.setCountry(country);
	}
	
	public int getIdStat() {
		return idStat;
	}
	public void setIdStat(int idStat) {
		this.idStat = idStat;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
