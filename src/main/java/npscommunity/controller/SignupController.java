package npscommunity.controller;

import java.security.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import npscommunity.dao.RoleDAO;
import npscommunity.dao.UserDAO;
import npscommunity.model.User;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	RoleDAO roleDAO;
	
	@GetMapping
	public String signupForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", new User());
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "signup";
		}
		return "redirect:/question";
	}
	
	@PostMapping
	public String processSignup(User user, Errors errors) {
		if(errors.hasErrors()) {
			return "signup";
		}
		log.info("User summited:" + user);	
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDAO.save(user);
        user.setId(userDAO.findIdByUsername(user.getUsername()));
        roleDAO.initRoleForNewUser(user);
		return "redirect:/login";
	}
}
