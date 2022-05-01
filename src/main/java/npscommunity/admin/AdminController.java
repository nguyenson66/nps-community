package npscommunity.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import npscommunity.admin.Dto.LoginDto;
import npscommunity.admin.Dto.RegisterDto;
import npscommunity.admin.repository.AdminRepositoty;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminRepositoty adminRepository;
	
	@GetMapping("login")
	public String login() {
		return "adminTemplates/login";
	}
	
	@GetMapping()
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
	
	@PostMapping("/login")
	public void loginPost(@RequestBody LoginDto data) {
		System.out.println(data.getUsername());
	}
	
	@PostMapping("/register")
	public String registerUserAdmin() {
		
//		System.out.println(registerDto);
		
		return "done";
	}
}
