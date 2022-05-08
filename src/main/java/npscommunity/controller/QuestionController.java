package npscommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

import npscommunity.entity.Question;
import npscommunity.repository.CategoryRepository;
import npscommunity.repository.QuestionRepository;
import npscommunity.repository.UserRepository;
import npscommunity.entity.AppUser;

@Slf4j
@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	QuestionRepository questionRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	CategoryRepository categoryRepo;

	@GetMapping
	public String questionForm(@RequestParam(name="id", required=false, defaultValue="1") String id, Model model) {
		Question ques = questionRepo.findById(id).get();
//		List<Category> categories = categoryRepo.findListCategoryByQuestionID(id);
//		List<Category> hotCate = categoryRepo.findHotCategory();
		log.info("Question found:" + ques);
		AppUser q_user = userRepo.findById(id).get();
		model.addAttribute("question", ques);
		model.addAttribute("q_user", q_user);
//		model.addAttribute("categories", categories);
//		model.addAttribute("hot_cate", hotCate);
		return "question";
	}
}
