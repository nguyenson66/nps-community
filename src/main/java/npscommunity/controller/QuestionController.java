package npscommunity.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import npscommunity.entity.Answer;
import npscommunity.entity.AppUser;
import npscommunity.entity.Category;
import npscommunity.entity.Question;
import npscommunity.repository.AnswerRepository;
import npscommunity.repository.CategoryRepository;
import npscommunity.repository.QuestionRepository;
import npscommunity.repository.UserRepository;

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
	private AnswerRepository answerRepo;

	@GetMapping("/ask")
	public String home(Model model, Principal principal) {
		if (principal != null) {
			AppUser auth_user = userRepo.findByUsername(principal.getName());
			model.addAttribute("auth_user", auth_user);
		}
		Page<Question> questions = (Page<Question>) questionRepo.findAll(PageRequest.of(0, 5));

		model.addAttribute("questions", questions);

		model.addAttribute("initQuestion", new Question());

		Page<AppUser> users = (Page<AppUser>) userRepo.findAll(PageRequest.of(0, 10));
		model.addAttribute("users", users);

		List<String> listCategory = categoryRepo.findDistinctCategory();
		model.addAttribute("titles", listCategory);

		Page<Question> topQues = (Page<Question>) questionRepo
				.findAll(PageRequest.of(0, 10, Sort.by("viewed").descending()));
		model.addAttribute("topQues", topQues);

		return "ask";
	}
	
	@PostMapping("/ask")
	public String addQuestion(Question initQuestion, Errors error, Principal principal) {
		if (principal == null) {
			return "login";
		}
		AppUser auth_user = userRepo.findByUsername(principal.getName());
		initQuestion.setUser(auth_user);
		questionRepo.save(initQuestion);
		return "redirect:/";
	}

	@GetMapping("/{q_id}")
	public String questionForm(@PathVariable Long q_id, Model model, Principal principal) {
		if (principal != null) {
			AppUser auth_user = userRepo.findByUsername(principal.getName());
			model.addAttribute("auth_user", auth_user);
		}
		Question ques = questionRepo.findById(q_id).get();
		ques.setViewed(ques.getViewed() + 1);
		questionRepo.save(ques);
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
