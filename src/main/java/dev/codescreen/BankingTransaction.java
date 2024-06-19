package dev.codescreen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingTransaction {
    public static void main(String[] args) throws IOException {
        File myObj = new File("src/main/resources/transaction_log.txt");
        if(myObj.exists()){
            //myObj.delete();
            FileWriter myWriter = new FileWriter("src/main/resources/transaction_log.txt", false);
            myWriter.close();
        }
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(BankingTransaction.class, args);
    }
}
