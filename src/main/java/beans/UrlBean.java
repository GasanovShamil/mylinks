package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "url", uniqueConstraints = { @UniqueConstraint(columnNames = { "shortUrl" }) })
public class UrlBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "shortUrl", nullable = false, unique = true, length = 100)
	private String shortUrl;

	@Column(name = "longUrl", length = 500, nullable = false)
	private String longUrl;

	@Column(name = "createDate", nullable = false)
	private Timestamp createDate;

	@Column(name = "startDate", nullable = true)
	private Timestamp startDate;

	@Column(name = "expireDate", nullable = true)
	private Timestamp expireDate;

	@Column(name = "password", nullable = true)
	private String password;

	@Column(name = "captcha", nullable = false)
	private boolean captcha;

	@Column(name = "nbClicks", nullable = true)
	private Integer nbClicks;

	@Column(name = "totalClicks", nullable = true)
	private Integer totalClicks;

	@Column(name = "userId", nullable = true)
	private Integer userId;

	@Column(name = "generic", nullable = false)
	private boolean generic;

	public UrlBean() {
	}

	public UrlBean(String shortUrl, String longUrl, Timestamp createDate, Timestamp startDate, Timestamp expireDate,
			String password, boolean captcha, Integer nbClicks, Integer totalClicks, Integer userId, boolean generic) {
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.createDate = createDate;
		this.startDate = startDate;
		this.expireDate = expireDate;
		this.password = password;
		this.captcha = captcha;
		this.nbClicks = nbClicks;
		this.totalClicks = totalClicks;
		this.userId = userId;
		this.generic = generic;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getNbClicks() {
		return nbClicks;
	}

	public void setNbClicks(Integer nbClicks) {
		this.nbClicks = nbClicks;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isGeneric() {
		return generic;
	}

	public void setGeneric(boolean generic) {
		this.generic = generic;
	}

	public boolean dateExpired() {
		boolean res = false;
		if (startDate != null && this.startDate.after(new Timestamp(new Date().getTime()))) {
			res = true;
		} else if (expireDate != null && this.expireDate.before(new Timestamp(new Date().getTime()))) {
			res = true;
		}
		// return (expireDate == null)?false:((this.expireDate.before(new
		// Timestamp(new Date().getTime())))||( this.startDate.after(new
		// Timestamp(new Date().getTime()))));
		// return (expireDate == null)?false:this.expireDate.before(new
		// Timestamp(new Date().getTime()));
		return res;
	}

	public void increaseNbClicks() {
		this.nbClicks++;
	}

	public String getHtmlId() {
		return "#" + shortUrl;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public boolean isCaptcha() {
		return captcha;
	}

	public void setCaptcha(boolean captcha) {
		this.captcha = captcha;
	}

	public Integer getTotalClicks() {
		return totalClicks;
	}

	public void setTotalClicks(Integer totalClicks) {
		this.totalClicks = totalClicks;
	}
	
	public String getCaptchaIcon() {
		return captcha ? "<span class=\"glyphicon glyphicon-ok green\"></span>" : "<span class=\"glyphicon glyphicon-remove red\"></span>";
	}
}
