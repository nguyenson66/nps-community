package npscommunity.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import npscommunity.utils.WebUtils;


@Controller
public class MainController {
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
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
