import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuFunctions {
    protected static final String pathToAudiFile = "E://IdeaProjects/CarDealer/Cars/listCarsAudi.txt";
    protected static final String pathToBMWFile = "E://IdeaProjects/CarDealer/Cars/listCarsBmw.txt";
    protected static final String pathToVolkswagenFile = "E://IdeaProjects/CarDealer/Cars/listCarsVolkswagen.txt";
    protected static final String pathToUserFile = "E://IdeaProjects/CarDealer/Users/listOfUsers.txt";

    public static void adminMenu(OutCars outCars, OutUsers outUsers, ArrayList<Cars> listAudi, ArrayList<Cars> listBmw,
                                 ArrayList<Cars> listVolkswagen) throws IOException {
        int option = 0;
        while (option != 6) {
            Cars cars = new Cars();
            System.out.println("""
                    Вы вошли как администратор.\s
                    Выберите раздел:\s
                    1. Список автомобилей
                    2. Добавить автомобиль
                    3. Удалить автомобиль
                    4. Список забронированных автомобилей
                    5. Действия с аккаунтами
                                                    
                    6. Выход
                    """);
            Scanner in = new Scanner(System.in);
            Checks.checkIntegerInput(in);
            int numberAdm = in.nextInt();

            switch (numberAdm) {
                case 1:
                    listAudi = outCars.getCarsList(pathToAudiFile);
                    listBmw = outCars.getCarsList(pathToBMWFile);
                    listVolkswagen = outCars.getCarsList(pathToVolkswagenFile);
                    listCarsAdminMenu(outCars, listAudi, listBmw, listVolkswagen);
                    break;
                case 2: //добавление авто
                    addCarsAdminMenu(outCars, cars);
                    break;
                case 3: //удаление авто
                    removeCarsAdminMenu(outCars, listAudi, listBmw, listVolkswagen);
                    break;
                case 4://Список забронированных авто
                    listReservationCarsAdminMenu(outCars, listAudi, listBmw, listVolkswagen);
                    break;
                case 5://Список зарегестрированнных пользователей
                    listUsersAdminMenu(outUsers);
                    break;
                case 6:
                    option = numberAdm;
                    break;
                default:
                    System.err.println("Введите верное значение.");
            }
        }
    }

    public static void userMenu(OutCars outCars, ArrayList<Cars> listAudi, ArrayList<Cars> listBmw,
                                ArrayList<Cars> listVolkswagen, CurrentUser currentUser) throws IOException {
        SortModel sortModel = new SortModel();
        SortColor sortColor = new SortColor();
        SortYear sortYear = new SortYear();
        SortPrice sortPrice = new SortPrice();

        int option = 0;
        while (option != 4) {
            System.out.println("""
                    Вы вошли как пользователь. Выберите автосалон:\s
                    1. Audi
                    2. BMW
                    3. Volkswagen

                    4. Назад""");
            Scanner in = new Scanner(System.in);
            Checks.checkIntegerInput(in);
            int numberSalon = in.nextInt();
            switch (numberSalon) {
                case 1:
                    System.out.println("*** Автосалон Audi ***");
                    listAudi = outCars.getCarsList(pathToAudiFile);
                    carShowRoomUserMenu(outCars, listAudi, sortModel, sortColor, sortYear,
                            sortPrice, pathToAudiFile, currentUser);
                    break;
                case 2:
                    System.out.println("*** Автосалон BMW ***");
                    listBmw = outCars.getCarsList(pathToBMWFile);
                    carShowRoomUserMenu(outCars, listBmw, sortModel, sortColor, sortYear,
                            sortPrice, pathToBMWFile, currentUser);
                    break;
                case 3:
                    System.out.println("*** Автосалон Volkswagen ***");
                    listVolkswagen = outCars.getCarsList(pathToVolkswagenFile);
                    carShowRoomUserMenu(outCars, listVolkswagen, sortModel, sortColor, sortYear,
                            sortPrice, pathToVolkswagenFile, currentUser);
                    break;
                case 4:
                    option = numberSalon;
                    break;
                default:
                    System.err.println("Введите верное значение.");
            }
        }
    }

    public static void carShowRoomUserMenu(OutCars outCars, ArrayList<Cars> listCars, SortModel sortModel,
                                           SortColor sortColor, SortYear sortYear,
                                           SortPrice sortPrice, String pathToCarFile,
                                           CurrentUser currentUser) throws IOException {
        int option = 0;
        while (option != 7) {
            System.out.println("""
                    1. Показать список авто
                    2. Забронировать авто
                                        
                    Упорядочить по:
                    3. Модели
                    4. Цвету
                    5. Году выпуска
                    6. Цене\s
                                                            
                    7. Назад""");
            Scanner in = new Scanner(System.in);
            Checks.checkIntegerInput(in);
            int numberSortCar = in.nextInt();
            switch (numberSortCar) {
                case 1:
                    outCars.printAllCars(listCars);
                    break;
                case 2:
                    outCars.reservation(listCars, pathToCarFile, currentUser);
                    break;
                case 3:
                    System.out.println("=== Сортировка по модели ===");
                    listCars.sort(sortModel);
                    outCars.printAllCars(listCars);
                    break;
                case 4:
                    System.out.println("=== Сортировка по цвету ===");
                    listCars.sort(sortColor);
                    outCars.printAllCars(listCars);
                    break;
                case 5:
                    System.out.println("=== Сортировка по году выпуска ===");
                    listCars.sort(sortYear);
                    outCars.printAllCars(listCars);
                    break;
                case 6:
                    System.out.println("=== Сортировка по цене ===");
                    listCars.sort(sortPrice);
                    outCars.printAllCars(listCars);
                    break;
                case 7:
                    option = numberSortCar;
                    break;
                default:
                    System.err.println("Введите правильное значение");
            }
        }
    }

    public static void listCarsAdminMenu(OutCars outCars, ArrayList<Cars> listAudi, ArrayList<Cars> listBmw,
                                         ArrayList<Cars> listVolkswagen) {
        int option = 0;
        while (option != 4) {
            System.out.println("""
                    Выберите автосалон:\s
                    1. Audi
                    2. BMW
                    3. Volkswagen

                    4. Назад""");
            Scanner in = new Scanner(System.in);
            Checks.checkIntegerInput(in);
            int numberSalon = in.nextInt();
            switch (numberSalon) {
                case 1:
                    System.out.println("*** Автосалон Audi ***");
                    outCars.printAllCars(listAudi);
                    break;
                case 2:
                    System.out.println("*** Автосалон BMW ***");
                    outCars.printAllCars(listBmw);
                    break;
                case 3:
                    System.out.println("*** Автосалон Volkswagen ***");
                    outCars.printAllCars(listVolkswagen);
                    break;
                case 4:
                    option = numberSalon;
                    break;
                default:
                    System.err.println("Введите верное значение.");
            }
        }
    }

    public static void addCarsAdminMenu(OutCars outCars, Cars cars) throws IOException {
        int option = 0;
        while (option != 4) {
            System.out.println("""
                    Выберите автосалон:\s
                    1. Audi
                    2. BMW
                    3. Volkswagen

                    4. Назад""");
            Scanner in = new Scanner(System.in);
            Checks.checkIntegerInput(in);
            int numberSalon = in.nextInt();
            switch (numberSalon) {
                case 1:
                    System.out.println("*** Автосалон Audi ***");
                    cars.addCars(pathToAudiFile, outCars.getCarsList(pathToAudiFile).size());
                    break;
                case 2:
                    System.out.println("*** Автосалон BMW ***");
                    cars.addCars(pathToBMWFile, outCars.getCarsList(pathToBMWFile).size());
                    break;
                case 3:
                    System.out.println("*** Автосалон Volkswagen ***");
                    cars.addCars(pathToVolkswagenFile, outCars.getCarsList(pathToVolkswagenFile).size());
                    break;
                case 4:
                    option = numberSalon;
                    break;
                default:
                    System.err.println("Введите верное значение.");
            }
        }
    }

    public static void removeCarsAdminMenu(OutCars outCars, ArrayList<Cars> listAudi, ArrayList<Cars> listBmw,
                                           ArrayList<Cars> listVolkswagen) throws IOException {
        int option = 0;
        while (option != 4) {
            System.out.println("""
                    Выберите автосалон:\s
                    1. Audi
                    2. BMW
                    3. Volkswagen

                    4. Назад""");
            Scanner in = new Scanner(System.in);
            Checks.checkIntegerInput(in);
            int numberSalonDel = in.nextInt();
            switch (numberSalonDel) {
                case 1:
                    System.out.println("*** Автосалон Audi ***");
                    listAudi = outCars.getCarsList(pathToAudiFile);
                    outCars.printAllCars(listAudi);
                    outCars.removeCar(listAudi, pathToAudiFile);
                    break;
                case 2:
                    System.out.println("*** Автосалон BMW ***");
                    listBmw = outCars.getCarsList(pathToBMWFile);
                    outCars.printAllCars(listBmw);
                    outCars.removeCar(listBmw, pathToBMWFile);
                    break;
                case 3:
                    System.out.println("*** Автосалон Volkswagen ***");
                    listVolkswagen = outCars.getCarsList(pathToVolkswagenFile);
                    outCars.printAllCars(listVolkswagen);
                    outCars.removeCar(listVolkswagen, pathToVolkswagenFile);
                    break;
                case 4:
                    option = numberSalonDel;
                    break;
                default:
                    System.err.println("Введите верное значение.");
            }
        }
    }

    public static void listReservationCarsAdminMenu(OutCars outCars, ArrayList<Cars> listAudi,
                                                    ArrayList<Cars> listBmw,
                                                    ArrayList<Cars> listVolkswagen) throws IOException {
        int option = 0;
        while (option != 4) {
            System.out.println("""
                    1. Audi
                    2. BMW
                    3. Volkswagen
                                                            
                    4. Назад""");
            Scanner in = new Scanner(System.in);
            Checks.checkIntegerInput(in);
            int numberRes = in.nextInt();
            switch (numberRes) {
                case 1:
                    System.out.println("=== Audi ===");
                    outCars.printReservationCars(listAudi);
                    removeReservationAdminMenu(outCars, listAudi, pathToAudiFile);
                    break;
                case 2:
                    System.out.println("=== BMW ===");
                    outCars.printReservationCars(listBmw);
                    removeReservationAdminMenu(outCars, listBmw, pathToBMWFile);
                    break;
                case 3:
                    System.out.println("=== VW ===");
                    outCars.printReservationCars(listVolkswagen);
                    removeReservationAdminMenu(outCars, listVolkswagen, pathToVolkswagenFile);
                    break;
                case 4:
                    option = numberRes;
                    break;
                default:
                    System.err.println("Введите верное значение.");
            }
        }
    }

    public static void removeReservationAdminMenu(OutCars outCars, ArrayList<Cars> listCars,
                                                  String pathToCarFile) throws IOException {
        System.out.println("""
                                                                
                1. Снять резервацию авто
                2. Назад""");
        Scanner in = new Scanner(System.in);
        Checks.checkIntegerInput(in);
        int numberDelResVw = in.nextInt();
        switch (numberDelResVw) {
            case 1:
                outCars.removeReservation(listCars, pathToCarFile);
                break;
            case 2:
                break;
            default:
                System.err.println("Введите верное значение.");
        }
    }

    public static void listUsersAdminMenu(OutUsers outUsers) throws IOException {
        int option = 0;
        while (option != 6) {
            ArrayList<Users> listUsers = outUsers.getUserList(pathToUserFile);
            Scanner in = new Scanner(System.in);
            //Checks.checkIntegerInput(in); ???????????????????????????????????????????
            while (!in.hasNextInt()) {
                System.out.println("Неверный формат ввода! Вводимое значение должно быть целым числом!");
                in.next();
            }
            System.out.println("""
                    Выберите действие:\s
                    1. Вывести список аккаунтов
                    2. Отображение списка упорядоченного по логину
                    3. Удалить пользователя
                    4. Дать права администратора
                    5. Удалить права администратора
                                                            
                    6. Назад
                    """);
            int numberUser = in.nextInt();
            switch (numberUser) {
                case 1:
                    outUsers.printAllUser(listUsers);
                    break;
                case 2:
                    SortLogin sortLogin = new SortLogin();
                    listUsers.sort(sortLogin);
                    System.out.println("=== Отсортированный список ===");
                    outUsers.printAllUser(listUsers);
                    break;
                case 3:
                    outUsers.printAllUser(listUsers);
                    outUsers.removeUser(listUsers, pathToUserFile);
                    System.out.println("=== Список обновлён ===");
                    listUsers = outUsers.getUserList(pathToUserFile);
                    outUsers.printAllUser(listUsers);
                    break;
                case 4:
                    outUsers.printAllUser(listUsers);
                    outUsers.makeAdmin(listUsers, pathToUserFile);
                    System.out.println("=== Список обновлён ===");
                    listUsers = outUsers.getUserList(pathToUserFile);
                    outUsers.printAllUser(listUsers);
                    break;
                case 5:
                    outUsers.printAllUser(listUsers);
                    outUsers.removeAdmin(listUsers, pathToUserFile);
                    System.out.println("=== Список обновлён ===");
                    listUsers = outUsers.getUserList(pathToUserFile);
                    outUsers.printAllUser(listUsers);
                    break;
                case 6:
                    option = numberUser;
                    break;
                default:
                    System.err.println("Введите верное значение.");
            }
        }
    }
}
