package npscommunity.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import npscommunity.dto.ManagerCategory;

@Entity
@Data
@Table(name = "category")

@NamedNativeQuery(name = "get_list_category", query = "select c.id as id, c.name as name, count(q.id) as total_question, sum(q.viewed) as total_view from category c"
		+ " left join question_categories qc on c.id = qc.categories_id"
		+ " left join Question q on q.id = qc.questions_id"
		+ " group by c.id", resultSetMapping = "get_list_category_dto")

@SqlResultSetMapping(name = "get_list_category_dto", classes = @ConstructorResult(targetClass = ManagerCategory.class, columns = {
		@ColumnResult(name = "id", type = Long.class),
		@ColumnResult(name = "name", type = String.class),
		@ColumnResult(name = "total_question", type = Long.class),
		@ColumnResult(name = "total_view", type = Long.class)
}))

public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto increment
	private long id;

	@Column()
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	@JsonIgnore
	List<Question> questions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
