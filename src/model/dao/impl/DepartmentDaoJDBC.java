package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.Department;
import model.Seller;
import model.dao.DepartmentDao;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;

	@Override
	public void insert(Department obj) {
		conn = DB.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)");
			st.setString(1, obj.getName());
			
			int rows = st.executeUpdate();
			
			System.out.println("Rows affected: " + rows);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Department obj) {

		conn = DB.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			int rows = st.executeUpdate();
			if(rows > 0) {
				System.out.println("Rows affected " + rows);
			}else {
				System.out.println("Id not found");
			}
	
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		
		conn = DB.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			System.out.println("Rows affected:" + rows);
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Department findById(Integer id) {
		conn = DB.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * from department where id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department d1 = new Department();
				d1.setId(rs.getInt("Id"));
				d1.setName(rs.getString("Name"));
				
				return d1;
				
			}
			
			return null;
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<Department> findAll() {
		
		
List<Department> list = new ArrayList<>();
		
		conn = DB.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st= conn.prepareStatement("SELECT * from department");
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				Department d1 = new Department(rs.getInt("Id"), rs.getString("Name"));			
				list.add(d1);
			
			}
		}catch(SQLException e) {
				e.printStackTrace();
			}

	
		return list;
	}

}
