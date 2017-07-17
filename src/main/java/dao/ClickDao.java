package dao;

import beans.ClickBean;

public interface ClickDao {
	
	boolean putStat(ClickBean bean);

	ClickBean getStat(String shostatIdrtUrl);

	void updateStat(ClickBean bean);
}
