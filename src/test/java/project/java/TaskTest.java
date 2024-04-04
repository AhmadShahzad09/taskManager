package project.java;

import project.java.models.Task;
import project.java.models.TaskStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testThatStatusIsNew() {
        Task task = new Task(1, "Title", "Description", 5);
        Assertions.assertEquals(TaskStatus.New, task.getStatus());
    }
}
