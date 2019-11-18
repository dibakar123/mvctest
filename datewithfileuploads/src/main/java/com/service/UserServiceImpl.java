package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.model.QueryForm;
import com.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  public UserDao userDao;

  public boolean register(User user) {
    return userDao.register(user);
  }

  public User validateUser(User user) {
    return userDao.validateUser(user);
  }

  public List<User> getUsers(){
	  List<User>  list = userDao.getUsers();
	  return list;
  }
  
  public boolean changepwd(String username,String opwd, String npwd) {
	  return userDao.changepwd(username, opwd,  npwd);
  }
  
  public QueryForm sendQuery(QueryForm queryForm) {
	  return userDao.sendQuery(queryForm);
  }
  
}
