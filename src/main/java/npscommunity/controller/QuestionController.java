package npscommunity.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

import npscommunity.entity.Question;
import npscommunity.repository.AnswerRepository;
import npscommunity.repository.CategoryRepository;
import npscommunity.repository.QuestionRepository;
import npscommunity.repository.UserRepository;
import npscommunity.entity.Answer;
import npscommunity.entity.AppUser;
import npscommunity.entity.Category;

@Slf4j
@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionRepository questionRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private AnswerRepository  answerRepo;

	@GetMapping
	public String allQuestionForm(Model model, Principal principal) {
		if(principal != null) {
			AppUser auth_user = userRepo.findByUsername(principal.getName());
			model.addAttribute("auth_user", auth_user);			
		}
		List<Question> questions = (List<Question>) questionRepo.findAll();
		model.addAttribute("questions", questions);
		return "allQuestions";
	}
	
	@GetMapping("/{id}")
	public String questionForm(@PathVariable Long id, Model model, Principal principal) {
		if(principal != null) {
			AppUser auth_user = userRepo.findByUsername(principal.getName());
			model.addAttribute("auth_user", auth_user);			
		}	
		Question ques = questionRepo.findById(id).get();
		log.info("Question found:" + ques.getId().toString());
		List<Category> hotCate = categoryRepo.findHotCategory();
		model.addAttribute("question", ques);
		model.addAttribute("q_user", ques.getUser());
		model.addAttribute("categories", ques.getCategories());
		model.addAttribute("hot_cate", hotCate);
		model.addAttribute("comment", new Answer());
		
		return "question";
	}

	@PostMapping("/comment/{q_id}")
	public String postComment(HttpServletRequest req, @PathVariable Long q_id, Principal principal, Answer comment) {
		AppUser auth_user = userRepo.findByUsername(principal.getName());			
		Question ques = questionRepo.findById(q_id).get();
		comment.setQuestion(ques);
		comment.setUser(auth_user);
		answerRepo.save(comment);
		return "redirect:" + req.getHeader("Referer");
	}
}
