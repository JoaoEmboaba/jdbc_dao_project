package db;

import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import db.DB;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
}
