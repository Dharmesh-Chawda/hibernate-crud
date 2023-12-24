package com.hibtest;

import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hibtest.util.Utils;

public class App 
{
    public static void main(String[] args)
    {
      
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (choice!=7)
		{			
			Session session = Utils.getSession();
			Employee employee = null;
			System.out.println("****************************EMPLOYEE DETAILS****************************");
			System.out.println("1. Add");
			System.out.println("2. Update");
			System.out.println("3. Delete");
			System.out.println("4. Display");
			System.out.println("5. Sort based on id");
			System.out.println("6. Get the Data with range");
			System.out.println("7. Exit");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				employee = new Employee();
				
				System.out.println("Enter the Id of the Employee: ");
				employee.setId(sc.nextInt());
				sc.nextLine();
				
				System.out.println("Enter the Firstname of the Employee: ");
				employee.setFirstName(sc.nextLine());
				
				System.out.println("Enter the Lastname of the Employee: ");
				employee.setLastName(sc.nextLine());
				
				System.out.println("Enter the Post of the Employee: ");
				employee.setpost(sc.nextLine());
				
				System.out.println("Enter the Age of the Employee: ");
				employee.setAge(sc.nextInt());
				
				System.out.println("Enter the Salary of the Employee: ");
				employee.setSalary(sc.nextInt());
			
				Transaction t1 = session.beginTransaction();
				session.save(employee);
				t1.commit();		
				break;
				
			case 2:
				System.out.println("Enter the Id of Employee to Update");
				int id = sc.nextInt();
				employee = session.get(Employee.class, id);
				sc.nextLine();
				if (employee != null)
				{
					System.out.println("Enter Employee Post to Update: ");
                    employee.setpost(sc.nextLine());

                    System.out.println("Enter Employee Age to Update: ");
                    employee.setAge(sc.nextInt());

                    System.out.println("Enter Employee Salary to Update: ");
                    employee.setSalary(sc.nextInt());
					
					Transaction t2 = session.beginTransaction();
					session.update(employee);
					t2.commit();
				} 
				else
				{
					System.out.println("Employee does not exist with id : " + id);
				}
				break;				
					
			case 3:
				System.out.println("Enter the Id of Employee to Delete : ");
				id = sc.nextInt();
				employee = session.get(Employee.class, id);		
				
				if (employee != null)
				{
					Transaction t3 = session.beginTransaction();
					session.delete(employee);
					t3.commit();
				} 
				else
				{
					System.out.println("Employee does not exist with id : " + id);
				}
				
							
			case 4:				
				 Criteria criteria = session.createCriteria(Employee.class);
                 List<Employee> employees = criteria.list();
                 System.out.println("-------List of Employees------");
                 for (Employee e: employees)
                 {                                        
                     System.out.println("Employee ID: "+e.getId());
                     System.out.println("Employee First Name: "+e.getFirstName());
                     System.out.println("Employee Last Name: "+e.getLastName());
                     System.out.println("Employee Age: "+e.getAge());
                     System.out.println("Employee Post: "+e.getpost());
                     System.out.println("Employee Salary: "+e.getSalary());
                     System.out.println("*************************");
                 }				
				break;
			case 5:
				 Criteria criteria1 = session.createCriteria(Employee.class);
				 criteria1.addOrder(Order.asc("id"));
                 List<Employee> employees_sorted = criteria1.list();
                 
                 System.out.println("-------List of Employees Sorted based on ID------");
                 for (Employee e: employees_sorted)
                 {                                        
                     System.out.println("Employee ID: "+e.getId());
                     System.out.println("Employee First Name: "+e.getFirstName());
                     System.out.println("Employee Last Name: "+e.getLastName());
                     System.out.println("Employee Age: "+e.getAge());
                     System.out.println("Employee Post: "+e.getpost());
                     System.out.println("Employee Salary: "+e.getSalary());
                     System.out.println("*************************");
                 }	
                 break;
                 
			case 6:
				System.out.println("Enter Start Range of Salary: ");
				int start_sal = sc.nextInt();
				
				System.out.println("Enter End Range of Salary: ");
				int end_sal =sc.nextInt();
				 Criteria criteria2 = session.createCriteria(Employee.class);

				 criteria2.add(Restrictions.between("salary",start_sal,end_sal));
				 List<Employee> employees_range = criteria2.list();
				
				 System.out.println("-------List of Employees from a range of Salary------");
				 for (Employee e: employees_range)
				 {                                        
					System.out.println("Employee ID: "+e.getId());
					System.out.println("Employee First Name: "+e.getFirstName());
					System.out.println("Employee Last Name: "+e.getLastName());
					System.out.println("Employee Age: "+e.getAge());
					System.out.println("Employee Post: "+e.getpost());
					System.out.println("Employee Salary: "+e.getSalary());
					System.out.println("*************************");
				}	
				break;
				
			case 7:
				System.out.println("Program Exited");
				break;
				
			default:
				break;
			

				
			
			}
			session.close();
		} 
	}
}












