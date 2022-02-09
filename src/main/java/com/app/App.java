package com.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.employee.EmployeeRegistration;

import static com.util.HibernateUtil.getSessionFactory;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws Exception {
		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		EmployeeRegistration empReg = new EmployeeRegistration();
		empReg.register();
	}
}
