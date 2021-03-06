package org.green.crud1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class TeacherDAO {
	
	private static final TeacherDAO instance = new TeacherDAO();
		
	private TeacherDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}			
	}
	
	public static TeacherDAO getInstance(){
		return instance;
	}
	
	
	
	public Connection connect(){
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return con;		
	}
	
	public int insert(Connection con, Teacher t){	
		String sql = "INSERT INTO teacher (name, age, subject )" 
				+ "VALUES ( ? , ? , ?)";		
		
		PreparedStatement pStmt = null;
		int resultRow = 0;
		try {
			pStmt = con.prepareStatement(sql);
			pStmt.setString(1, t.getName());
			pStmt.setInt(2, t.getAge());
			pStmt.setString(3, t.getSubject());
			resultRow = pStmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			close(pStmt);
		}
		
		return resultRow;
	}
	
	public List<Teacher> getAll(Connection con){
		String sql = "SELECT * FROM teacher ORDER BY id DESC";
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		Vector<Teacher> list = null;
		try{
			pStmt = con.prepareStatement(sql);
			rs = pStmt.executeQuery();
			list = new Vector<Teacher>();
			while(rs.next()){
				
				list.add(new Teacher(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("age"),
						rs.getString("subject")						
						));
				
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	private void close(Statement stmt){
		try {
			if(stmt != null) stmt.close();
		} catch(SQLException e) {}
	}
	
	public void close(Connection con){
		try {
			if(con != null) con.close();
		} catch(SQLException e){}
	}
	
	
}
