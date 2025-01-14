package project.java.repositories;

import project.java.db.DbManager;
import project.java.models.Task;
import project.java.models.TaskDependency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDependencyRepository extends BaseRepository<TaskDependency> implements ITaskDependencyRepository {

    public TaskDependencyRepository(DbManager dbManager) {
        super(dbManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskDependency> getAll() {
        try (var dbConnection = dbManager.getConnection()) {
            Statement statement = dbConnection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("select * from taskDependencies");
            return getDataFromResultSet(rs);
        }
        catch (SQLException throwables) {
            handleSQLException(throwables);
        }
        return new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(TaskDependency item) {
        try (var dbConnection = dbManager.getConnection()) {
            PreparedStatement statement = dbConnection.prepareStatement("delete from taskDependencies where id=?");
            statement.setInt(1, item.getId());
            statement.execute();
        }
        catch (SQLException throwables) {
            handleSQLException(throwables);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(TaskDependency item) {
        try (var dbConnection = dbManager.getConnection()) {
            PreparedStatement statement = dbConnection.prepareStatement("insert into taskDependencies(taskId, dependsOn) values (?,?)");
            statement.setInt(1, item.getTaskId());
            statement.setInt(2, item.getDependsOnTaskId());
            statement.execute();
        }
        catch (SQLException throwables) {
            handleSQLException(throwables);
        }
    }

    @Override
    public List<TaskDependency> getDependeciesOfTask(Task task) {
        try (var dbConnection = dbManager.getConnection()) {
            PreparedStatement statement = dbConnection.prepareStatement("select * from taskDependencies where taskId=?");
            statement.setInt(1, task.getId());
            ResultSet rs = statement.executeQuery();
            return getDataFromResultSet(rs);
        }
        catch (SQLException throwables) {
            handleSQLException(throwables);
        }
        return new ArrayList<>();
    }

    /**
     * collect all TaskDependencies from the result set and return it
     *
     * @param rs given result set
     * @return list of all dependencies from the result set
     * @throws SQLException
     */
    private List<TaskDependency> getDataFromResultSet(ResultSet rs) throws SQLException {
        var taskDependencies = new ArrayList<TaskDependency>();
        while (rs.next()) {
            var taskDependency = TaskDependency.fromResultSet(rs);
            taskDependencies.add(taskDependency);
        }
        return taskDependencies;
    }
}
