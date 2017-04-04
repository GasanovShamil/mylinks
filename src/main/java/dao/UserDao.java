package dao;

import beans.UserBean;

public interface UserDao {

	UserBean getUser(String login);
	boolean putUser(UserBean bean);
	void updateUser(UserBean bean);
}