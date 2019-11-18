package com.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model.QueryForm;
import com.model.User;
import com.model.UserRegForm;
import com.service.UserService;

@Controller("mycontroller")

public class MyController {
	@Autowired
	 public UserService userService;

	 @RequestMapping(value = "/register2", method = RequestMethod.GET)
	  public ModelAndView showRegister2(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("register2");
	    mav.addObject("user", new User());
	    return mav;
	  }
	 
	  @RequestMapping(value = "/registerProcess2", method = RequestMethod.POST)
	  public ModelAndView addUser2(HttpServletRequest request, HttpServletResponse response,  @ModelAttribute("user") User user, @RequestParam("file") MultipartFile files[]) {
		  String username = user.getUsername();
			for (int i = 0; i < files.length; i++) {
				String filename="";
				if(i==0)
					filename=(username+i)+".pdf";
					else if(i==1)
						filename=(username+i)+".pdf";
					else if(i==2)
						filename=(username+i)+".jpg";
				MultipartFile file = files[i];
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					File dir = new File(rootPath + File.separator + "tmpFiles");
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath()+ File.separator + filename);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					
					if(i==0)
                    user.setAddress_fname(filename);
					else if(i==1)
						user.setDob_fname(filename);
					else if(i==2)
						user.setImage(filename);
					user.setStatus("N");
					
					System.out.println("Server File Location="+ serverFile.getAbsolutePath());
					} catch (Exception e) {
					System.out.println( "You failed to upload " + (username+i) + " => " + e.getMessage());
				}
			}  
		  
		boolean flag=userService.register(user);
	    if(flag) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new User());
	    return mav;
	    }
	    else {
	    	ModelAndView mav = new ModelAndView("register");
	        mav.addObject("user", new User());
	        mav.addObject("status","Sorry! Registration in incomplete");
	        return mav;	
	    }
	  }
	
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("user", new User());
	    return mav;
	  }

	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,   @ModelAttribute User iuser) {
	    ModelAndView mav = null;
	    User user = userService.validateUser(iuser);
	    if (user != null) {
	      mav = new ModelAndView("welcome");
	      mav.addObject("firstname", user.getFirstname());
	      //session manage
	      HttpSession session= request.getSession();
	      session.setAttribute("username", iuser.getUsername());
	    } else {
	      mav = new ModelAndView("login");
	      mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }

		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public ModelAndView userlogout(HttpServletRequest request, HttpServletResponse response) {
			 HttpSession session =request.getSession(false);
			 session.invalidate();
			 ModelAndView mav = new ModelAndView("login");
			 mav.addObject("login", new User());
			 return mav;
		 }

		@RequestMapping(value = "/viewusers", method = RequestMethod.GET)
		public ModelAndView viewflight(HttpServletRequest request, HttpServletResponse response) {
			List<User>  userlist = userService.getUsers();
			 ModelAndView mav = new ModelAndView("viewusers");
			 mav.addObject("userlist", userlist);
			 HttpSession session =request.getSession(false);
			 session.setAttribute("ulist", userlist);
			 return mav;
		 }
  
		@RequestMapping(value = "/changepwd", method = RequestMethod.GET)
		  public ModelAndView changepwd1(HttpServletRequest request, HttpServletResponse response) {
		    ModelAndView mav = new ModelAndView("changepwd");
		    return mav;
		  }
		@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
		  public ModelAndView changepwd2(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
            String username=(String)session.getAttribute("username");
            String opwd= request.getParameter("opassword");
            String npwd= request.getParameter("npassword");
            System.out.println(username+"  "+opwd+"  "+npwd);
            boolean flag = userService.changepwd(username,opwd,npwd);
            if(flag) {
		       ModelAndView mav = new ModelAndView("changepwd");
		       mav.addObject("message", "Password is successfully updated");
		       return mav;
		  }
            else {
            	ModelAndView mav = new ModelAndView("changepwd");
 		       mav.addObject("message", "Password is not updated");
 		       return mav;
            }
		}
		
		@RequestMapping(value="/query", method=RequestMethod.GET)
	    public ModelAndView getQueryForm() {
			ModelAndView mav = new ModelAndView("query");
			mav.addObject("queryform",new QueryForm());
	    	return mav;
	    }
	    
	    @RequestMapping(value="/query", method=RequestMethod.POST)
	    public ModelAndView sendQueryForm(@ModelAttribute QueryForm queryForm) {
	    	System.out.println(queryForm.getName()+" "+queryForm.getInfo());
	    	queryForm = userService.sendQuery(queryForm);
	    	ModelAndView mav = new ModelAndView("query1");
	    	mav.addObject("obj",queryForm);
	    	return mav;
	    }
	  
/*	  @RequestMapping(value = "/register", method = RequestMethod.GET)
	  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		//public ModelAndView showRegister() {
	    ModelAndView mav = new ModelAndView("register");
	    mav.addObject("user", new User());
	    return mav;
	  }

	  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,  @ModelAttribute("user") User user) {
	    boolean flag=userService.register(user);
	    if(flag) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new User());
	    return mav;
	    ModelAndView mav = new ModelAndView("fileregister");
		mav.addObject("userRegForm", new UserRegForm());	
		mav.addObject("username",user.getUsername());
	    return mav;
	    
	    }
	    else {
	    	ModelAndView mav = new ModelAndView("register");
	        mav.addObject("user", new User());
	        mav.addObject("status","Sorry! Registration in incomplete");
	        return mav;	
	    }
	  }
	  
	  @RequestMapping(value = "/uploadfiles", method = RequestMethod.POST)
		//public String save(	@ModelAttribute("userRegForm") UserRegForm userRegForm,	BindingResult result) {
		  public String save (@RequestParam("username") String username,@RequestParam("file") MultipartFile files[]) {
		if (userRegForm != null) {
			
			List<String> uploadedFileNames = new ArrayList<String>();
			if (userRegForm.getFiles() != null) {
				for (MultipartFile file : userRegForm.getFiles()) {
					uploadedFileNames.add(file.getOriginalFilename());
				}
				//modelAndView.addObject("listUploadedFiles", uploadedFileNames);
			}
			System.out.println(uploadedFileNames);
	       
		} 
		  String message = "";
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				//String name = names[i];
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					File dir = new File(rootPath + File.separator + "tmpFiles");
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath()+ File.separator + (username+i));
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					System.out.println("Server File Location="+ serverFile.getAbsolutePath());

					message = message + "You successfully uploaded file=" + (username+i)
							+ "<br />";
				} catch (Exception e) {
					System.out.println( "You failed to upload " + (username+i) + " => " + e.getMessage());
				}
			}
		return "welcome";
	      }*/
	  
	
	//Form handling with Model object
	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public ModelAndView addContact(HttpServletRequest request, HttpServletResponse response) {
		 String name= request.getParameter("name");
		 String info = request.getParameter("info");
		 System.out.println(name+"  "+info);
		 // do some job using service layer
		 ModelAndView mav = new ModelAndView("viewcontact");
	     mav.addObject("name", name.toUpperCase());
	     mav.addObject("info", info.toLowerCase());
	     return mav;  
	     //return "viewcontact";
	 }




	}

	
