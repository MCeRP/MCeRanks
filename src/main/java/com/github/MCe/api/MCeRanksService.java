package com.github.MCe.api;

import com.github.MCe.MCeRanks;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * MCe default implementation of Ranks API. Uses an underlying JDBC SQLite interface.
 */
public class MCeRanksService extends Ranks {

    private MCeRanks plugin;
    private Connection connection = null;

    /**
     * @param plugin The parent plugin
     * @throws ClassNotFoundException if there is trouble loading JDBC
     */
    public MCeRanksService(final MCeRanks plugin) throws ClassNotFoundException {
        this.plugin = plugin;
        createDataFolderIfNotExists();
        openDatabaseConnection();
    }

    /** Ensures the data folder for the associated plugin was created. */
    private final void createDataFolderIfNotExists() {
        //ensure existence of data folder
        if(!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

    }

    /**
     * Opens the database connection.
     * @throws ClassNotFoundException if JDBC could not be loaded
     */
    private final void openDatabaseConnection() throws ClassNotFoundException {
        //database file
        String db_file = plugin.getDataFolder() + File.pathSeparator + "player_ranks.db";

        Class.forName("org.sqlite.JDBC");
        closeDatabaseConnection();

        connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:" + db_file);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(1);  // set timeout to 1 sec; any longer and the game will be /terribly effected

        } catch(SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            plugin.getLogger().severe(e.toString());
        } finally {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                plugin.getLogger().severe(e.toString());
            }
        }
    }

    /** Closes the associated database connection, if there is one. */
    private void closeDatabaseConnection() {
        try
        {
            if(connection != null)
                connection.close();
        }
        catch(SQLException e)
        {
            plugin.getLogger().severe(e.toString());
        }
    }

    @Override
    public String[] getSkills() {
        return new String[0];
    }

    @Override
    public String getSkillDescription(String skill) {
        return null;
    }

    @Override
    public int getPlayerRanksInSkill(String player_name, String skill) {
        return 0;
    }


    boolean _closed = false;

    public void close() {
        closeDatabaseConnection();
        _closed = true;
    }

    protected void finalize() {
        if(!_closed) {
            close();
            plugin.getLogger().severe("MCeRanksService DB Connection not close()ed before destruction.");
        }
    }
}
