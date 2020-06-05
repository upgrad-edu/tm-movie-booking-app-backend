package com.upgrad.client;

import com.upgrad.DAOImpl.CustomerDAOImpl;
import com.upgrad.beans.Customer;
import com.upgrad.utiity.PhoneNumber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) throws ParseException {
            boolean flag = true;
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            System.out.println("Kindly chose your option ");
            while(flag)
            {
                System.out.println("Please chose an option: ");
                System.out.println("1. Create a new customer ");
                System.out.println("2. Get customer details");
                System.out.println("3. Delete a customer");
                System.out.println("4. Get all customers");
                System.out.println("5. Exit");
                Scanner sc = new Scanner(System.in);
                int switchKey = sc.nextInt();
                switch(switchKey)
                {
                    case 1 : System.out.println("**CREATING NEW CUSTOMER**");
                        Customer newCustomer = new Customer();
                        System.out.println("Please enter customer first name");
                        String firstName = sc.nextLine();
                        newCustomer.setFirstName(firstName);
                        System.out.println("Please enter customer last name");
                        String lastName = sc.nextLine();
                        newCustomer.setLastName(lastName);
                        System.out.println("Please enter username");
                        String username = sc.nextLine();
                        newCustomer.setUsername(username);
                        System.out.println("Please enter password");
                        String password = sc.nextLine();
                        newCustomer.setPassword(password);
                        System.out.println("Please enter date of birth in dd/MM/yyyy format");
                        String dateString = sc.nextLine();
                        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date futureDate = myFormat.parse(dateString);
                        newCustomer.setDateOfBirth(futureDate);
                        System.out.println("Please select user type : 1. Admin 2.Customer");
                        int userType = sc.nextInt();
                        newCustomer.setUserTypeId(userType);
                        System.out.println("Please enter customer phone number");
                        String phoneNumberString = sc.nextLine();
                        newCustomer.setPhoneNumber(new PhoneNumber(phoneNumberString));
                        Customer customer = customerDAO.createNewCustomer(newCustomer);
                        System.out.println("Customer created : KINDLY NOTE YOUR CREDENTIALS");
                        System.out.println("Customer name : " + customer.getFirstName() + " " + customer.getLastName() );
                        System.out.println("Customer username : " + customer.getUsername());
                        System.out.println("Customer password : " + customer.getPassword());
                        break;

                    case 2 : System.out.println("**GET CUSTOMER DETAIL**");
                        System.out.println("Enter customer id");
                        int customerId = sc.nextInt();
                        Customer customer1 = customerDAO.getCustomer(customerId);
                        if(customer1 == null) {
                            System.out.println("Customer details not found");
                            break;
                        }
                        customer1.toString();
                        break;

                    case 3 : System.out.println("**DELETE CUSTOMER DETAILS**");
                        System.out.println("Enter customer id");
                        int customerId1 = sc.nextInt();
                        boolean deletedCustomer = customerDAO.deleteCustomer(customerId1);
                        if(deletedCustomer)
                            System.out.println("Customer details deleted");
                        else
                            System.out.println("Customer details not deleted");
                        break;

                    case 4 : System.out.println("**GET ALL CUSTOMERS**");
                        System.out.println("Getting all customer details");
                        List<Customer> allCustomers = customerDAO.getAllCustomer();
                        System.out.println("Customers " + "\n" + allCustomers);
                        break;

                    case 5 : flag = false;
                        break;

                    default: System.out.println("Invalid option");
                        break;
                }
            }
        }
}
