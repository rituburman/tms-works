package com.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.bean.EmployeeBean;
import com.employee.EmployeeDashboard;

public class EmployeeDAO implements IEmployeeDAO {


	public void saveEmployee(Session session, EmployeeBean emp) {
		session.save(emp);
		session.getTransaction().commit();
		System.out.println("Congratulations! You have been registered.");
		
	}

	/*public void getEmployee(Session session, String username, String password) {
		EmployeeDashboard dashboard = new EmployeeDashboard();
		try {
			String str = "from EmployeeBean emp where emp.empUsername=:username and emp.empPassword=:password";
			Query<EmployeeBean> query = session.createQuery(str, EmployeeBean.class);

			query.setParameter("username", username);
			query.setParameter("password", password);
			EmployeeBean emp = (EmployeeBean)query.uniqueResult();
			if (emp != null)
				
				dashboard.dashBoard(emp);

			else
				System.out.println("Incorrect Username or Password. Please try again.");
		} catch (Exception e) {
			System.out.println("Signing in failed. Please try again.");
			e.printStackTrace();
		}
		}*/
	
	public void getEmployee(Session session, String username, String password) {
		EmployeeDashboard dashboard = new EmployeeDashboard();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<EmployeeBean> query = criteriaBuilder.createQuery(EmployeeBean.class);
		Root<EmployeeBean> root = query.from(EmployeeBean.class);
		query.select(root).where(criteriaBuilder.equal(root.get("empUsername"), username),
				criteriaBuilder.equal(root.get("empPassword"), password));

		Query<EmployeeBean> employeeQuery = session.createQuery(query);
		EmployeeBean emp = employeeQuery.getSingleResult();
		dashboard.dashBoard(emp);

	}
		


	public void updateEmployee(EmployeeBean emp, Session session) {
		session.merge(emp);
		session.getTransaction().commit();
		System.out.println("Mobile Number and Company Name updated successfully!"); 
		EmployeeDashboard dashboard = new EmployeeDashboard();
		dashboard.dashBoard(emp);
		
	}

	public void changePassword(Session session, String username, String password) {
		EmployeeDashboard dashboard = new EmployeeDashboard();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<EmployeeBean> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(EmployeeBean.class);
		Root<EmployeeBean> root = criteriaUpdate.from(EmployeeBean.class);
		criteriaUpdate.set("empPassword", password);
		criteriaUpdate.where(criteriaBuilder.equal(root.get("empUsername"), username));
		System.out.println("Password Updated!");


		session.createQuery(criteriaUpdate).executeUpdate();
		session.getTransaction().commit();



		}
}
	
	/*public void changePassword(Session session, String username, String password) {
		
		  String query = "UPDATE EmployeeBean SET empPassword = '" + password + "' WHERE empUsername = '" + username + "'";
	        try {
	            session.createSQLQuery(query).executeUpdate();
	            session.getTransaction().commit();
	            System.out.println("password Changed....");*/
	       //} catch (HibernateException e) {
	           // session.getTransaction().rollback();
	        //}
		
		/*Query query = session.createQuery("update EmployeeBean emp set emp.empPassword =:password where emp.empUsername =:username");
		query.setParameter("username", username);
		query.setParameter("password", password);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		//String query = "update EmployeeBean set empPassword =:password where emp.empUsername =:username";
		/*try {
			session.createQuery(query).executeUpdate();
			session.getTransaction().commit();
			System.out.println("Password Updated!");
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}*/

		

