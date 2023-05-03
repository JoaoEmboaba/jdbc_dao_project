package application;

import java.util.Date;
import java.util.List;

import db.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		// Teste com o findById
		SellerDao sellerDao = new DaoFactory().createSellerDao();
		Seller seller = sellerDao.findById(77);
		System.out.println(seller);

		System.out.println();

		// Teste com o findByDepartment
		Department dep = new Department(8, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println();

		// Teste com o findAll
		dep = new Department(8, null);
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);

		}
		
		System.out.println();
		
		// Teste com o insert
		Seller sellerTest = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep);
		sellerDao.insert(sellerTest);
		System.out.println("Inserted! New id = " + sellerTest.getId());
	}
}
