package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection cnn;

	public SellerDaoJDBC(Connection cnn) {
		this.cnn = cnn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = cnn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			pst.setInt(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				Department dep = Department.instanceDepartment(rs);
				Seller seller = Seller.instanceSeller(rs, dep);
				return seller;
			}

			return null;

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = cnn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name;");

			rs = pst.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = Department.instanceDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller seller = Seller.instanceSeller(rs, dep);
				list.add(seller);
			}

			return list;

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = cnn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name;");

			pst.setInt(1, department.getId());
			rs = pst.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = Department.instanceDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}

				Seller seller = Seller.instanceSeller(rs, dep);
				list.add(seller);
			}

			return list;

		} catch (SQLException ex) {
			throw new DbException(ex.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
}
