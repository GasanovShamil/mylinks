package dao;

import beans.StatBean;

public interface StatDao {
	
	boolean putStat(StatBean bean);

	StatBean getStat(String shortUrl);

	void updateStat(StatBean bean);
}
