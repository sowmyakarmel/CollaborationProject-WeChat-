package com.niit.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.search.IntegerComparisonTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendsDao;
import com.niit.dao.RegistrationDao;
import com.niit.dao.RegistrationDaoImpl;
import com.niit.model.Friends;
import com.niit.model.RegistrationPage;;

@RestController
public class FriendRestController {
	
	@Autowired
	FriendsDao friendsDao;
	
	@Autowired
	RegistrationDao regDao;
	
     @PostMapping("/addfriend")
     public ResponseEntity<List<RegistrationPage>> addFriend(@RequestBody Friends friends)
     {
    	  System.out.println("friend id :::::::::::"+friends.getFriendId());
    	   friends.setStatus("pending");
    	   friendsDao.addFriend(friends);
    	   List users=friendsDao.suggestFriends(friends.getUserId());
    	   return new ResponseEntity<List<RegistrationPage>>(users,HttpStatus.OK);
    	   
     }
     
     @RequestMapping("updatefriend/{fid}/{uid}")
     public ResponseEntity<List<Friends>> update(@RequestBody String status,@PathVariable("fid") Integer fid,@PathVariable("uid") Integer uid)
     {
    	 Friends friend=friendsDao.getFriend(fid, uid);
    	 friend.setStatus("approved");
    	 friendsDao.updateFriend(friend);
    	 List<Friends> friends=friendsDao.getFriends(uid);
    	 return new ResponseEntity<List<Friends>>(friends,HttpStatus.ACCEPTED);
     }
    
     
     
     @PostMapping("/getfriends")
     public ResponseEntity<Collection<RegistrationPage>> getFriends(@RequestBody RegistrationPage user)
     {
    	 System.out.println(user.getUserid());
    	List<Friends> friends= friendsDao.getFriends(user.getUserid());
    	Set<RegistrationPage> friendList=new HashSet();
    	for(Friends f:friends)
    	{
    		RegistrationPage fuser=regDao.getUserId(f.getFriendId());
    		friendList.add(fuser);
    	}
    	for(Friends f:friends)
    	{
    		RegistrationPage fuser=regDao.getUserId(f.getUserId());
    		friendList.add(fuser);
    	}
    	 System.out.println("firends list"+friends);
    	 
    	return new ResponseEntity<Collection<RegistrationPage>>(friendList, HttpStatus.OK) ;
     }
     
     
     @GetMapping("/suggestedFriends/{id}")
     public ResponseEntity<List<RegistrationPage>> suggestedFriends(@PathVariable("id") Integer id)
     {
    	 System.out.println("user Id for suggestedFriends:::::"+id);
    	List<RegistrationPage> users= friendsDao.suggestFriends(id);
     
    	 return new ResponseEntity<List<RegistrationPage>>(users, HttpStatus.OK);
     }
     
     @PostMapping("/getFriendrequests")
     public ResponseEntity<List<RegistrationPage>> getFriendsRequest(@RequestBody Integer userId)
     {
    	 System.out.println("getFriendsrequest");
    	 System.out.println("sizeoflistfriends"+friendsDao.getPendingReqs(userId).size());
    	 List<RegistrationPage> users=new ArrayList<>();
    	 int i=0;
    	 for(Friends friend:friendsDao.getPendingReqs(userId))
    	 {
    		 System.out.println(i+"\n");
    		 i++;
    		   users.add(regDao.getUserId(friend.getFriendId()));
    	 }
    	 return new ResponseEntity<List<RegistrationPage>>(users,HttpStatus.OK);
     }
     
     
     @PostMapping("/updateStatus/{friendId}/{userId}")
     public ResponseEntity<List<Friends>> updateStatus(@RequestBody String status,@PathVariable("friendId") Integer friendId,@PathVariable("userId")Integer userId)
     {
    	 System.out.println(friendId+"    "+userId);
    	  Friends frnd=   friendsDao.getFriend(friendId, userId);
    	  System.out.println("this is friend object  "+frnd);
    	  frnd.setStatus("accepted");
    	  friendsDao.updateFriend(frnd);
    	  
    	  
    	 return new ResponseEntity<List<Friends>>(friendsDao.getFriends(userId), HttpStatus.OK);
     }
}