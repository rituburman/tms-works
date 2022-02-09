package com.employee;

import java.io.IOException;
import static com.util.HibernateUtil.getSessionFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.EmployeeDAO;
import com.util.Utility;

public class ForgotPassword {
	
	public void forgotPassword() throws IOException {

		EmployeeDAO dao = new EmployeeDAO();
		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		
		System.out.println("Enter your Username::");
		String username = Utility.stringUtil();
		System.out.println("Create new Password::");
		String password = Utility.stringUtil();

		dao.changePassword(session, username, password);

	}

}
