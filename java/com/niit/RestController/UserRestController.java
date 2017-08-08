package com.niit.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.RegistrationDao;
import com.niit.model.RegistrationPage;

@RestController
public class UserRestController 
{
	@Autowired
	RegistrationDao regDao;
	
	@GetMapping(value="/user/")
	public ResponseEntity<List<RegistrationPage>>retrieveAllUsers()
	{
		List<RegistrationPage>users=regDao.listUsers();
		if(users.isEmpty())
		{
			return new ResponseEntity<List<RegistrationPage>>
			(HttpStatus.NO_CONTENT);//you man decide to return HttpStatus.NOT_FOUND
			
		}
		return new ResponseEntity<List<RegistrationPage>>(users,HttpStatus.OK);
	}
	@PostMapping(value="/usersave")
	public ResponseEntity<Void>createUser(@RequestBody RegistrationPage user){
		System.out.println("Creating User"+user.getUsername());
		if(regDao.isExistingUser(user)){
			System.out.println("A User with name"+ user.getUsername()+"already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
		regDao.addUser(user);
	
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	
}
	
	
	@PostMapping("/login")
    public ResponseEntity<RegistrationPage> login(@RequestBody RegistrationPage user)
    {
		System.out.println("user email::"+user.getEmail());
		System.out.println("user password::"+user.getPassword());
		user=regDao.getEmail(user.getEmail(), user.getPassword());
		if(user!=null)
		{
			return new ResponseEntity<RegistrationPage>(user,HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<RegistrationPage>(user,HttpStatus.UNAUTHORIZED);
    }
}
}
