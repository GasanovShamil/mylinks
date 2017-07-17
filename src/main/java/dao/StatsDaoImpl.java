package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import utils.HibernateUtil;

@Named
@RequestScoped
public class StatsDaoImpl implements StatsDao {

	@Inject
	HibernateUtil hibernateUtil;

	private SessionFactory sessionFactory;
	private Session session;

	@PostConstruct
	public void init() {
		sessionFactory = hibernateUtil.getSessionFactory();
	}

	@PreDestroy
	public void destroy() {
	}

	@Override
	public Object[][] getEvolutionOverTime(String shortUrl) {
		Object[][] result = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.createSQLQuery("");

			String sql = "SELECT visitDate, Count(clickId) FROM clicks WHERE shortUrl = :url GROUP BY visitDate ORDER BY visitDate";
			Query query = session.createSQLQuery(sql).setParameter("url", shortUrl);
			List<Object[]> rows = query.list();
			session.getTransaction().commit();

			result = new Object[rows.size()][2];

			for (int i = 0; i < rows.size(); i++) {
				result[i][0] = (rows.get(i)[0]).toString();
				result[i][1] = (rows.get(i)[1]).toString();
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	@Override
	public Object[][] getPasswordUrls() {
		Object[][] result = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.createSQLQuery("");

			String sql = "SELECT COUNT(password), COUNT(shortUrl) FROM url";
			Query query = session.createSQLQuery(sql);
			List<Object[]> rows = query.list();
			session.getTransaction().commit();

			int securised = Integer.parseInt((rows.get(0)[0]).toString());
			int total = Integer.parseInt((rows.get(0)[1]).toString());

			result = new Object[][] { { "PW", securised }, { "No", total - securised } };
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	@Override
	public Object[][] getCaptchaUrls() {
		Object[][] result = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.createSQLQuery("");

			String sql = "SELECT (SELECT COUNT(captcha) FROM url WHERE captcha = 1), COUNT(shortUrl) FROM url";
			Query query = session.createSQLQuery(sql);
			List<Object[]> rows = query.list();
			session.getTransaction().commit();

			int securised = Integer.parseInt((rows.get(0)[0]).toString());
			int total = Integer.parseInt((rows.get(0)[1]).toString());

			result = new Object[][] { { "CP", securised }, { "No", total - securised } };
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	@Override
	public Object[] getBrowserByCountry() {
		return new Object[] {
				getCountriesFromBrowser("Chrome"),
				getCountriesFromBrowser("Mozilla"),
				getCountriesFromBrowser("Safari"),
				getCountriesFromBrowser("Internet Explorer")
		};
	}

	private Object[][] getCountriesFromBrowser(String browser) {
		Object[][] result = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.createSQLQuery("");

			String sqlCount = "SELECT COUNT(clickId) FROM clicks c1 WHERE browser = :b AND c2.country = c1.country";
			String sqlQuery = "SELECT DISTINCT country, (" + sqlCount + ") FROM clicks c2 WHERE country IS NOT NULL ORDER BY country DESC";
			Query query = session.createSQLQuery(sqlQuery).setParameter("b", browser);
			List<Object[]> rows = query.list();
			session.getTransaction().commit();

			result = new Object[rows.size()][2];

			for (int i = 0; i < rows.size(); i++) {
				result[i][0] = rows.get(i)[1].toString();
				result[i][1] = rows.get(i)[0].toString();
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	@Override
	public Object[] getPopularity() {
		Object[] result = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.createSQLQuery("");

			String sql = "SELECT shortUrl, longUrl,(SELECT COUNT(c.clickId) FROM clicks c WHERE c.shortUrl = u.shortUrl) AS nb FROM url u ORDER BY nb DESC LIMIT 10";
			Query query = session.createSQLQuery(sql);
			List<Object[]> rows = query.list();
			session.getTransaction().commit();
			
			result = new Object[rows.size()];
			
			for (int i = 0; i < rows.size(); i++) {
				HashMap map = new HashMap();
				map.put("Rank", i + 1);
				map.put("Short url", (rows.get(i)[0]).toString());
				map.put("Long url", (rows.get(i)[1]).toString());
				map.put("Number of visits", (rows.get(i)[2]).toString());
				map.put("Most visited by", getMostVisitedBy((rows.get(i)[0]).toString()));
				result[i] = map;
			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	private String getMostVisitedBy(String country) {
		
		String result = "";

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.createSQLQuery("");

			String sql = "SELECT country, COUNT(clickId) AS nb FROM clicks WHERE country IS NOT NULL AND shortUrl = :c GROUP BY country ORDER BY nb DESC LIMIT 1";
			Query query = session.createSQLQuery(sql).setParameter("c", country);
			List<Object[]> rows = query.list();
			session.getTransaction().commit();
			
			result = rows.isEmpty() ? "" : rows.get(0)[0].toString();

//			List<String> temp = new ArrayList<String>();
//			
//			if (!rows.isEmpty()) {
//				int i = 0, max = (int)rows.get(i)[1];
//				while (max == (int)rows.get(i)[1] && i < rows.size()) {
//					temp.add(rows.get(i)[0].toString());
//					i++;
//				}
//				
//				for (String s : temp) {
//				    result += s + ",";
//				}
//			}
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(country);
		System.out.println(result);

		return result;
	}
}
