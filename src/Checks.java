import java.util.ArrayList;
import java.util.Scanner;

public class Checks {
    public static int checkCarsIndexRange(Scanner in, ArrayList<Cars> carsList){
        int indexDelete;
        do {
            System.out.println("Введите индекс удаляемого автомобиля:");
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

    public static void checkIntegerInput(Scanner in){ //содать такую же на булеан
        while (!in.hasNextInt()) {
            System.out.println("Неверный формат ввода!");
            in.next();
        }
    }
}
