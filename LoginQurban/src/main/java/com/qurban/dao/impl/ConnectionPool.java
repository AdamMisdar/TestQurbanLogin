package com.qurban.dao.impl;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

public class ConnectionPool {
	
	private ConnectionPool() {
		
	}
	
	// Connection instance
	private Connection connection = getConnection();
	
	private static ConnectionPool instance = null;
	
	public static ConnectionPool getInstance(){
		if (instance==null)
			instance = new ConnectionPool();
		return instance;
	}
	
	/**
	 * Getting connection from connection pool.
	 *
	 * @see ConnectionPool
	 * @throws SQLException
	 */
	
	public Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String username = "xznwdneuajrbej";
		String password = "1af98ac62f4e9c123a7196b8a144f047cc93dd613d1ab12897ec80ed529e2d5c";
		String dbUrl = "jdbc:postgresql://"
						+ "ec2-52-22-136-117.compute-1.amazonaws.com:5432"
						+ "/d3f62gbdclmsp3?sslmode=require"; // '?sslmode=require' : it's to connect to the database from our machine
		
		try {
			return DriverManager.getConnection(dbUrl, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
		}
}
