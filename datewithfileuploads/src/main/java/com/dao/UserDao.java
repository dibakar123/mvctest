package com.dao;

import java.util.List;

import com.model.QueryForm;
import com.model.User;

public interface UserDao {
  boolean register(User user);
  User validateUser(User user);
  public List<User> getUsers();
  public boolean changepwd(String username,String opwd, String npwd);
  public QueryForm sendQuery(QueryForm queryForm);
}
