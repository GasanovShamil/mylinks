package dao;

public interface StatsDao {
	public Object[][] getEvolutionOverTime(String shortUrl);
	public Object[][] getPasswordUrls();
	public Object[][] getCaptchaUrls();
	public Object[] getBrowserByCountry();
	public Object[] getPopularity();
}
