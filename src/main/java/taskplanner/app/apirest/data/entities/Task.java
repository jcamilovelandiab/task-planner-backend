package taskplanner.app.apirest.data.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

    @Id
    private ObjectId _id;
	String strId;
    String title;
    String status;
    String dueDate;
    String description;
    @DBRef
    User responsible;

}
