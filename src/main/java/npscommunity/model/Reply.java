package npscommunity.model;

import lombok.Data;

@Data
public class Reply {

	private long id;
	private long user_id;
	private long question_id;
	private String content;
	private long vote_up;
	private long vote_down;
	
	
	
	public Reply(long id, long user_id, long question_id, String content, long vote_up, long vote_down) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.question_id = question_id;
		this.content = content;
		this.vote_up = vote_up;
		this.vote_down = vote_down;
	}
	
}
