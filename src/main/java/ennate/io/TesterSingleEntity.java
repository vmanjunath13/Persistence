package ennate.io;

import ennate.io.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TesterSingleEntity {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        Insert query
        Employee employee = new Employee();
        employee.setEmail("vaishnavim1913@gmail.com");
        employee.setFirstName("Vaish");
        employee.setLastName("Manjunath");

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

//        Find by ID logic
        Employee emp = entityManager.find(Employee.class, "2a0af2e8-356f-4bcb-b6cd-1733bf817397");
        System.out.println(emp);

//        Update by ID logic
        Employee emp1 = entityManager.find(Employee.class, "2a0af2e8-356f-4bcb-b6cd-1733bf817397");
        emp.setFirstName("Ajith");
        emp.setLastName("HG");
        entityManager.getTransaction().begin();
        entityManager.merge(emp1);
        entityManager.getTransaction().commit();

//        Delete by ID
        Employee emp2 = entityManager.find(Employee.class, "2a0af2e8-356f-4bcb-b6cd-1733bf817397");
        entityManager.getTransaction().begin();
        entityManager.remove(emp2);
        entityManager.getTransaction().commit();

//        Find all
        TypedQuery<Employee> query = entityManager.createQuery("SELECT emp FROM Employee emp ORDER BY emp.email DESC", Employee.class);
        TypedQuery<Employee> query1 = entityManager.createNamedQuery("Employee.findAll", Employee.class);
        List<Employee> resultList = query.getResultList();
        resultList.forEach(result -> System.out.println(result));


        entityManager.close();
        entityManagerFactory.close();
    }
}
