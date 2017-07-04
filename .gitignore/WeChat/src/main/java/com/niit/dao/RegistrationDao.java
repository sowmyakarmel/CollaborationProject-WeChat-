package com.niit.dao;

import java.util.List;

import com.niit.model.RegistrationPage;


public interface RegistrationDao
{
public void addUser(RegistrationPage user);
public List<RegistrationPage> listUsers();
public boolean isExistingUser(RegistrationPage user);
public RegistrationPage getUserByUsername(String username);
public RegistrationPage getEmail(String email,String password);
public RegistrationPage getUserId(int userId);

}