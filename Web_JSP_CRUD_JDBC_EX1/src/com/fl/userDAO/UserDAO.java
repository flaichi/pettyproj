package com.fl.userDAO;


import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fl.model.User;

public class UserDAO {
	
	
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/sampledb?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "farouk";
	
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES "
			+ " (?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
	
	
	public Connection getConnection() {
				Connection connection = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return connection;
	}
	
	
	public void insertUser(User user) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(INSERT_USERS_SQL);
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getCountry());
		stmt.executeUpdate();
		conn.close();
	}
	
	public boolean  deleteUser(int id) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(DELETE_USERS_SQL);	
		stmt.setInt(1, id);
		boolean result = stmt.executeUpdate() > 0;
		conn.close();
		return result;
	}
	
	public boolean  updateUSer(User user) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(UPDATE_USERS_SQL);
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getCountry());
		stmt.setInt(4, user.getId());
		boolean result = stmt.executeUpdate() > 0;
		conn.close();
		return result;
	}
	
	public List<User>  selectAllUser() throws SQLException {
		List<User>  userList  = new ArrayList<User>();
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_USERS);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			User user = new User();
			user.setId(result.getInt(1));
			user.setName(result.getString(2));
			user.setEmail(result.getString(3));
			user.setCountry(result.getString(4));
			userList.add(user);
		}
		return userList;		
	}
	
	public User  selectUser(int id) throws SQLException {
		User user = new User();
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_ID);
		stmt.setInt(1,id);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			user.setId(result.getInt(1));
			user.setName(result.getString(2));
			user.setEmail(result.getString(3));
			user.setCountry(result.getString(4));
		}
		return user;		
	}
}