/*	private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024; 
	
	@Autowired
	 public UserService userService;
	

	
	@RequestMapping(value = "/register", method = RequestMethod.GET )
	  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		//public ModelAndView showRegister() {
	    ModelAndView mav = new ModelAndView("register");
	    mav.addObject("user", new User());
	    return mav;
	  }

	  

	  //public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,  @ModelAttribute("user") User user , @RequestParam("file") MultipartFile[] files) {
	 // public ModelAndView addUser(@ModelAttribute("userRegForm") UserRegForm userRegForm , BindingResult bindingResult ) {  
	  
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,  @ModelAttribute("user") User user) {
		  
		  try {
			Part part = request.getPart("file1");
			System.out.println(part);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  //System.out.println("obj:"+request.getParameter("address"));  
	  //System.out.println(("obj:"+userRegForm.getAddress()));
	   * 
		  
       boolean flag=userService.register(user);
	    if(flag) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new User());
	    	ModelAndView mav = new ModelAndView("fileregister");
		    mav.addObject("userRegForm", new UserRegForm());	
		    mav.addObject("username",user.getUsername());
	    return mav;
	    }
	    else {
	    	ModelAndView mav = new ModelAndView("register");
	        mav.addObject("user", new User());
	        mav.addObject("status","Sorry! Registration in incomplete");
	        return mav;	
	    }
	  }
	  
	
	 @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,  @ModelAttribute("user") User user) {
	    boolean flag=userService.register(user);
	    if(flag) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new Login());
	    return mav;
	    	ModelAndView mav = new ModelAndView("fileregister");
		    mav.addObject("userRegForm", new UserRegForm());	
		    mav.addObject("username",user.getUsername());
	    return mav;
	    }
	    else {
	    	ModelAndView mav = new ModelAndView("register");
	        mav.addObject("user", new User());
	        mav.addObject("status","Sorry! Registration in incomplete");
	        return mav;	
	    }
	  }
	  
	
	@RequestMapping(value = "/uploadfiles", method = RequestMethod.POST)
	public String save(	@ModelAttribute("userRegForm") UserRegForm userRegForm,	BindingResult result) {
	if (userRegForm != null) {
		
		List<String> uploadedFileNames = new ArrayList<String>();
		if (userRegForm.getFiles() != null) {
			for (MultipartFile file : userRegForm.getFiles()) {
				uploadedFileNames.add(file.getOriginalFilename());
			}
			//modelAndView.addObject("listUploadedFiles", uploadedFileNames);
		}
		System.out.println(uploadedFileNames);
       
	} 
	return "welcome";
      }
 
    @RequestMapping(path="/hello1", method=RequestMethod.GET)
	public void show1() {
		System.out.println(" I am fine....");
	}
    
    @RequestMapping(value="/hello2", method=RequestMethod.GET)
    public String show2() {
    	System.out.println(" I am fine from show2....");
    	return "show2";
    }
    
    @RequestMapping(value="/hello3", method=RequestMethod.GET)
    public ModelAndView show3() {
    	ModelAndView mav  = new ModelAndView();
    	mav.setViewName("show3");
    	mav.addObject("var1","Dibakar Sanyal");
    	mav.addObject("address", "Kolkata,WB");
    	return mav;
    	
    }
    
    @RequestMapping(value="/query", method=RequestMethod.GET)
    public String getQueryForm() {
    	return "query";
    }
    
    @RequestMapping(value="/makequery", method=RequestMethod.POST)
    public ModelAndView sendQueryForm(@ModelAttribute MyQuery myQuery) {
    	System.out.println(myQuery.getName());
    	ModelAndView mav = new ModelAndView("query1");
    	mav.addObject("obj",myQuery);
    	return mav;
    }
    
    @RequestMapping(value = "/viewusers", method = RequestMethod.GET)
    public ModelAndView viewflight(HttpServletRequest request, HttpServletResponse response) {
    	List<User>  userlist = userService.getUsers();
    	 ModelAndView mav = new ModelAndView("viewusers");
    	 mav.addObject("userlist", userlist);
    	// HttpSession session =request.getSession(false);
    	// session.setAttribute("ulist", userlist);
    	 return mav;
     }
    
}


*/