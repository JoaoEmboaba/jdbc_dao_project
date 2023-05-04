package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private static Connection cnn;
	
	public DepartmentDaoJDBC(Connection cnn) {
		this.cnn = cnn;
	}
	
	@Override
	public void insert(Department department) {
		PreparedStatement pst = null;
		try {
			
			pst = cnn.prepareStatement(""
					+ "INSERT INTO department (Name) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, department.getName());
			
			int rows = pst.executeUpdate();
			
			if(rows > 0) {
				ResultSet rs = pst.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
				}else {
					throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
				}
			}
			
		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		}finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
