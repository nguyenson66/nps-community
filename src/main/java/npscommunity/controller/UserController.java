package npscommunity.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import npscommunity.entity.AppUser;
import npscommunity.repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping
	public String usersForm(Model model){
		List<AppUser> users= (List<AppUser>) userRepo.findAll();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("/{username}")
	public String userProfile(@PathVariable String username, Model model) {
		AppUser user = userRepo.findByUsername(username);
		model.addAttribute("user", user);
		return "userProfile";
	}

	@GetMapping("/{username}/edit")
	public String userProfileUpdate(@PathVariable String username, Model model, Principal principal) {
		AppUser user = userRepo.findByUsername(username);
		if(principal.getName().equals(username)) {
			log.info("OK, May co quyen edit");
			return "userProfileUpdate";
		}
		model.addAttribute("user", user);
		log.info("May deo co quyen edit ok ?");
		return "userProfile";
	}
}
