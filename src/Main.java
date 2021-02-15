import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        //

        String pathToAudiFile = "E://IdeaProjects/CarDealer/Cars/listCarsAudi.txt";
        String pathToBMWFile = "E://IdeaProjects/CarDealer/Cars/listCarsBmw.txt";
        String pathToVolkswagenFile = "E://IdeaProjects/CarDealer/Cars/listCarsVolkswagen.txt";

        System.out.println("\t\t***** Добро пожаловать! *****");

        int option = 0;

        Out out = new Out();
        ArrayList<Cars> listAudi = out.getCarsList(pathToAudiFile);
        ArrayList<Cars> listBmw = out.getCarsList(pathToBMWFile);
        ArrayList<Cars> listVolkswagen = out.getCarsList(pathToVolkswagenFile);
        SortModel sortModel = new SortModel();
        SortColor sortColor = new SortColor();
        SortYear sortYear = new SortYear();
        SortPrice sortPrice = new SortPrice();

        while (option != 3) {
            System.out.println("1. Вход\n" + "2. Регистрация\n" + "3. Выход\n");
            int numberMenu;
            Scanner in = new Scanner(System.in);
            numberMenu = in.nextInt();
            switch (numberMenu) {

                case 1:
                    System.out.println("Вход в систему... \n");
                    Users user = new Users();
                    String userType = user.login();

                    if (userType.equals("admin")) {
                        Cars cars = new Cars();
                        System.out.println("Вы вошли как администратор. \nВыберите раздел: \n"
                                + "1. Список автомобилей\n"//вывод с индексом, вроде выводило же
                                + "2. Добавить автомобиль\n"
                                + "3. Удалить автомобиль\n"//
                                + "4. Список забронированных автомобилей\n"//бронирование
                                + "5. Список зарегестрированных пользователей\n");//удалить пользователя/дать права админа
                        int numberAdm = in.nextInt();
                        switch (numberAdm) {
                            case 1:
                                System.out.println("Выберите автосалон: \n"
                                        + "1. Audi\n"
                                        + "2. BMW\n"
                                        + "3. Volkswagen\n");
                                int numberSalon = in.nextInt();
                                switch (numberSalon) {
                                    case 1:
                                        System.out.println("*** Автосалон Audi ***");
                                        out.printAllCars(listAudi);
                                        break;
                                    case 2:
                                        System.out.println("*** Автосалон BMW ***");
                                        out.printAllCars(listBmw);
                                        break;
                                    case 3:
                                        System.out.println("*** Автосалон Volkswagen ***");
                                        out.printAllCars(listVolkswagen);
                                        break;
                                    case 4:
                                        //Назад в главное меню
                                        break;
                                    default:
                                        System.out.println("Введите верное значение.");
                                }
                                break;
                            case 2: //добавление авто
                                System.out.println("Выберите автосалон: \n"
                                        + "1. Audi\n"
                                        + "2. BMW\n"
                                        + "3. Volkswagen\n");
                                numberSalon = in.nextInt();
                                switch (numberSalon) {
                                    case 1:
                                        System.out.println("*** Автосалон Audi ***");
                                        cars.addCars(pathToAudiFile, out.getCarsList(pathToAudiFile).size());
                                        break;
                                    case 2:
                                        System.out.println("*** Автосалон BMW ***");
                                        cars.addCars(pathToBMWFile, out.getCarsList(pathToBMWFile).size());
                                        break;
                                    case 3:
                                        System.out.println("*** Автосалон Volkswagen ***");
                                        cars.addCars(pathToVolkswagenFile, out.getCarsList(pathToVolkswagenFile).size());
                                        break;
                                    case 4:
                                        //Назад в главное меню
                                        break;
                                    default:
                                        System.out.println("Введите верное значение.");
                                }
                                break;
                            case 3: //удаление авто
                                System.out.println("Выберите автосалон: \n"
                                        + "1. Audi\n"
                                        + "2. BMW\n"
                                        + "3. Volkswagen\n");
                                int numberSalonDel = in.nextInt();
                                switch (numberSalonDel) {
                                    case 1:
                                        System.out.println("*** Автосалон Audi ***");
                                        out.printAllCars(listAudi);
                                        //
                                        break;
                                    case 2:
                                        System.out.println("*** Автосалон BMW ***");
                                        out.printAllCars(listBmw);
                                        //
                                        break;
                                    case 3:
                                        System.out.println("*** Автосалон Volkswagen ***");
                                        out.printAllCars(listVolkswagen);
                                        //
                                        break;
                                    case 4:
                                        //Назад в главное меню
                                        break;
                                    default:
                                        System.out.println("Введите верное значение.");
                                }
                                break;
                            case 4:
                                //logic
                                break;
                            case 5:
                                //logic
                                break;
                            default:
                                System.out.println("Введите верное значение.");
                        }


                    } else if (userType.equals("user")) {
                        System.out.println("Вы вошли как пользователь. Выберите автосалон: \n"
                                + "1. Audi\n"
                                + "2. BMW\n"
                                + "3. Volkswagen\n");
                        int numberSalon = in.nextInt();
                        switch (numberSalon) {
                            case 1:
                                System.out.println("*** Автосалон Audi ***");
                                out.printAllCars(listAudi);
                                System.out.println("Упорядочить по:\n1. Модели.\n2. Цвету.\n3. Году выпуска.\n4. Цене.\n5. Выход к автосалонам");
                                int numberSort = in.nextInt();
                                switch (numberSort) {
                                    case 1:
                                        System.out.println("Сортировка по модели");
                                        listAudi.sort(sortModel);
                                        out.printAllCars(listAudi);
                                        break;
                                    case 2:
                                        System.out.println("Сортировка по цвету");
                                        listAudi.sort(sortColor);
                                        out.printAllCars(listAudi);
                                        break;
                                    case 3:
                                        System.out.println("Сортировка по году выпуска");
                                        listAudi.sort(sortYear);
                                        out.printAllCars(listAudi);
                                        break;
                                    case 4:
                                        System.out.println("Сортировка по цене");
                                        listAudi.sort(sortPrice);
                                        out.printAllCars(listAudi);
                                        break;
                                    case 5:
                                        break;
                                        //выход к салонам
                                    default:
                                        System.out.println("Введите правильное значение");
                                }
                                break;
                            case 2:
                                System.out.println("*** Автосалон BMW ***");
                                break;
                            case 3:
                                System.out.println("*** Автосалон Volkswagen ***");
                                break;
                            case 4:
                                //Назад в главное меню
                                break;
                            default:
                                System.out.println("Введите верное значение.");
                        }
                    } else {
                        System.out.println("Неверный логин или пароль. Попробуйте снова.");
                        break;
                    }
                    break;
                case 2:
                    System.out.println("Регистрация.");
                    Users userRegistr = new Users();
                    userRegistr.registration();
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

    public void adminMenu(){

    }
}
