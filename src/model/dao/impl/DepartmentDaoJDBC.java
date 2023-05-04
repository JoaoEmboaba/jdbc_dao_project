package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

			pst = cnn.prepareStatement("" + "INSERT INTO department (Name) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, department.getName());

			int rows = pst.executeUpdate();

			if (rows > 0) {
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
				} else {
					throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
				}
			}

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void update(Department department) {
		PreparedStatement pst = null;

		try {
			pst = cnn.prepareStatement("" + "UPDATE department SET Name = ? WHERE Id = ?",
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, department.getName());
			pst.setInt(2, department.getId());

			int rows = pst.executeUpdate();

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		try {
			pst = cnn.prepareStatement("" + "DELETE FROM department WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, id);

			int rows = pst.executeUpdate();

			if (rows == 0) {
				throw new DbException("Erro inesperado! Esse Id não existe!");
			}

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			pst = cnn.prepareStatement("" + "SELECT department.Id as departmentId, department.Name as DepName "
					+ "FROM department WHERE Id = ?");

			pst.setInt(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				Department dep = Department.instanceDepartment(rs);
				return dep;
			}

			return null;

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(pst);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = cnn.prepareStatement(
					"" + "SELECT department.Id as departmentId, department.Name as DepName FROM" + " department");

			rs = pst.executeQuery();
			List<Department> lista = new ArrayList<>();

			while (rs.next()) {
				Department dep2 = Department.instanceDepartment(rs);
				lista.add(dep2);
			}

			return lista;

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		}
	}
}
