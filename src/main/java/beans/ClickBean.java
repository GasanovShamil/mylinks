package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "clicks", uniqueConstraints = { @UniqueConstraint(columnNames = { "clickId" }) })
public class ClickBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="clickId", nullable=false, unique=true)
	private int statId;
	
	@Column(name = "shortUrl", nullable = false, length = 100)
	private String shortUrl;
	
	@Column(name = "ipAddress", nullable = false, length = 15)
	private String ipAddress;
	
	@Column(name = "country", nullable = true, length = 100)
	private String country;
	
	@Column(name = "visitDate", nullable = false)
	private Date visitDate;
	
	@Column(name = "browser", nullable = true, length = 100)
	private String browser;
	
	@Column(name = "os", nullable = true, length = 100)
	private String os;	
	
	
	public ClickBean(){}
	
	public ClickBean(String shortUrl,String ipAddress, String country, String browser, String os){
		this.setShortUrl(shortUrl);
		this.setIpAddress(ipAddress);
		this.setCountry(country);
		this.visitDate = new Date();
		this.setBrowser(browser);
		this.setOs(os);
		//String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
	}
	
	public int getStatId() {
		return statId;
	}
	public void setStatId(int statId) {
		this.statId = statId;
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

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}
