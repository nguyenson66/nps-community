package npscommunity.model;

import lombok.Data;

@Data
public class Question {
	private long question_id;
	
	private long user_id;
	private String title;
	private String description;
	
	public Question(long user_id, String title, String description) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.description = description;
	}

	public Question(long question_id, long user_id, String title, String description) {
		super();
		this.question_id = question_id;
		this.user_id = user_id;
		this.title = title;
		this.description = description;
	}
	
	
	
}
