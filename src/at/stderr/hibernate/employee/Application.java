package at.stderr.hibernate.employee;

import at.stderr.hibernate.employee.entity.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        Employee employee = new Employee("Toni", "Schmidbauer", "Red Hat");
        System.out.println("Employee ID: " + employee.getId());
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        System.out.println("Employee ID: " + employee.getId());
        System.out.println("Employee: " + employee.hashCode());

        session = factory.getCurrentSession();
        session.beginTransaction();
        Employee byIdEmployee = session.get(Employee.class, employee.getId());
        System.out.println("Employee ID: " + byIdEmployee.getId());
        System.out.println("Employee: " + byIdEmployee.hashCode());
        session.getTransaction().commit();

        session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(byIdEmployee);
        System.out.println("Session contains employee: " + session.contains(byIdEmployee));
        session.getTransaction().commit();

        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Employee> employeeList=  session.createQuery("from Employee").getResultList();
        session.getTransaction().commit();

        for(Employee e : employeeList) {
            System.out.println(e);
        }
        factory.close();
    }
}
