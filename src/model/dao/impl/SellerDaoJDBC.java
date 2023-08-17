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
import model.dao.SellerDao;

public class SellerDaoJDBC implements SellerDao {

	
	
	private Connection conn;
	@Override
	public void insert(Seller obj) {
		
		conn = DB.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)");
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDp().getId());
			
			int rows = st.executeUpdate();
			
			System.out.println("Rows affected: " + rows);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Seller obj) {
		
		conn = DB.getConnection();
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE seller SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4,  obj.getBaseSalary());
			st.setInt(5,  obj.getDp().getId());
			st.setInt(6, obj.getId());
			
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
			
			st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			System.out.println("Rows affected:" + rows);
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Seller findById(Integer id) {
		
		
		
		conn = DB.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department on seller.DepartmentId = department.Id WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department d1 = new Department();
				d1.setId(rs.getInt("DepartmentId"));
				d1.setName(rs.getString("DepName"));
				
				Seller s1 = new Seller();
				s1.setId(rs.getInt("Id"));
				s1.setName(rs.getString("Name"));
				s1.setEmail(rs.getString("Email"));
				s1.setBirthDate(rs.getDate("BirthDate"));
				s1.setBaseSalary(rs.getDouble("BaseSalary"));
				s1.setDp(d1);
				
				return s1;
				
			}
			
			return null;
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<Seller> findAll() {
		
		List<Seller> list = new ArrayList<>();
		
		conn = DB.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st= conn.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department on seller.DepartmentId = department.id");
			rs = st.executeQuery();
			
			while(rs.next()) {
				
				Department d1 = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
				Seller s1 = new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), d1);
				
				list.add(s1);
			
			}
		}catch(SQLException e) {
				e.printStackTrace();
			}

	
		return list;
	}

}
