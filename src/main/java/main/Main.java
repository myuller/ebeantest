package main;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.dbplatform.DbPlatformName;
import com.avaje.ebean.dbmigration.DbMigration;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Employee;

import java.io.StringWriter;
import java.util.List;

/**
 * Generate the DB Migration.
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        DbMigration dbMigration = new DbMigration();
//        dbMigration.setPlatform(DbPlatformName.POSTGRES);
//        dbMigration.generateMigration();

        List<Employee> employees = Ebean.createQuery(Employee.class)
                .fetch("department")
                .fetch("department.offices")
                .findList();

        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, employees);
        System.out.println(writer.toString());
    }
}
