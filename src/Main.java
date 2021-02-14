import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String pathToAudiFile = "E://IdeaProjects/CarDealer/Cars/listCarsAudi.txt";
        String pathToBMWFile = "E://IdeaProjects/CarDealer/Cars/listCarsBmw.txt";
        String pathToVolkswagenFile = "E://IdeaProjects/CarDealer/Cars/listCarsVolkswagen.txt";

        OutCars outCars = new OutCars();
        ArrayList<Cars> listAudi = outCars.getCarsList(pathToAudiFile);
        ArrayList<Cars> listBmw = outCars.getCarsList(pathToBMWFile);
        ArrayList<Cars> listVolkswagen = outCars.getCarsList(pathToVolkswagenFile);
        SortModel sortModel = new SortModel();
        SortColor sortColor = new SortColor();
        SortYear sortYear = new SortYear();
        SortPrice sortPrice = new SortPrice();

        String pathToUserFile = "E://IdeaProjects/CarDealer/Users/listOfUsers.txt";
        OutUsers outUsers = new OutUsers();
        ArrayList<Users> listUsers = outUsers.getUserList(pathToUserFile);

        int option = 0;

        System.out.println("\t\t***** Добро пожаловать! *****");

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
                                + "1. Список автомобилей\n"
                                + "2. Добавить автомобиль\n"
                                + "3. Удалить автомобиль\n"
                                + "4. Список забронированных автомобилей\n"//бронирование
                                + "5. Список зарегестрированных пользователей\n");//удалить пользователя/дать права админа
                        int numberAdm = in.nextInt();
                        switch (numberAdm) {
                            case 1:
                                System.out.println("Выберите автосалон: \n"
                                        + "1. Audi\n"
                                        + "2. BMW\n"
                                        + "3. Volkswagen\n"
                                        + "\n"
                                        + "4. Назад");
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
                                        + "3. Volkswagen\n"
                                        + "\n"
                                        + "4. Назад");
                                numberSalon = in.nextInt();
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
                                        + "3. Volkswagen\n"
                                        + "\n"
                                        + "4. Назад");
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
                                        //Назад в главное меню
                                        break;
                                    default:
                                        System.out.println("Введите верное значение.");
                                }
                                break;
                            case 4://Бронирование
                                //logic
                                break;
                            case 5://Список зарегестрированнных пользователей
                                //Список пользователей:
                                //id: value
                                //Логин value
                                //isAdmin = true/false
                                System.out.println("Выберите действие: \n"
                                        + "1. Удалить пользователя\n"
                                        + "2. Дать права администратора\n"
                                        + "3. Назад в главное меню\n");
                                int numberUser = in.nextInt();
                                switch (numberUser) {
                                    case 1:
                                        //
                                        break;
                                    case 2:
                                        //
                                        break;
                                    case 3:
                                        //назад в главное меню
                                        break;
                                    default:
                                        System.out.println("Введите верное значение.");
                                }
                                break;
                            default:
                                System.out.println("Введите верное значение.");
                        }

                    } else if (userType.equals("user")) {
                        System.out.println("Вы вошли как пользователь. Выберите автосалон: \n"
                                + "1. Audi\n"
                                + "2. BMW\n"
                                + "3. Volkswagen\n"
                                + "\n"
                                + "4. Назад");
                        int numberSalon = in.nextInt();
                        switch (numberSalon) {
                            case 1:
                                System.out.println("*** Автосалон Audi ***");
                                outCars.printAllCars(listAudi);
                                System.out.println("Упорядочить по:\n1. Модели.\n2. Цвету.\n3. Году выпуска.\n4. Цене.\n5. Выход к автосалонам");
                                int numberSortAudi = in.nextInt();
                                switch (numberSortAudi) {
                                    case 1:
                                        System.out.println("Сортировка по модели");
                                        listAudi.sort(sortModel);
                                        outCars.printAllCars(listAudi);
                                        break;
                                    case 2:
                                        System.out.println("Сортировка по цвету");
                                        listAudi.sort(sortColor);
                                        outCars.printAllCars(listAudi);
                                        break;
                                    case 3:
                                        System.out.println("Сортировка по году выпуска");
                                        listAudi.sort(sortYear);
                                        outCars.printAllCars(listAudi);
                                        break;
                                    case 4:
                                        System.out.println("Сортировка по цене");
                                        listAudi.sort(sortPrice);
                                        outCars.printAllCars(listAudi);
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
                                outCars.printAllCars(listBmw);
                                System.out.println("Упорядочить по:\n1. Модели.\n2. Цвету.\n3. Году выпуска.\n4. Цене.\n5. Выход к автосалонам");
                                int numberSortBmw = in.nextInt();
                                switch (numberSortBmw) {
                                    case 1:
                                        System.out.println("Сортировка по модели");
                                        listBmw.sort(sortModel);
                                        outCars.printAllCars(listBmw);
                                        break;
                                    case 2:
                                        System.out.println("Сортировка по цвету");
                                        listBmw.sort(sortColor);
                                        outCars.printAllCars(listBmw);
                                        break;
                                    case 3:
                                        System.out.println("Сортировка по году выпуска");
                                        listBmw.sort(sortYear);
                                        outCars.printAllCars(listBmw);
                                        break;
                                    case 4:
                                        System.out.println("Сортировка по цене");
                                        listBmw.sort(sortPrice);
                                        outCars.printAllCars(listBmw);
                                        break;
                                    case 5:
                                        break;
                                    //выход к салонам
                                    default:
                                        System.out.println("Введите правильное значение");
                                }
                                break;
                            case 3:
                                System.out.println("*** Автосалон Volkswagen ***");
                                outCars.printAllCars(listVolkswagen);
                                System.out.println("Упорядочить по:\n1. Модели.\n2. Цвету.\n3. Году выпуска.\n4. Цене.\n5. Выход к автосалонам");
                                int numberSortVolkswagen = in.nextInt();
                                switch (numberSortVolkswagen) {
                                    case 1:
                                        System.out.println("Сортировка по модели");
                                        listVolkswagen.sort(sortModel);
                                        outCars.printAllCars(listVolkswagen);
                                        break;
                                    case 2:
                                        System.out.println("Сортировка по цвету");
                                        listVolkswagen.sort(sortColor);
                                        outCars.printAllCars(listVolkswagen);
                                        break;
                                    case 3:
                                        System.out.println("Сортировка по году выпуска");
                                        listVolkswagen.sort(sortYear);
                                        outCars.printAllCars(listVolkswagen);
                                        break;
                                    case 4:
                                        System.out.println("Сортировка по цене");
                                        listVolkswagen.sort(sortPrice);
                                        outCars.printAllCars(listVolkswagen);
                                        break;
                                    case 5:
                                        break;
                                    //выход к салонам
                                    default:
                                        System.out.println("Введите правильное значение");
                                }
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
                    System.out.println("Регистрация...");
                    Users userRegistr = new Users();
                    userRegistr.registration(outUsers.getUserList(pathToUserFile).size());
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
