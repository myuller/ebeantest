package main;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.ebean.Ebean;
import models.Employee;

import java.io.StringWriter;
import java.util.List;

/**
 * Generate the DB Migration.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        List<Employee> employees = Ebean.createQuery(Employee.class)
                .fetch("department")
                .fetch("department.offices")
                .findList();

        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, employees);
        System.out.println(writer.toString());

        if(employees.size() != 4) throw new IllegalStateException("employees.size() != 4");
        if (employees.get(0).getDepartment().getOffices().size() != 4) // BUG HERE
            throw new IllegalStateException("employees.get(0).getDepartment().getOffices().size() != 4");
    }
}
