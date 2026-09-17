package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // =========================
        // CREATE LAPTOPS
        // =========================

        Laptop laptop = new Laptop();
        laptop.setLaptopId(17);
        laptop.setModel("MacBook Pro M2");
        laptop.setBrand("Apple");
        laptop.setRam(8);

        Laptop laptop2 = new Laptop();
        laptop2.setLaptopId(18);
        laptop2.setModel("Victus 15 Radeon 5600H RX6500M");
        laptop2.setBrand("HP");
        laptop2.setRam(12);

        Laptop laptop3 = new Laptop();
        laptop3.setLaptopId(19);
        laptop3.setModel("Omen 16 RTX4050 Radeon6500H");
        laptop3.setBrand("HP");
        laptop3.setRam(16);


        // =========================
        // CREATE EMPLOYEE
        // =========================

        Employee employee = new Employee();

        employee.setFirstName("Aditya Vikram");
        employee.setLastName("Kirtania");
        employee.setEmail("AdityaVikramKirtania1792@gmail.com");
        employee.setSalary(55.9);
        employee.setId(143);

        employee.setLaptops(
                Arrays.asList(laptop, laptop2, laptop3)
        );


        // =========================
        // HIBERNATE CONFIG
        // =========================

        Configuration config = new Configuration();

        config.addAnnotatedClass(Employee.class);
        config.addAnnotatedClass(Laptop.class);

        config.configure();

        SessionFactory sf = config.buildSessionFactory();

        Session session = sf.openSession();

        Transaction transaction = session.beginTransaction();


        // =========================
        // CREATE
        // =========================

        session.persist(laptop);
        session.persist(laptop2);
        session.persist(laptop3);

        session.persist(employee);


        // =========================
        // READ
        // =========================

        Employee findEmployee =
                session.get(Employee.class, 143);

        System.out.println(findEmployee);


        // =========================
        // COMMIT
        // =========================

        transaction.commit();

        session.close();
        sf.close();
    }
}