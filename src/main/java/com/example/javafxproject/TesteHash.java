package com.example.javafxproject;

import org.mindrot.jbcrypt.BCrypt;

public class TesteHash { 
    public static void main (String[] args) {
        String password = "senha123";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        System.out.println("Hash da senha: " + hashedPassword);

        String inputPassword = "senha123";
        if (BCrypt.checkpw(inputPassword, hashedPassword)) {
            System.out.println("Senha válida!");
        } else {
            System.out.println("Senha inválida!");
        }
    }
}