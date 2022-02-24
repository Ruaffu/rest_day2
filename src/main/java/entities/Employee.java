package entities;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.deleteAllRows", query = "DELETE from Employee "),
        @NamedQuery(name = "Employee.getAll", query = "select e from Employee e"),
        @NamedQuery(name = "Movie.getByName", query = "select e from Employee e where e.name =:name")})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private long salary;

    public Employee() {
    }

    public Employee(String name, String address, long salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
