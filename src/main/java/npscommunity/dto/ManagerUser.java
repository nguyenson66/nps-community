package npscommunity.dto;

import lombok.Data;

@Data
public class ManagerUser {
    public Long id;
    public String name;
    public String username;
    public String email;
    public Long total_question;
    public Long total_answer;
    public Long total_vote;

    public ManagerUser(Long id, String name, String username, String email, Long total_question, Long total_answer,
            Long total_vote) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.total_question = total_question;
        this.total_answer = total_answer;

        if (total_vote != null)
            this.total_vote = total_vote;
        else
            this.total_vote = (long) 0;
    }

    public String toString() {
        return name + " " + username + " " + email + " " + total_question + " " + total_answer + " " + total_vote;
    }
}
