package dao;

import beans.UserBean;

public interface UserDao {

	UserBean getUser(String email);
	boolean putUser(UserBean bean);
	void updateUser(UserBean bean);
}