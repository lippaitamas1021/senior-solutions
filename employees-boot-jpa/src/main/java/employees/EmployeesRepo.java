package employees;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeesRepo {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveEmployee(Employee employee) {
        em.persist(employee);
    }

    public List<Employee> listEmployees() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }
}
