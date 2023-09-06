package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
	Scanner sc = new Scanner(System.in);
    /*	
    SELLER TESTS: 
    
	SellerDao sellerDao = DaoFactory.createSellerDao();
	//instanciou um SellerDaoJDBC
		
	System.out.println("=== TEST 1: seller findById ===");
	Seller seller = sellerDao.findById(3);
	System.out.println(seller);
		
	
	System.out.println("\n=== TEST 2: seller findByDepartment ===");
	Department department = new Department(2, null);
	List<Seller> list = sellerDao.findByDepartment(department);
	for (Seller obj : list) {
		System.out.println(obj);
	}
	
	
	System.out.println("\n=== TEST 3: seller findAll ===");
	List<Seller> list = sellerDao.findAll();
	for (Seller obj : list) {
		System.out.println(obj);
	}

	
	Department department = new Department(2, null);
	System.out.println("\n=== TEST 4: seller insert ===");
	Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
	sellerDao.insert(newSeller);
	System.out.println("Inserted! New id = " + newSeller.getId());
	
	
	System.out.println("\n=== TEST 3: seller update ===");
	Seller seller = sellerDao.findById(1);
	seller.setName("Martha Waine");
	//atualizando o valor da variável na classe
	sellerDao.update(seller);
	//atualizando no banco de dados
	System.out.println("Update completed!");
	
	
	System.out.println("\n=== TEST 3: seller delete ===");
	System.out.println("Enter id for delete test: ");
	int id = sc.nextInt();
	sellerDao.deleteById(id);
	System.out.println("Delete completed!");
	
	--------------------------------------------------------------------
	Department TESTS:
		
	System.out.println("=== TEST 1: department insert ===");
	Department newDep = new Department(5, "Toys");
	dep.insert(newDep);
	System.out.println("Inserted! New id = " + newDep.getId());
    
	
	
	System.out.println("=== TEST 2: department findAll ===");
	List<Department> list = dep.findAll();
	for (Department obj : list) {
		System.out.println(obj);
	}*/
	
	DepartmentDao dep = DaoFactory.createDepartmentDao();	
	System.out.println("=== TEST 3: department findById ===");
	Department newdep = dep.findById(2);
	System.out.println(newdep);
	
	sc.close();
	
	}

}
