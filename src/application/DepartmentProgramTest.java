package application;

import db.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentProgramTest {

	public static void main(String[] args) {
		
		DepartmentDao depDao = new DaoFactory().createDepartmentDao();
		
		// Teste com o insert
		Department dep = new Department(null, "Clothes");
		depDao.insert(dep);
		System.out.println("Inserted! Id = " + dep.getId());
		
		System.out.println();
	}
}
