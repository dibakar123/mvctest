package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*create table myusers(
username varchar(20) not null primary key,
password  varchar(20),
firstname  varchar(20),
lastname  varchar(20),
email  varchar(20),
address  varchar(20),
phone  varchar(20),
dob date
);*/

/*new table ddl
create table myusers (username varchar2(255 char) not null,
address varchar2(255 char), 
address_fname varchar2(255 char), 
dob date, 
dob_fname varchar2(255 char), 
email varchar2(255 char), 
firstname varchar2(255 char), 
lastname varchar2(255 char), 
password varchar2(255 char), 
phone varchar2(255 char), 
image varchar2(255 char), 
status char(1) default 'N',
primary key (username));*/

@Entity
@Table(name="myusers")
public class User {
  @Id
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String address;
  private String phone;
  @Temporal(TemporalType.DATE)
  private Date dob;
  private String dob_fname;
  private String address_fname;
  private String image;
  private String status;
  
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public String getDob_fname() {
	return dob_fname;
}
public void setDob_fname(String dob_fname) {
	this.dob_fname = dob_fname;
}
public String getAddress_fname() {
	return address_fname;
}
public void setAddress_fname(String address_fname) {
	this.address_fname = address_fname;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public User() {
	super();
}

  
}
