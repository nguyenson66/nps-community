package npscommunity.controller;

import java.security.Principal;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import npscommunity.dao.UserDAO;
import npscommunity.model.User;

@Slf4j
@Controller
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping 
	public String usersForm(Model model){
		List<User> users= userDAO.findALlUser();
		model.addAttribute("users", users);
		return "users";
	}
	
	@GetMapping("/{username}")
	public String userProfile(@PathVariable String username, Model model) {
		User user = userDAO.findUserAccount(username);
		model.addAttribute("user", user);
		return "userProfile";
	}
	
	@GetMapping("/{username}/edit")
	public String userProfileUpdate(@PathVariable String username, Model model, Principal principal) {
		User user = userDAO.findUserAccount(username);
		if(principal.getName().equals(username)) {
			log.info("OK, May co quyen edit");
			return "userProfileUpdate";
		}
		model.addAttribute("user", user);
		log.info("May deo co quyen edit ok ?");
		return "userProfile";
	}
}
