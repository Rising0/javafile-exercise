package application;

import model.entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Application {

    public static void main(String[] args) {

        String folder = "/home/pc/IdeaProjects/Course/dir-exercise/summary.csv";
        Product product;
        List<Product> products = new ArrayList<>();
        Locale.setDefault(Locale.US);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(folder))) {

            for (int i = 0; i < 4; i++) {
                String[] line = bufferedReader.readLine().split(",");
                product = new Product(line[0], Double.parseDouble(line[1]), Integer.parseInt(line[2]));
                products.add(product);
            }
        } catch (IOException exception) {
            System.out.println("Error: " + exception.getMessage());
        }

        boolean success = new File("/home/pc/IdeaProjects/Course/dir-exercise/out").mkdir();

        System.out.println("Directory created: " + success);

        String filePath = "/home/pc/IdeaProjects/Course/dir-exercise/out/summary.csv";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Product value : products) {
                bufferedWriter.write(value.getName() + "," + String.format("%.2f", value.getPrice() * value.getQuantity()));
                bufferedWriter.newLine();
            }
        } catch (IOException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }
}