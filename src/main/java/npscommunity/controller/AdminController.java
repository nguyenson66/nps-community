package npscommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import npscommunity.dao.QuestionDAO;
import npscommunity.dao.UserDAO;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	QuestionDAO questionDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@GetMapping("")
	public String homeView() {
		return "adminTemplates/home";
	}
	
	@GetMapping("/category")
	public String categoryView() {
		return "adminTemplates/category";
	}
	
	@GetMapping("/user")
	public String userView() {
		return "adminTemplates/user";
	}
	
	@GetMapping("/question")
	public String questionView() {
		return "adminTemplates/question";
	}
}
