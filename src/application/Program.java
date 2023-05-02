package application;

import java.util.List;
import java.util.Scanner;

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
	}
}
