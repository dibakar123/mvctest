package com.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.model.QueryForm;
import com.model.User;


@Repository("userDao")
public class UserDaoImpl implements UserDao {
  
  public boolean register(User user) {
    boolean flag=false;
    try {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin( ); 
	em.persist(user);
	em.getTransaction().commit();
	em.close();
	System.out.println("end");
	flag=true;
    }
    catch(Exception e) { System.out.println("Error:"+e);  }
    return flag;
  }

  public User validateUser(User user) {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	EntityManager em = emf.createEntityManager();
	User f =null;
	try{
		f=(User)em.createQuery("SELECT f FROM User f WHERE f.username=:uname and f.password=:pwd")
	         .setParameter("uname", user.getUsername())
	         .setParameter("pwd",user.getPassword())
	         .getSingleResult();
	}
	catch(Exception e) {System.out.println(e); }
	em.close();
	System.out.println(f);
	return f;
  }

  public List<User> getUsers(){
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
  EntityManager em = emf.createEntityManager();	  
  @SuppressWarnings("unchecked")
	List<User> users = em.createQuery("SELECT u FROM  User u").getResultList();
  em.close();
  return  users;
  }
  
  public boolean changepwd(String username,String opwd, String npwd) {
	  boolean flag=false;
	  EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
	  EntityManager em = emf.createEntityManager();	  
	  Query query = em.createQuery("update User u set u.password=:npwd where u.username=:username and u.password=:opwd");
	  query.setParameter("npwd", npwd);
	  query.setParameter("opwd", opwd);
	  query.setParameter("username", username);
	  em.getTransaction().begin();
	  int r = query.executeUpdate();
	  em.getTransaction().commit();
	  em.close();
	  if(r>0)
		  flag=true;
	  return flag;
  }
	  
  public QueryForm sendQuery(QueryForm queryForm) {
	  String qid = "Q"+(int)(new Date().getTime());
	  queryForm.setId(qid);
	  // add queryform in table using entitymanager
	  return queryForm;
  }
	
}

