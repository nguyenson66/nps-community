package npscommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import npscommunity.dao.QuestionDAO;
import npscommunity.dao.UserDAO;
import npscommunity.model.Question;
import npscommunity.model.User;

@Slf4j
@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	QuestionDAO questionDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@GetMapping
	public String questionForm(@RequestParam(name="id", required=false, defaultValue="1") String id, Model model) {
		Question ques = questionDAO.findQuestionByID(id);
		log.info("Question found:" + ques);
		User q_user = userDAO.findUserByID(id);
		model.addAttribute("question", ques);
		model.addAttribute("q_user", q_user);
		return "question";
	}
}
