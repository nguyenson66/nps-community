package npscommunity.dto;

import lombok.Data;

@Data()
public class ManagerCategory {
    public Long id;
    public String name;
    public Long total_question;
    public Long total_view;

    public ManagerCategory(Long id, String name, Long total_question, Long total_view) {
        this.id = id;
        this.name = name;
        this.total_question = total_question;
        this.total_view = total_view;
    }
}
