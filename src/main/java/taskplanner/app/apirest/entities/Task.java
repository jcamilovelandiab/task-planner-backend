package taskplanner.app.apirest.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
	String id;
    String title;
    String dueDate;
    String description;
    User responsible;

}
