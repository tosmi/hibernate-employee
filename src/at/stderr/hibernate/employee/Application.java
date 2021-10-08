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
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();

        List<Employee> employeeList=  session.createQuery("from Employee").getResultList();

        for(Employee e : employeeList) {
            System.out.println(e);
        }
        factory.close();
    }
}
