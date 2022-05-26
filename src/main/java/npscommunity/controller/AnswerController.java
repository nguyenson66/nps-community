package npscommunity.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import npscommunity.entity.Answer;
import npscommunity.repository.AnswerRepository;

@Controller
@RequestMapping("/answers")
public class AnswerController {
	@Autowired
	private AnswerRepository answerRepo;
	
	@PostMapping("/{id}/voteup")
	public String voteUp(HttpServletRequest req, @PathVariable Long id) {
		Answer ans = answerRepo.findById(id).get();
		ans.voteUp();
		answerRepo.save(ans);
		return "redirect:" + req.getHeader("Referer"); 
	}
	
	@PostMapping("/{id}/votedown")
	public String voteDown(HttpServletRequest req, @PathVariable Long id) {
		Answer ans = answerRepo.findById(id).get();
		ans.voteDown();
		answerRepo.save(ans);
		return "redirect:" + req.getHeader("Referer"); 
	}
	
	
	@PostMapping("/delete/{id}")
	public String delete(HttpServletRequest req, @PathVariable Long id) {
		answerRepo.deleteById(id);
		return "redirect:" + req.getHeader("Referer"); 
	}
	
}
