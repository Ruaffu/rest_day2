package facades;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class EmployeeFacade {
    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public Employee getEmployeeById(long id){
        EntityManager em = getEntityManager();

        try {

            return em.find(Employee.class, id);

        }finally {
            em.close();
        }
    }

    public Employee getEmployeeByName(String name){
        EntityManager em = getEntityManager();
        TypedQuery<Employee> query = em.createNamedQuery("Employee.getByName", Employee.class);
        query.setParameter("name", name);
        Employee emp = query.getSingleResult();
        return emp;

    }

    public List<Employee> getAllEmployees(){
        EntityManager em = getEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();

        return employees;

    }

    public Employee getEmployeesWithHighestSalary(){
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> tq = em.createNamedQuery("Employee.getHighest", Employee.class);
            tq.setMaxResults(1);
            return tq.getSingleResult();
        }finally {
            em.close();
        }

    }

    public void createEmployee(Employee employee){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        }finally {
            em.close();
        }

    }
}
