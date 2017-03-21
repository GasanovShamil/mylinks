package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class UrlBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String shortUrl;
	private String longUrl;
	private Timestamp createDate;
	private Timestamp expireDate;
	private Integer nbClicks;
	private Integer userId;

	public UrlBean() {}
	
	public UrlBean(String shortUrl,String longUrl, Timestamp createDate, Timestamp expireDate, Integer nbClicks, Integer userId){
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.createDate = createDate;
		this.expireDate = expireDate;
		this.nbClicks = nbClicks;
		this.userId = userId;
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

}
