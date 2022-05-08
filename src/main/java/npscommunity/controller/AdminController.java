package npscommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import npscommunity.repository.QuestionRepository;
import npscommunity.repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	QuestionRepository questionRepo;

	@Autowired
	UserRepository userRepo;

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
