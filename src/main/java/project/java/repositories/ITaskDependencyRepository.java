package project.java.repositories;

import project.java.models.Task;
import project.java.models.TaskDependency;

import java.util.List;

public interface ITaskDependencyRepository extends IRepository<TaskDependency> {
    /**
     * get all dependencies of the given task
     * @param task task that we gather dependencies for
     * @return list of dependencies for this task
     */
    List<TaskDependency> getDependeciesOfTask(Task task);
}
