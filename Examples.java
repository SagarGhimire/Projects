/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

/**
 *
 * @author S525189
 */
import java.util.Scanner;

public class Examples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int num;

        do {
            System.out.println("Enter your first name");
            String fun = input.nextLine();
            System.out.println("Enter the last name");
            String lastname = input.nextLine();
            fun = fun.replaceAll("[0-9]", " ");
            fun = fun.replaceAll("//S", " ");
            fun = fun.replaceAll("//W", " ");
            lastname = lastname.replaceAll("//S", " ");
            lastname = lastname.replaceAll("[0-9]", " ");
            lastname = lastname.replaceAll("//W", " ");
            System.out.println("Enter the report name");
            String report = input.nextLine();
            
            System.out.println(fun + lastname);
            System.out.println(report);
            
            System.out.println("Do u want to exit(-1)");
            num = input.nextInt();

        } while (num != -1);
    }
}
