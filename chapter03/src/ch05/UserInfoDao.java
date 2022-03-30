package ch05;

public interface UserInfoDao {
	//data access object

	void insertUserInfo(UserInfo info);
	void updateUserInfo(UserInfo info);
	void deleteUserInfo(String userId);
	
}
