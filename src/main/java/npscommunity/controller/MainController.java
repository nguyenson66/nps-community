package npscommunity.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
// import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import npscommunity.entity.Answer;
import npscommunity.entity.AppUser;
import npscommunity.entity.Category;
import npscommunity.entity.Question;
import npscommunity.repository.AnswerRepository;
import npscommunity.repository.CategoryRepository;
import npscommunity.repository.QuestionRepository;
import npscommunity.repository.UserRepository;
import npscommunity.utils.WebUtils;

@Controller
@Slf4j
public class MainController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private QuestionRepository questionRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private AnswerRepository answerRepo;

	@GetMapping(value = { "/", "/questions" })
	public String allQuestionForm(Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "8") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort, Principal principal) {
		AppUser auth_user = null;
		if (principal != null)
			auth_user = userRepo.findByUsername(principal.getName());
		model.addAttribute("auth_user", auth_user);
		List<Category> hotCate = categoryRepo.findHotCategory();
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("id").descending();
		}
		Pageable pageable = PageRequest.of(page - 1, size, sortable);
		Page<Question> questions = questionRepo.findAll(pageable);
		log.info("" + questions.getTotalPages());
		model.addAttribute("hot_cate", hotCate);
		model.addAttribute("questions", questions);
		int totalPages = questions.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "allQuestions";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginForm(@RequestParam(value = "error", defaultValue = "false") boolean loginError,
			HttpServletRequest req, Model model) {
		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("url_prior_login", referer);
		if (loginError) {
			String errorMessage = null;
			AuthenticationException ex = (AuthenticationException) req.getSession(false)
					.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			if (ex != null) {
				errorMessage = ex.getMessage();
			}

			model.addAttribute("errorMessage", errorMessage);
			return "login";
		}
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

	@GetMapping("/addDemoQ")
	public String addDemoQuestion() {
		for (int i = 1; i < 10; i++) {
			Question question = new Question();
			Category category = new Category();
			AppUser user = new AppUser();

			question.setTitle("Câu hỏi " + (i + 1));
			question.setContent("Nội dung của câu hỏi " + (i + 1));
			question.setUser(user);

			category.setName("Chủ đề " + (i + 1));
			categoryRepo.save(category);

			user.setUsername("user" + (i + 1));
			user.setPassword(new BCryptPasswordEncoder().encode("1"));
			user.setName("Người dùng " + (i + 1));
			userRepo.save(user);
			questionRepo.save(question);

			Answer ans1 = new Answer();
			Answer ans2 = new Answer();
			ans1.setContent("Câu trả lời 1 của câu hỏi " + (i + 1));
			ans2.setContent("Câu trả lời 2 của câu hỏi " + (i + 1));
			ans1.setUser(user);
			ans2.setUser(user);
			ans1.setQuestion(question);
			ans2.setQuestion(question);
			answerRepo.save(ans1);
			answerRepo.save(ans2);

			List<Category> cates = new ArrayList<>();
			List<Answer> answers = new ArrayList<>();
			answers.add(ans1);
			answers.add(ans2);

			question.setCategories(cates);
			questionRepo.save(question);
		}
		return "redirect:/questions";
	}

}
