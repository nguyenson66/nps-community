package npscommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import npscommunity.entity.Answer;
import npscommunity.entity.Question;
import npscommunity.repository.AnswerRepository;
import npscommunity.repository.QuestionRepository;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
@CrossOrigin(origins = "*")
public class RestAPIController {
	@Autowired
	private QuestionRepository quesRepo;
	
	@Autowired
	private AnswerRepository ansRepo;
	
	@GetMapping("/questions")
	public Iterable<Question> getAllQuestion () {
		return quesRepo.findAll();
	}
	
	@PostMapping("/questions/{id}/upvote")
	public Question quesUpVote(@PathVariable Long id) {
		Question ques = quesRepo.findById(id).get();
		ques.voteUp();
		quesRepo.save(ques);
		return ques;
	}
	
	@PostMapping("/questions/{id}/downvote")
	public Question quesDownVote(@PathVariable Long id) {
		Question ques = quesRepo.findById(id).get();
		ques.voteDown();
		quesRepo.save(ques);
		return ques;
	}
	
	@PostMapping("/answers/{id}/upvote")
	public Answer anesUpVote(@PathVariable Long id) {
		Answer ans = ansRepo.findById(id).get();
		ans.voteUp();
		ansRepo.save(ans);
		return ans;
	}
	
	@PostMapping("/answers/{id}/downvote")
	public Answer ansDownVote(@PathVariable Long id) {
		Answer ans = ansRepo.findById(id).get();
		ans.voteDown();
		ansRepo.save(ans);
		return ans;
	}
}
