package npscommunity.model;


import java.sql.Timestamp;


import lombok.Data;

@Data
public class User {
	private long id;
	
	private String username;
	private String password;
	private String email;
	private String avatar;
	private String name;
    private String address;
    private Timestamp birthday;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    public User() {
    	
    }
    
	public User(long id, String username, String password, String email, String avatar, String name, String address,
			Timestamp birthday, Timestamp created_at, Timestamp updated_at) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.avatar = avatar;
		this.name = name;
		this.address = address;
		this.birthday = birthday;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}	
}
