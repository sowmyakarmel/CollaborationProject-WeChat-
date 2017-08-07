package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.FriendsDao;
import com.niit.model.Friends;
import com.niit.model.RegistrationPage;;
@Repository("friendsDao")
@Transactional
public class FriendsDaoImpl implements FriendsDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addFriend(Friends friendShip) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(friendShip);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateFriend(Friends friendShip) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(friendShip);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<Friends> getFriends(Integer id) {
		// TODO Auto-generated method stub
		try {
		return	sessionFactory.getCurrentSession().createQuery("from Friends where userid=:id",Friends.class).setParameter("id", id).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<RegistrationPage> suggestFriends(Integer id) {
		// TODO Auto-generated method stub
		try {
			SQLQuery query= sessionFactory.getCurrentSession().createSQLQuery("select * from RegistrationPage where userid in (select userid from RegistrationPage where userid!=? minus (select userId from Friends where friendId=?"
					+ "union select userId from Friends where userId=?"
					+ "))");
			query.setInteger(0, id);
			query.setInteger(1, id);
			query.setInteger(2, id);
			query.addEntity(RegistrationPage.class);
			return (List<RegistrationPage>)query.list();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	
	public List<Friends> getPendingReqs(Integer userId) {
		try{
			System.out.println("friendsDaouserid:::::::"+userId);

		return sessionFactory.getCurrentSession().createQuery("from Friends where friendId=:id and status=:status",Friends.class).setParameter("id", userId).
	setParameter("status","pending" ).getResultList();
	
	
		
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	
	public Friends getFriend(Integer friendId,Integer userId)
	{
		try {
			return sessionFactory.getCurrentSession().createQuery("from Friends where friendId=:frndId and userId=:userId",Friends.class).setParameter("frndId", friendId)
			.setParameter("userId", userId).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}