package project.java.repositories;

import project.java.db.DbManager;
import project.java.exceptions.DatabaseCommunicationException;

import java.sql.SQLException;

public abstract class BaseRepository<T> implements IRepository<T> {

    /**
     * connection to the database
     */
    protected final DbManager dbManager;

    protected BaseRepository(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * handle thrown SQL exception
     * @param exception thrown exception
     */
    protected void handleSQLException(SQLException exception) {
        exception.printStackTrace();
        throw new DatabaseCommunicationException();
    }
}
