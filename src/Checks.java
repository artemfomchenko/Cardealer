import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Checks {
    public static int checkCarsIndexRange(Scanner in, ArrayList<Cars> carsList){
        int indexDelete;
        do {
            System.out.println("Введите индекс автомобиля:");
            checkIntegerInput(in);
            indexDelete = in.nextInt();
            if (indexDelete <= 0 || indexDelete > carsList.size()){
                System.out.println("Такого индекса не существует!");
            }
        } while (indexDelete <= 0 || indexDelete > carsList.size());
        return indexDelete;
    }

    public static int checkUsersIndexRange(Scanner in, ArrayList<Users> userList){
        int indexDelete;
        do {
            System.out.println("Введите индекс удаляемого аккаунта:");
            checkIntegerInput(in);
            indexDelete = in.nextInt();
            if (indexDelete <= 0 || indexDelete > userList.size()){
                System.out.println("Такого индекса не существует!");
            }
        } while (indexDelete <= 0 || indexDelete > userList.size());
        return indexDelete;
    }

    public static int checkCarsYears(Scanner input){
        int year;
        do {
            System.out.println("Введите год выпуска: ");
            checkIntegerInput(input);
            year = input.nextInt();
            if (year > 3000 || year < 1800 ){
                System.out.println("Введён не корректный год выпуска (1800 - 3000)");
            }
        } while (year > 3000 || year < 1800);
        return year;
    }

    public static int checkCarsPrice(Scanner input){
        int price;
        do {
            System.out.println("Введите цену: ");
            checkIntegerInput(input);
            price = input.nextInt();
            if (price > 2000000000 || price <= 0){
                System.out.println("Введена не корректная стоимость (0 - 2000000000)");
            }
        } while (price > 2000000000 || price <= 0);
        return price;
    }

    public static String checkUsersLogin(Scanner input){
        Users users = new Users();
        String userName;
        do {
            System.out.println("Введите имя пользователя: ");
            userName = input.nextLine();
            if (userName.length() > 20 || userName.length() < 3){
                System.out.println("Имя пользователя должно состоять из не менее 3 и не более 20 символов");
            }
            try {
                String pathToUserList = "E://IdeaProjects/CarDealer/Users/listOfUsers.txt";
                BufferedReader read = new BufferedReader(new FileReader(pathToUserList));
                String line;
                while ((line = read.readLine()) != null) {
                    if (line.equals("{")) {
                        int dbIndex = Integer.parseInt(read.readLine().replaceAll("Индекс: ", "").trim());
                        String dbUser = read.readLine().replaceAll("Логин: ", "").trim();
                        if (userName.equals(dbUser)) {
                            System.out.println("Такое имя пользователя уже существует.");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (userName.length() > 20 || userName.length() < 3);
        return userName;
    }

    public static String checkUsersPassword(Scanner input){
        String password;
        do {
            System.out.println("Введите пароль: ");
            password = input.nextLine();
            if (password.length() > 20 || password.length() < 6){
                System.out.println("Пароль должен состоять из не менее 6 и не более 20 символов");
            }
        } while (password.length() > 20 || password.length() < 3);
        return password;
    }

    public static String checkUsersPhoneNumber(Scanner input){
        String phoneNumber;
        do {
            System.out.println("Введите свой номер телефона: ");
            phoneNumber = input.nextLine();
            if (phoneNumber.length() > 15 || phoneNumber.length() < 6){
                System.out.println("Номер телефона должен состоять из не менее 6 и не более 15 символов");
            }
        } while (phoneNumber.length() > 15 || phoneNumber.length() < 6);
        return phoneNumber;
    }

    public static void checkIntegerInput(Scanner in){
        while (!in.hasNextInt()) {
            System.out.println("Неверный формат ввода! Вводимое значение должно быть целым числом!");
            in.next();
        }
    }
}