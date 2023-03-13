package com.ashokit.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.ashokit.ui.constants.AppConstants;
import com.ashokit.ui.model.CallDetails;
import com.ashokit.ui.model.Customer;
import com.ashokit.ui.model.CustomerProfile;
import com.ashokit.ui.model.Friend;
import com.ashokit.ui.model.Login;
import com.ashokit.ui.model.Plan;

@Controller
public class CustomerController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(value = "/index")
	public String getIndexPage() {
		return "Index";
	}
	
	private List<Plan>  getPlans() {
		ParameterizedTypeReference<List<Plan>>  typeRef=new  ParameterizedTypeReference<List<Plan>>() {};
		
		ResponseEntity<List<Plan>>  re = restTemplate.exchange(AppConstants.BROWSE_ALL_PLANS_URL, HttpMethod.GET, null, typeRef);
		List<Plan>   plansList = re.getBody();
		return  plansList;
	}
	
	@GetMapping(value = "/customer_registration_page")
	public String getCustomerRegistrationPage(Model  model) {
		
		Customer customer = new Customer();
		customer.setPlansList(getPlans());
		model.addAttribute("customer", customer);
		
		return  "Registration";
		
	}
	
	@PostMapping("/add_customer")
	public String addCustomer(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			customer.setPlansList(getPlans());
			model.addAttribute("customer", customer);
			return   "Registration";			
		}
		
		//call Microservice-Customer
		boolean flag=restTemplate.postForObject(AppConstants.CUSTOMER_REGISTRATION_URL, customer, Boolean.class);
		if(flag==true) {
			String message="Customer is successfully registered!!!";
			model.addAttribute("message", message);
			return "Success";
		}
		else {
			String message="Customer is already registered with this phone number, try again!!!";
			model.addAttribute("message", message);
			customer.setPlansList(getPlans());
			model.addAttribute("customer", customer);
			return "Registration";
		}
			
	}
	
	@GetMapping( value = "/customer_login_page")
	public   String   getCustomerLoginPage() {
		return  "Login";
	}
	
	@PostMapping( value = "/login_customer")
	public   String   loginCustomer(@RequestParam Long phoneNumber, @RequestParam String password, Model model, HttpServletRequest request)
	{
		Login  login=new  Login();
		login.setPhoneNumber(phoneNumber);
		login.setPassword(password);
		
		boolean  flag=restTemplate.postForObject(AppConstants.CUSTOMER_LOGIN_URL, login, Boolean.class);
		
		if(flag==true) {
			HttpSession  session=request.getSession();
			session.setAttribute("phoneNumber", phoneNumber);
			String userName = restTemplate.getForObject(AppConstants.CUSTOMER_NAME_URL, String.class, phoneNumber);
			session.setAttribute("username", userName);
			return "Dashboard1";
		}
		else {
			model.addAttribute("message", "Bad Credentials");
			return  "Login";
		}
	
	}
	
	@GetMapping("/profile")
	public  String  customerProfile(Model model, HttpServletRequest request) {
		HttpSession  session=request.getSession();
		Long phoneNumber = (Long) session.getAttribute("phoneNumber");
		CustomerProfile custProfile = restTemplate.getForObject(AppConstants.CUSTOMER_PROFILE_URL, CustomerProfile.class, phoneNumber);
		model.addAttribute("customer_profile", custProfile);
		return  "customerProfile";
	}
	
	@GetMapping("/plans")
	public String browseAllPlans(Model model) {
		List<Plan> plansList = getPlans();
		model.addAttribute("plansList", plansList);
		return  "showPlans";
	}
	
	@GetMapping("/addfriend")
	public  String  addFriendPage() {
		return "addContact";
	}
	
	@GetMapping("/addContact")
	public  String  addFriendContact(Model model, HttpServletRequest request,@RequestParam Long friendNumber) {
		HttpSession  session=request.getSession();
		Long phoneNumber = (Long) session.getAttribute("phoneNumber");
		
		Friend  friend=new Friend();
		friend.setPhoneNumber(phoneNumber);
		friend.setFriendNumber(friendNumber);
		
		String message = restTemplate.postForObject(AppConstants.ADD_FRIEND_CONTACT_URL, friend, String.class);
		model.addAttribute("message", message);
		return  "friendAdded";
		
	}
	
	@GetMapping(value = "/dash_board")
	public String getCustomerDashboard( ) {
		return "Dashboard1";
	}
	
	@GetMapping("/calldetails")
	public String fetchCallDetails(HttpSession session, Model model) {
		Long phoneNumber = (Long)session.getAttribute("phoneNumber");
		ParameterizedTypeReference<List<CallDetails>>  typeRef=new  ParameterizedTypeReference<List<CallDetails>>() {};
		ResponseEntity<List<CallDetails>>  re = restTemplate.exchange(AppConstants.CALLDETAILS_URL, HttpMethod.GET, null, typeRef, phoneNumber);
		List<CallDetails>   callsList = re.getBody();
		model.addAttribute("callsList", callsList);
		return "calldetails_page";
		
	}
  
}
