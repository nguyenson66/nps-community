package npscommunity.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import npscommunity.dto.ManagerCategory;
import npscommunity.dto.ManagerQuestionDto;
import npscommunity.dto.ManagerUser;
import npscommunity.entity.AppUser;
import npscommunity.entity.Category;
import npscommunity.entity.Question;
import npscommunity.repository.CategoryRepository;
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

	@Autowired
	CategoryRepository categoryRepo;

	@GetMapping("")
	public String homeView(Principal principal, Model model) throws ParseException {
		Date timeNow = new Date();
		Calendar c = Calendar.getInstance();

		// subtract 1 month //
		c.setTime(timeNow);
		c.add(Calendar.MONTH, -1);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String thisMonth = format.format(new Date()) + "-01";

		String lastMonth = format.format(c.getTime()) + "-01";

		Date thisMonthDate = new SimpleDateFormat("yyyy-MM-dd").parse(thisMonth);
		Date lastMonthDate = (new SimpleDateFormat("yyyy-MM-dd").parse(lastMonth));

		long countUser = userRepo.count();
		long countQuestion = questionRepo.count();
		long viewQuestion = questionRepo.countView();
		long questionThisMonth = questionRepo.countQuestionByDate(thisMonthDate, timeNow);
		long questionLastMonth = questionRepo.countQuestionByDate(lastMonthDate, thisMonthDate);

		long userThisMonth = userRepo.countUserByDate(thisMonthDate, timeNow);
		long userLastMonth = userRepo.countUserByDate(lastMonthDate, thisMonthDate);

		/// set attribute ///
		// model.addAttribute("username", user.getName());
		model.addAttribute("countUser", countUser);
		model.addAttribute("countQuestion", countQuestion);
		model.addAttribute("viewQuestion", viewQuestion);
		model.addAttribute("questionThisMonth", questionThisMonth);
		model.addAttribute("questionLastMonth", questionLastMonth);
		model.addAttribute("userThisMonth", userThisMonth);
		model.addAttribute("userLastMonth", userLastMonth);
		model.addAttribute("thisMonth", format.format(thisMonthDate));
		model.addAttribute("lastMonth", format.format(lastMonthDate));

		return "adminTemplates/home";
	}

	@GetMapping("/category")
	public String categoryView(Model model) {

		long totalCategory = categoryRepo.count();
		List<ManagerCategory> getListCategory = categoryRepo.getListCategory();

		/////////////////////
		model.addAttribute("totalCategory", totalCategory);
		model.addAttribute("listCategory", getListCategory);
		model.addAttribute("category", new Category());

		return "adminTemplates/category";
	}

	@GetMapping("/user")
	public String userView(Model model, @RequestParam(value = "user", required = false) String emailOrUsername)
			throws ParseException {

		Date timeNow = new Date();
		Calendar c = Calendar.getInstance();

		// subtract 1 month //
		c.setTime(timeNow);
		c.add(Calendar.MONTH, -1);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String thisMonth = format.format(new Date()) + "-01";

		String lastMonth = format.format(c.getTime()) + "-01";

		Date thisMonthDate = new SimpleDateFormat("yyyy-MM-dd").parse(thisMonth);
		Date lastMonthDate = (new SimpleDateFormat("yyyy-MM-dd").parse(lastMonth));

		long userThisMonth = userRepo.countUserByDate(thisMonthDate, timeNow);
		long userLastMonth = userRepo.countUserByDate(lastMonthDate, thisMonthDate);

		List<ManagerUser> managerUsers = userRepo.getListTopUser();
		List<ManagerUser> findUserByUsernameOremail = userRepo.findUserByUsernameOrEmail(emailOrUsername);

		model.addAttribute("topUsers", managerUsers);
		model.addAttribute("findUser", findUserByUsernameOremail);
		model.addAttribute("totalUser", userRepo.count());
		model.addAttribute("userThisMonth", userThisMonth);
		model.addAttribute("userLastMonth", userLastMonth);
		model.addAttribute("thisMonth", format.format(thisMonthDate));
		model.addAttribute("lastMonth", format.format(lastMonthDate));

		return "adminTemplates/user";
	}

	@GetMapping("/question")
	public String questionView(Model model, @RequestParam(value = "s", required = false) String key)
			throws ParseException {
		Date timeNow = new Date();
		Calendar c = Calendar.getInstance();

		// subtract 1 month //
		c.setTime(timeNow);
		c.add(Calendar.MONTH, -1);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String thisMonth = format.format(new Date()) + "-01";

		String lastMonth = format.format(c.getTime()) + "-01";

		Date thisMonthDate = new SimpleDateFormat("yyyy-MM-dd").parse(thisMonth);
		Date lastMonthDate = (new SimpleDateFormat("yyyy-MM-dd").parse(lastMonth));

		///////////////////////////

		long totalQuestion = questionRepo.count();
		long questionThisMonth = questionRepo.countQuestionByDate(thisMonthDate, timeNow);
		long questionLastMonth = questionRepo.countQuestionByDate(lastMonthDate, thisMonthDate);

		List<ManagerQuestionDto> getListQuestion = questionRepo.getListQuestion();
		List<ManagerQuestionDto> getQuestionByKey = questionRepo.getQuestionByKey("%" + key + "%");

		/////////////////////////////
		model.addAttribute("thisMonth", format.format(thisMonthDate));
		model.addAttribute("lastMonth", format.format(lastMonthDate));
		model.addAttribute("totalQuestion", totalQuestion);
		model.addAttribute("questionThisMonth", questionThisMonth);
		model.addAttribute("questionLastMonth", questionLastMonth);
		model.addAttribute("topQuestions", getListQuestion);
		model.addAttribute("searchQuestion", getQuestionByKey);

		return "adminTemplates/question";
	}

	@PostMapping("/category/add")
	public String addCategory(Category category) {

		categoryRepo.save(category);

		return "redirect:/admin/category";
	}

	@PostMapping("/question/delete/{id}")
	public String deleteQuestion(HttpServletRequest req, @PathVariable(name = "id") Long id) {

		Question question = questionRepo.findById(id).get();

		if (question != null) {
			questionRepo.delete(question);
		}

		return "redirect:" + req.getHeader("Referer");
	}

	@GetMapping("/category/delete/{id}")
	public String deleteCategory(@PathVariable(name = "id") Long id) {

		System.out.println(id);

		Category category = categoryRepo.findById(id).get();

		if (category != null) {
			categoryRepo.delete(category);
		}

		return "redirect:/admin/category";
	}

	@GetMapping("/user/{username}")
	public String informationUser(@PathVariable(name = "username") String username, Model model) {

		AppUser user = userRepo.findByUsername(username);

		if (user == null) {
			return "redirect:/admin/user";
		}

		List<ManagerQuestionDto> questions = questionRepo.getListQuestionByUserId(user.getId());

		model.addAttribute("user", user);
		model.addAttribute("questions", questions);

		return "adminTemplates/informationUser";
	}
}