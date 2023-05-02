package application;

import java.util.Scanner;

import db.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		//Teste com o findById
		SellerDao sellerDao = new DaoFactory().createSellerDao();
		Seller seller = sellerDao.findById(110);
		
		System.out.println(seller);
		
	}
}
