package com.employee;

import java.io.IOException;

import static com.util.HibernateUtil.getSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.EmployeeBean;
import com.dao.EmployeeDAO;
import com.util.Utility;

public class EmpProfile {
	
	
	public void viewProfile(EmployeeBean emp) {
		
		
		System.out.println("Employee Name :" + emp.getEmpName());
		System.out.println("Employee Email :" + emp.getEmpEmail());
		System.out.println("Employee Contact Number :" + emp.getEmpMb());
		System.out.println("Employee Company Name :" + emp.getEmpCompanyName());

		System.out.println("Press 1 to Go Back.");
		System.out.println("Press 2 to Exit.");

		try {
			int ch = Utility.integerUtil();

			switch (ch) {

			case 1:
				EmployeeDashboard dashboard = new EmployeeDashboard();
				dashboard.dashBoard(emp);
				break;
			case 2:
				System.out.println("Exit");
				break;
			default:
				System.out.println("Invalid choice.");
				break;

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void editProfile(EmployeeBean emp) {
		EmployeeDAO dao = new EmployeeDAO();

		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		

		try {
			System.out.println("Employee Contact Number::" + emp.getEmpMb());

			System.out.println("Enter new Contact Number::");
			int mb = Utility.integerUtil();
			emp.setEmpMb(mb);

			System.out.println("Employee Company Name :" + emp.getEmpCompanyName());
			System.out.println("Enter new Company name::");
			String company = Utility.stringUtil();
			emp.setEmpCompanyName(company);

			dao.updateEmployee(emp, session);
			//session.close();

		}

		catch (IOException e) {
			System.out.println("Trainer Updation failed" + e);
		}

	}

}
