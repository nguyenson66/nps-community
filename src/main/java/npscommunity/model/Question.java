package npscommunity.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Question {
	private long id;
	
	private long user_id;
	private long category_id;
	private String title;
	private String content;
	private long viewed;
	private long vote_up;
	private long vote_down;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	public Question(long user_id, String title, String content) {
		super();
		this.user_id = user_id;
		this.title = title;
		this.content = content;
	}

	public Question(long id, long user_id, long category_id, String title, String content, long viewed, long vote_up,
			long vote_down, Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.category_id = category_id;
		this.title = title;
		this.content = content;
		this.viewed = viewed;
		this.vote_up = vote_up;
		this.vote_down = vote_down;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}
