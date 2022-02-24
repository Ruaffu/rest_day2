package facades;


import entities.Employee;
import entities.RenameMe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFacadeTest {
    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;
    Employee emp1, emp2;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = EmployeeFacade.getFacadeExample(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            emp1 = new Employee("John Doe", "1 new Street", 120000);
            emp2 = new Employee("Jane Doe", "2 new Street", 130000);
            em.persist(emp1);
            em.persist(emp2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getEmployeeById() {
        String expected = emp1.getName();
        String actual = facade.getEmployeeById(emp1.getId()).getName();
        assertEquals(expected, actual);

    }

    @Test
    void getEmployeeByName() {
        String expected = emp1.getName();
        String actual = facade.getEmployeeByName(emp1.getName()).getName();
        assertEquals(expected, actual);
    }

    @Test
    void getAllEmployees() {
        int expected = 2;
        int actual = facade.getAllEmployees().size();
        assertEquals(expected, actual);
    }

    @Test
    void getEmployeesWithHighestSalary() {
        String expected = emp2.getName();
        String actual = facade.getEmployeesWithHighestSalary().getName();
        assertEquals(expected, actual);
    }

    @Test
    void createEmployee() {
        Employee emp3 = new Employee("Sean don", "3 Backstreet", 110000);
        facade.createEmployee(emp3);
        String expected = emp3.getName();
        String actual = facade.getEmployeeById(emp3.getId()).getName();
        assertEquals(expected, actual);
    }
}