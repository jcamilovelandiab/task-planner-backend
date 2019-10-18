package taskplanner.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import taskplanner.app.apirest.TaskPlannerApp;
import taskplanner.app.apirest.entities.User;
import taskplanner.app.apirest.services.IUserServices;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskPlannerApp.class)
public class ApplicationTest {

    @Autowired
    @Qualifier("userService")
    IUserServices userServices;

    @Test
    public void getUserTest() throws Exception {
        User user = new User("100", "camilotaskplanner", "camilo", "camilo@gmail.com", "camilo");
        userServices.createUser(user);
        Assert.assertEquals(userServices.getUserByUsername("camilotaskplanner").getUsername(),("camilotaskplanner"));
        Assert.assertEquals(userServices.getUserByEmail("camilo@gmail.com").getEmail(),("camilo@gmail.com"));
    }
}