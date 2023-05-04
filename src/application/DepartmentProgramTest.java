package application;

import java.util.List;

import db.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentProgramTest {

	public static void main(String[] args) {

		DepartmentDao depDao = new DaoFactory().createDepartmentDao();

		// Teste com o findyById
		Department dep3 = depDao.findById(10);
		System.out.println(dep3);

		System.out.println();

		// Teste com o findAll
		List<Department> lista = depDao.findAll();
		for (Department dep : lista) {
			System.out.println(dep);
		}

		// Teste com o insert
		Department dep = new Department(null, "Periferics");
		depDao.insert(dep);
		System.out.println("Inserted! Id = " + dep.getId());

		System.out.println();

		// Teste com o update
		Department dep1 = new Department(7, "Almox");
		depDao.update(dep1);
		System.out.println("Updated! Id = " + dep1.getId());

		System.out.println();

		// Teste com o deleteById
		Department dep2 = new Department(19);
		depDao.deleteById(dep2.getId());
		System.out.println("Deleted! Id excluded = " + dep2.getId());

	}
}
