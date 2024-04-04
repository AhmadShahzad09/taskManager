package project.java.repositories;

import project.java.models.Task;

public interface ITaskRepository extends IRepository<Task> {
    /**
     * update properties of the given item
     * @param item item to update
     */
    void update(Task item);

    /**
     * get task by its ID
     * @param id given ID
     * @return task with the given ID
     */
    Task getById(int id);
}
