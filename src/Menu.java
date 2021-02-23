import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void menu() throws IOException {
        //MenuFunctions menuFunctions = new MenuFunctions();
        OutCars outCars = new OutCars();
        OutUsers outUsers = new OutUsers();

        ArrayList<Cars> listAudi = outCars.getCarsList(MenuFunctions.pathToAudiFile);
        ArrayList<Cars> listBmw = outCars.getCarsList(MenuFunctions.pathToBMWFile);
        ArrayList<Cars> listVolkswagen = outCars.getCarsList(MenuFunctions.pathToVolkswagenFile);

        int option = 0;

        System.out.println("\t\t***** Добро пожаловать! *****");

        while (option != 3) {
            System.out.println("""
                    1. Вход
                    2. Регистрация
                    3. Выход
                    """);
            int numberMenu;
            Scanner in = new Scanner(System.in);
            Checks.checkIntegerInput(in);
            numberMenu = in.nextInt();

            switch (numberMenu) {
                case 1:
                    System.out.println("Вход в систему... \n");
                    Users user = new Users();
                    String userType = user.login();

                    if (userType.equals("admin")) {
                        MenuFunctions.adminMenu(outCars, outUsers, listAudi, listBmw, listVolkswagen);
                    } else if (userType.equals("user")) {
                        MenuFunctions.userMenu(outCars, listAudi, listBmw, listVolkswagen);
                    } else {
                        System.out.println("Неверный логин или пароль. Попробуйте снова.");
                        break;
                    }
                    break;
                case 2:
                    System.out.println("Регистрация...");
                    Users userRegister = new Users();
                    userRegister.registration(outUsers.getUserList(MenuFunctions.pathToUserFile).size());
                    break;
                case 3:
                    System.out.println("Выход из приложения.");
                    option = numberMenu;
                    break;
                default:
                    System.out.println("Введено не верное значение.");
            }
        }
    }
}
