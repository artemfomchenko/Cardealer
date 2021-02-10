import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class Cars {

    private int index;
    private String brand;
    private String model;
    private String color;
    private int year;
    private int price;

    @Override
    public String toString() {
        return "Марка: " + brand + "\n"
                + "Модель: " + model + "\n"
                + "Цвет: " + color + "\n"
                + "Год выпуска: " + year + "\n"
                + "Стоимость: " + price + "\n";
    }

    public String toStringAdmin(int listLenght) {
        return  "Индекс: " + listLenght + "\n"
                + "Марка: " + brand + "\n"
                + "Модель: " + model + "\n"
                + "Цвет: " + color + "\n"
                + "Год выпуска: " + year + "\n"
                + "Стоимость: " + price + "\n";
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }
    //

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void addCars(String pathToFile, int listLenght) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        String pathToCarsList = pathToFile;
        Writer write = new FileWriter(pathToCarsList, true);
        Out out = new Out();

        while (run) {
            System.out.println("Введите марку: ");
            String brand = input.nextLine();
            setBrand(brand);
            System.out.println("Введите модель: ");
            String model = input.nextLine();
            setModel(model);
            System.out.println("Введите цвет: ");
            String color = input.nextLine();
            setColor(color);
            System.out.println("Введите год выпуска: ");
            int year = input.nextInt();
            setYear(year);
            System.out.println("Введите цену: ");
            int price = input.nextInt();
            setPrice(price);

            listLenght++;
            write.write("{\n"+ toStringAdmin(listLenght) + "},\n");
            input.nextLine();//
            System.out.println("Авто добавлено! Добавить еще? (Да/Нет):");

            boolean answerCheck = false;
            while (!answerCheck) {
                String answer = input.nextLine().toLowerCase();
                if (answer.equals("да")) {
                    run = true;
                    answerCheck = true;

                } else if (answer.equals("нет")) {
                    run = false;
                    answerCheck = true;

                } else {
                    System.out.println("Введите правильное значение: Да/Нет");
                    answerCheck = false;
                }
            }
        }
        write.close();
    }
}
