package com.niit.dao;

import java.util.List;


import com.niit.model.Friends;
import com.niit.model.RegistrationPage;


public interface FriendsDao {

	boolean addFriend(Friends friends);
	boolean updateFriend(Friends friends);
	List<Friends> getFriends(Integer id); 
	List<RegistrationPage> suggestFriends(Integer id);
	public List<Friends> getPendingReqs(Integer userId);
	public Friends getFriend(Integer friendId,Integer userId);
	
}
