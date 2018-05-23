package com.ismail.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.ismail.models.Role;
import com.ismail.models.User;
import com.ismail.repository.RoleRepository;
import com.ismail.repository.UserRepository;
import org.springframework.http.ResponseEntity;

@Service
public class UserService {
	@Autowired
	UserRepository urepo;
	@Autowired
	private RoleRepository roleRepository;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;	
	@Autowired
	MailClient messanger;
	
	
	public ResponseEntity<ResponseWrapper<Optional<User>>> oneUser (Long id) {
            ResponseWrapper response = new ResponseWrapper();
            Optional<User> usr =  urepo.findById(id);
            
            if (usr == null){
                response.setCode(404);
                response.setMessage("User with id " +id+ "not found");
                return ResponseEntity.ok().body(response);               
            }else{
                response.setCode(200);
                response.setData(usr);
                return ResponseEntity.ok().body(response);
            }
                
	}
	
    public ResponseEntity<ResponseWrapper<Optional<User>>> newUser (User usr) {
            ResponseWrapper response = new ResponseWrapper();
            User user =  urepo.findByEmail(usr.getEmail());
            
            if (user == null){
                response.setCode(201);
                response.setMessage("User created successfully");
                urepo.save(usr);
                return ResponseEntity.ok().body(response);               
            }else{
                response.setCode(200);
                response.setMessage("User with email " +usr.getEmail() + "already exists");
                return ResponseEntity.ok().body(response);
            }                
    }
    
         public ResponseEntity<ResponseWrapper<Optional<User>>> updateUser (User usr) {
          ResponseWrapper response = new ResponseWrapper();
          User user = urepo.findByEmail(usr.getEmail());

          user.setFirstName(usr.getFirstName());
          user.setLastName(usr.getLastName());
          user.setPhoneNo(usr.getPhoneNo());
          user.setEmail(usr.getEmail());
          urepo.save(user);

          response.setCode(200);
          response.setMessage("User updated successfully");
          return ResponseEntity.ok().body(response);
        }             
      
    public ResponseEntity<ResponseWrapper<Optional<User>>> deleteUser(Long id) {
        ResponseWrapper response = new ResponseWrapper();
        Optional<User> usr = urepo.findById(id);
        urepo.deleteById(id);

        response.setCode(200);
        response.setMessage("User deleted successfully");
        return ResponseEntity.ok().body(response);
    }
      public ResponseEntity<ResponseWrapper<Optional<User>>> allUsers() {
        ResponseWrapper response = new ResponseWrapper();
        List<User> users = urepo.findAll();

        response.setCode(200);
        response.setData(users);
        return ResponseEntity.ok().body(response);
    }

}
	
//	public String deleteUser(Long id) {
//		 urepo.deleteById(id);
//		 return "success";
//	}
//	
//	public List<User> getAll(){
//		return urepo.findAll();
//	}
//	
//	public User findUserByEmail(String email) {
//		return urepo.findByEmail(email);
//	}
//	public String saveUser(User user) {
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		user.setActive(false);
//		Role userRole = roleRepository.findByRole("ROLE_CUSTOMER");
//		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//		urepo.save(user);
//		
////		send email
//		String message = "http://localhost:8080/" + user.getEmailConfirmation();
//		messanger.prepareAndSend(user.getEmail(), message);
//		
//		ModelAndView model = new ModelAndView();
//		model.addObject("successMsg", "*Registered  successfully. KIndly check your email inbox to confirm your email address.");
//		return "login";
//		
//	}
//	
//	
////**************  ACCOUNT RECOVERY + EMAIL CONFIRMATION  ***************************************************************************
//
//	
//	//Find user by token
//		public String findUserByEmailtoken(String emailToken) {
//			User user = urepo.findByEmailConfirmation(emailToken);
//			if (user == null) {
//				ModelAndView model = new ModelAndView();
//				model.addObject("failedMsg", "Email confirmation failed. Make sure the URL has not been edited.");
//				return "login";
//			}
//			
//			user.setActive(true);
//			user.setEmailConfirmation(null);
//			urepo.save(user);
//			
//			ModelAndView model = new ModelAndView();
//			model.addObject("successMsg", "Email has been confirmed. You can log into your account now.");
//
//			return "login";
//		}
//		
////		public ResponseEntity<User> setUserPassword(String emailToken, String password) {
////			User user = urepository.findByEmailConfirmation(emailToken);
////			if (user == null) {
////				return ResponseEntity.notFound().build();
////			}
////			user.setActive(true);
////			user.setEmailConfirmation(null);
////			user.setPassword(bcryptPasswordEncoder.encode(password));
////			urepository.save(user);
////			return ResponseEntity.ok().build();		
////		}
//		
////		//FORGOT PASSWORD
////		public String forgotPassword(String email) {
////			User user = urepo.findByEmail(email);
////			if (user == null) {
////				ModelAndView model = new ModelAndView();
////				model.addObject("failedMsg", "Sorry, no user with that email address exist. Make sure you use a valid email address");
////				return "reset-password";
////			}
////		    user.setPassword(null);		
////			user.setActive(false);
////			user.setEmailConfirmation(generateEmailToken(12));
////			urepo.save(user);
////			
////			//send email
////			String message = "http://localhost:8080/resetpassword/" + user.getEmailConfirmation();
////			messanger.prepareAndSend(user.getEmail(), message);
////			
////			ModelAndView model = new ModelAndView();
////			model.addObject("successMsg", "Email has been confirmed. Kindly check your email address for the reset link we have sent you");
////			
////			return "reset-password";
////		}
//		
//		//GENERATE emailConfirmation TOKEN
//		public String generateEmailToken (int len) {
//
//			// Using random method
//			Random rndm_method = new Random();
//
//			char[] otp = new char[len];
//
//			for (int i = 0; i < len; i++)
//			{
//				otp[i] =(char) rndm_method.nextInt(9);
//			}
//			
//			//String Builder 
//			StringBuilder sb = new StringBuilder();
//			for (int n : otp) {
//			    sb.append(n);
//			}
//			String result = sb.toString();
//			
//			return result;
//			
//			
//			}	
	
//**************  AUTHENTICATION  ***************************************************************************
//
//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		User user = urepo.findByEmail(userName);
//		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
//		return buildUserForAuthentication(user, authorities);
//	}
//
//	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
//		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//		for (Role role : userRoles) {
//			roles.add(new SimpleGrantedAuthority(role.getRole()));
//		}
//
//		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
//		return grantedAuthorities;
//	}
//
//	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
//	}	


