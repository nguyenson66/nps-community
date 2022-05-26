package npscommunity.dto;

import lombok.Data;

@Data
public class ManagerQuestionDto {
    public Long id;
    public String title;
    public String content;
    public String name;
    public Long total_answer;
    public Long view;

    public ManagerQuestionDto(Long id, String title, String content, String name, Long total_answer, Long view) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.total_answer = total_answer;
        this.view = view;
    }
}
