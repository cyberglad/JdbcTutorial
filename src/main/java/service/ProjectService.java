package service;

import bl.Util;
import dao.ProjectDAO;
import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vserdiuk on 2/5/17.
 */
public class ProjectService extends Util implements ProjectDAO {

    private Connection connection = getConnection();


    public void add(Project project) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO [jcaps_mapping_Q].[dbo].[TB_PROJECT](ID, TITLE) VALUES(?, ?)";

        try {
            Long maxID = getMaxID();
            maxID = maxID + 1L;
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, maxID);
            preparedStatement.setString(2, project.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public List<Project> getAll() throws SQLException {
        List<Project> projectList = new ArrayList<Project>();

        String sql = "SELECT ID, TITLE FROM [jcaps_mapping_Q].[dbo].[TB_PROJECT]";

        Statement statement = null;
        try {
            if(connection.isClosed())
                connection = getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getLong("ID"));
                project.setTitle(resultSet.getString("TITLE"));

                projectList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return projectList;
    }


    public Project getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT ID, TITLE FROM [jcaps_mapping_Q].[dbo].[TB_PROJECT] WHERE ID=?";

        Project project = new Project();
        try {
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            project.setId(resultSet.getLong("ID"));
            project.setTitle(resultSet.getString("TITLE"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return project;
    }


    public void update(Project project) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE [jcaps_mapping_Q].[dbo].[TB_PROJECT] SET TITLE=? WHERE ID=?";

        try {
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setLong(2, project.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void remove(Project project) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM [jcaps_mapping_Q].[dbo].[TB_PROJECT] WHERE ID=?";

        try {
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, project.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public Long getMaxID() throws SQLException {
        Long maxId = 0L;

        String sql = "SELECT max(ID) as MAX_ID  FROM [jcaps_mapping_Q].[dbo].[TB_PROJECT]";

        Statement statement = null;
        try {
            if(connection.isClosed())
                connection = getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                maxId = resultSet.getLong("MAX_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return maxId;
    }

}