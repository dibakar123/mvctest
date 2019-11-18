package com.service;

import java.util.List;

import com.model.QueryForm;
import com.model.User;

public interface UserService {
  public boolean register(User user);
  public User validateUser(User user);
  public List<User> getUsers();
  public boolean changepwd(String username,String opwd, String npwd);
  public QueryForm sendQuery(QueryForm queryForm);
}
