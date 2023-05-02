package application;

import java.util.Scanner;

import db.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		// Teste com o findById
		Scanner read = new Scanner(System.in);
		System.out.println("Informe o id de um usuário: ");
		int id = read.nextInt();
		SellerDao sellerDao = new DaoFactory().createSellerDao();
		Seller seller = sellerDao.findById(id);

		System.out.println(seller);

	}
}
