/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade fe = EmployeeFacade.getFacadeExample(emf);
        fe.createEmployee(new Employee("Larry","larry street", 1000));
        fe.createEmployee(new Employee("Curly","curly street", 2000));
        fe.createEmployee(new Employee("Mo","mo street", 3000));
    }
    
    public static void main(String[] args) {
        populate();
    }
}
