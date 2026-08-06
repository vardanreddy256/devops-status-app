package com.devops;

public class App {

    public static void main(String[] args) {

        String applicationName = "DevOps Status Application";
        String version = "1.0";
        String environment = "Development";
        String status = "Running Successfully";

        System.out.println("================================");
        System.out.println("     DevOps Status Application");
        System.out.println("================================");
        System.out.println("Application : " + applicationName);
        System.out.println("Version     : " + version);
        System.out.println("Environment : " + environment);
        System.out.println("Status      : " + status);
        System.out.println("     These Changes Are From Local");
        System.out.println("================================");
    }
}
