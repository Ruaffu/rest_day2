package dtos;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {
    private long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
    }

    public static List<EmployeeDTO> getDtos(List<Employee> emps){
        List<EmployeeDTO> empdtos = new ArrayList();
        emps.forEach(emp->empdtos.add(new EmployeeDTO(emp)));
        return empdtos;
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

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
