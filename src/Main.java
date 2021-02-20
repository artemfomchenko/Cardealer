import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String pathToAudiFile = "E://IdeaProjects/CarDealer/Cars/listCarsAudi.txt";
    private static final String pathToBMWFile = "E://IdeaProjects/CarDealer/Cars/listCarsBmw.txt";
    private static final String pathToVolkswagenFile = "E://IdeaProjects/CarDealer/Cars/listCarsVolkswagen.txt";
    private static final String pathToUserFile = "E://IdeaProjects/CarDealer/Users/listOfUsers.txt";

    public static void main(String[] args) throws IOException {



        OutCars outCars = new OutCars();
        ArrayList<Cars> listAudi = outCars.getCarsList(pathToAudiFile);
        ArrayList<Cars> listBmw = outCars.getCarsList(pathToBMWFile);
        ArrayList<Cars> listVolkswagen = outCars.getCarsList(pathToVolkswagenFile);
        OutUsers outUsers = new OutUsers();

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
            numberMenu = in.nextInt();
            switch (numberMenu) {
                case 1:
                    System.out.println("Вход в систему... \n");
                    Users user = new Users();
                    String userType = user.login();

                    if (userType.equals("admin")) {
                        adminMenu(outCars, outUsers, listAudi, listBmw, listVolkswagen);
                        } else if (userType.equals("user")) {
                        userMenu(outCars, listAudi, listBmw, listVolkswagen);
                        } else {
                        System.out.println("Неверный логин или пароль. Попробуйте снова.");
                        break;
                    }
                    break;
                case 2:
                    System.out.println("Регистрация...");
                    Users userRegister = new Users();
                    userRegister.registration(outUsers.getUserList(pathToUserFile).size());
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
                    5. Список зарегестрированных пользователей
                                                    
                    6. Выход
                    """);
            Scanner in = new Scanner(System.in);
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
                    System.out.println("Введите верное значение.");
            }
        }
    }

    public static void userMenu(OutCars outCars, ArrayList<Cars> listAudi, ArrayList<Cars> listBmw,
                                ArrayList<Cars> listVolkswagen) throws IOException {
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
            int numberSalon = in.nextInt();
            switch (numberSalon) {
                case 1:
                    System.out.println("*** Автосалон Audi ***");
                    listAudi = outCars.getCarsList(pathToAudiFile);
                    carShowRoomUserMenu(outCars, listAudi, sortModel, sortColor, sortYear, sortPrice, pathToAudiFile);
                    break;
                case 2:
                    System.out.println("*** Автосалон BMW ***");
                    listBmw = outCars.getCarsList(pathToBMWFile);
                    carShowRoomUserMenu(outCars, listBmw, sortModel, sortColor, sortYear, sortPrice, pathToBMWFile);
                    break;
                case 3:
                    System.out.println("*** Автосалон Volkswagen ***");
                    listVolkswagen = outCars.getCarsList(pathToVolkswagenFile);
                    carShowRoomUserMenu(outCars, listVolkswagen, sortModel, sortColor, sortYear, sortPrice, pathToVolkswagenFile);
                    break;
                case 4:
                    option = numberSalon;
                    break;
                default:
                    System.out.println("Введите верное значение.");
            }
        }

    }

    public static void carShowRoomUserMenu(OutCars outCars, ArrayList<Cars> listCars, SortModel sortModel,
                                           SortColor sortColor, SortYear sortYear,
                                           SortPrice sortPrice, String pathToCarFile) throws IOException {
        int option = 0;
        while(option != 6) {
            outCars.printAllCars(listCars);
            System.out.println("""
                    Упорядочить по:
                    1. Модели.
                    2. Цвету.
                    3. Году выпуска.
                    4. Цене.
                    5. Забронировать авто.\s
                                                            
                    6. Назад""");
            Scanner in = new Scanner(System.in);
            int numberSortCar = in.nextInt();
            switch (numberSortCar) {
                case 1:
                    System.out.println("Сортировка по модели");
                    listCars.sort(sortModel);
                    outCars.printAllCars(listCars);
                    break;
                case 2:
                    System.out.println("Сортировка по цвету");
                    listCars.sort(sortColor);
                    outCars.printAllCars(listCars);
                    break;
                case 3:
                    System.out.println("Сортировка по году выпуска");
                    listCars.sort(sortYear);
                    outCars.printAllCars(listCars);
                    break;
                case 4:
                    System.out.println("Сортировка по цене");
                    listCars.sort(sortPrice);
                    outCars.printAllCars(listCars);
                    break;
                case 5://бронь
                    outCars.reservation(listCars, pathToCarFile);
                    break;
                case 6:
                    option = numberSortCar;
                    break;//назад к салонам
                default:
                    System.out.println("Введите правильное значение");
            }
        }
    }

    public static void listCarsAdminMenu(OutCars outCars, ArrayList<Cars> listAudi, ArrayList<Cars> listBmw,
                                         ArrayList<Cars> listVolkswagen){
        int option = 0;
        while (option != 4) {
            System.out.println("""
                    Выберите автосалон:\s
                    1. Audi
                    2. BMW
                    3. Volkswagen

                    4. Назад""");
            Scanner in = new Scanner(System.in);
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
                    System.out.println("Введите верное значение.");
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
                    System.out.println("Введите верное значение.");
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
                    //Назад
                    break;
                default:
                    System.out.println("Введите верное значение.");
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
                    System.out.println("Введите верное значение.");
            }
        }
    }

    public static void removeReservationAdminMenu(OutCars outCars, ArrayList<Cars> listCars, String pathToCarFile) throws IOException {
        System.out.println("""
                                                                            
                            1. Снять резервацию авто
                            2. Назад""");
        Scanner in = new Scanner(System.in);
        int numberDelResVw = in.nextInt();
        switch (numberDelResVw) {
            case 1:
                outCars.removeReservation(listCars, pathToCarFile);
                break;
            case 2:
                //
                break;
            default:
                System.out.println("Введите верное значение.");
        }
    }

    public static void listUsersAdminMenu(OutUsers outUsers) throws IOException {
        int option = 0;
        while (option != 5) {
            Scanner in = new Scanner(System.in);
            ArrayList<Users> listUsers = outUsers.getUserList(pathToUserFile);
            outUsers.printAllUser(listUsers);
            System.out.println("""
                    Выберите действие:\s
                    1. Сортировка по логину
                    2. Удалить пользователя
                    3. Дать права администратора
                    4. Удалить права администратора
                                                            
                    5. Назад
                    """);
            int numberUser = in.nextInt();
            switch (numberUser) {
                case 1:
                    SortLogin sortLogin = new SortLogin();
                    listUsers.sort(sortLogin);
                    System.out.println("=== Отсортированный список ===");
                    outUsers.printAllUser(listUsers);
                    break;
                case 2:
                    outUsers.removeUser(listUsers, pathToUserFile);
                    System.out.println("=== Список обновлён ===");
                    listUsers = outUsers.getUserList(pathToUserFile);
                    outUsers.printAllUser(listUsers);
                    break;
                case 3://makeAdmin
                    outUsers.makeAdmin(listUsers, pathToUserFile);
                    System.out.println("=== Список обновлён ===");
                    listUsers = outUsers.getUserList(pathToUserFile);
                    outUsers.printAllUser(listUsers);
                    break;
                case 4://removeAdmin
                    outUsers.removeAdmin(listUsers, pathToUserFile);
                    System.out.println("=== Список обновлён ===");
                    listUsers = outUsers.getUserList(pathToUserFile);
                    outUsers.printAllUser(listUsers);
                    break;
                case 5:
                    option = numberUser;
                    break;
                default:
                    System.out.println("Введите верное значение.");
            }
        }
    }
}
