package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import facades.FacadeExample;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("employee")
public class EmployeeResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final EmployeeFacade FACADE =  EmployeeFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(){
        List<Employee> employees = FACADE.getAllEmployees();
        List<EmployeeDTO> employeeDTOS = EmployeeDTO.getDtos(employees);
        return Response.ok().entity(GSON.toJson(employeeDTOS)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id")long id){
        Employee employee = FACADE.getEmployeeById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO(employee);
        return Response.ok().entity(GSON.toJson(employeeDTO)).build();

    }

    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHighestPaidEmployee(){
        Employee employee = FACADE.getEmployeesWithHighestSalary();
        EmployeeDTO employeeDTO = new EmployeeDTO(employee);
        return Response.ok().entity(GSON.toJson(employeeDTO)).build();

    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByName(@PathParam("name")String name){
        Employee employee = FACADE.getEmployeeByName(name);
        EmployeeDTO employeeDTO = new EmployeeDTO(employee);
        return Response.ok().entity(GSON.toJson(employeeDTO)).build();

    }
}