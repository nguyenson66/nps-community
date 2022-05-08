package npscommunity.controller;

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
import npscommunity.entity.AppUser;
import npscommunity.repository.RoleRepository;
import npscommunity.repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@GetMapping
	public String signupForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", new AppUser());
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "signup";
		}
		return "redirect:/question";
	}

	@PostMapping
	public String processSignup(AppUser user, Errors errors) {
		if(errors.hasErrors()) {
			return "signup";
		}
		log.info("User summited:" + user);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
		return "redirect:/login";
	}
}
