package npscommunity.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import npscommunity.entity.AppUser;
import npscommunity.entity.Question;
import npscommunity.repository.QuestionRepository;
import npscommunity.repository.UserRepository;
import npscommunity.utils.WebUtils;

@Controller
public class MainController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@GetMapping(value = {"/", "/questions"})
	public String allQuestionForm(Model model, Principal principal) {
		if (principal != null) {
			AppUser auth_user = userRepo.findByUsername(principal.getName());
			model.addAttribute("auth_user", auth_user);
		}
		List<Question> questions = (List<Question>) questionRepo.findAll();
		model.addAttribute("questions", questions);
		return "allQuestions";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginForm(HttpServletRequest req, Model model) {
		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("url_prior_login", referer);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/" + referer;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					+ "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "Error_403";
	}

}
