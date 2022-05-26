package npscommunity.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public String usersForm(Model model, Principal principal){
		if(principal != null) {
			AppUser auth_user = userRepo.findByUsername(principal.getName());
			model.addAttribute("auth_user", auth_user);			
		}
		List<AppUser> users= (List<AppUser>) userRepo.findAll();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("/{username}")
	public String userProfile(@PathVariable String username, Model model, Principal principal) {
		AppUser user = userRepo.findByUsername(username);
		if(user == null)
			return "Error_404";
		boolean aup = false;
		if(principal != null) {
			if(principal.getName().equals(username))
				aup=true;
			AppUser auth_user = userRepo.findByUsername(principal.getName());
			model.addAttribute("auth_user", auth_user);
		}
		model.addAttribute("user", user);
		model.addAttribute("aup", aup);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
		Date user_birthday = new Date(user.getBirthday().getTime());
		model.addAttribute("user_birthday", df.format(user_birthday));
		return "userProfile";
	}

	@GetMapping("/{username}/edit")
	public String userProfileUpdate(@PathVariable String username, Model model, Principal principal) {
		AppUser user = userRepo.findByUsername(username);
		if(principal != null) {
			AppUser auth_user = userRepo.findByUsername(principal.getName());
			model.addAttribute("auth_user", auth_user);
			model.addAttribute("user", user);
			if(principal.getName().equals(username)) {
				log.info("OK, Ban co quyen edit");
				return "userProfileUpdate";
			}
		}
		log.info("Ban ko co quyen edit ok ?");
		return "userProfile";
	}
}
