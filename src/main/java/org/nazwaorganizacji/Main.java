package org.nazwaorganizacji;

import org.nazwaorganizacji.repository.InMemoryClientRepository;
import org.nazwaorganizacji.service.BankService;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private BankService bankService;

    public static void main(String[] args) {
        new Main().run();


    }

    public void run() {
        InMemoryClientRepository repository = new InMemoryClientRepository(new HashSet<>());
        bankService = new BankService(repository);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1 - add user");
                System.out.println("2 - find user");
                System.out.println("3 - exit app");
                String next = scanner.next();
                if (next.equals("1")) {
                    addUser(scanner);
                }
                if (next.equals("2")) {
                    printUser(scanner);
                }
                if (next.equals("3")) {
                    break;
                }


            }
        }


    }

    private void addUser(Scanner scanner) {
        System.out.println("Enter name: ");
        String name = scanner.next();
        System.out.println("Enter email: ");
        String email = scanner.next();
        System.out.println("Enter balance: ");
        double balance = scanner.nextDouble();
        bankService.save(new Client(name, email, balance));
    }

    private void printUser(Scanner scanner) {
        System.out.println("Enter email: ");
        String mail = scanner.next();
        System.out.println(bankService.findByEmail(mail));

    }

}