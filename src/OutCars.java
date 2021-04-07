import java.io.*;
import java.util.*;

public class OutCars {

    public ArrayList<Cars> getCarsList(String pathToFile) {
        ArrayList<Cars> carsList = new ArrayList<>();
        String pathToCarsList = pathToFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(pathToCarsList));
            String line;
            while ((line = read.readLine()) != null) {
                if (line.equals("{")) {
                    Cars cars = new Cars();
                    cars.setIndex(Integer.parseInt(read.readLine().
                            replaceAll("Индекс: ", "").trim()));
                    cars.setBrand(read.readLine().
                            replaceAll("Марка: ", "").trim());
                    cars.setModel(read.readLine().
                            replaceAll("Модель: ", "").trim());
                    cars.setColor(read.readLine().
                            replaceAll("Цвет: ", "").trim());
                    cars.setYear(Integer.parseInt(read.readLine().
                            replaceAll("Год выпуска: ", "").trim()));
                    cars.setPrice(Integer.parseInt(read.readLine().
                            replaceAll("Стоимость: ", "").trim()));
                    cars.setOrder(Boolean.parseBoolean(read.readLine().
                            replaceAll("Бронирование: ", "").trim()));
                    cars.setOrderIndex(read.readLine().
                            replaceAll("Индекс пользователя: ", "").trim());
                    cars.setOrderUser(read.readLine().
                            replaceAll("Логин пользователя: ", "").trim());
                    carsList.add(cars);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carsList;
    }

    public void printAllCars(ArrayList<Cars> carsList) {
        carsList.forEach(System.out::println);
    }

    public void printReservationCars(ArrayList<Cars> carsList) {
        for (Cars value : carsList) {
            if (value.isOrder()) {
                System.err.println("Забронирован: " +
                        "\n" + "Id аккаунта: " + value.getOrderIndex() +
                        "\n" + "Логин: " + value.getOrderUser());
                System.out.println(value);
            }
        }
    }

    public void removeCar(ArrayList<Cars> carsList, String pathToFile) throws IOException {
        Writer write = new FileWriter(pathToFile);
        Scanner numberDelete = new Scanner(System.in);
        int indexDelete = Checks.checkCarsIndexRange(numberDelete, carsList);
        int firstCarIndex = 1;
        for (Cars value : carsList) {
            if (value.getIndex() != indexDelete) {
                write.write("{\n" + value.toStringAdmin(firstCarIndex) + "},\n");
                firstCarIndex++;
            }
        }
        write.close();
        System.out.println("Авто удалено из базы.");
    }

    public void reservation(ArrayList<Cars> carsList, String pathToFile, CurrentUser currentUser) throws IOException {
        Writer write = new FileWriter(pathToFile);
        Scanner numberReservation = new Scanner(System.in);
        int indexReservation = Checks.checkCarsIndexRange(numberReservation, carsList);
        int firstCarIndex = 1;
        for (Cars value : carsList) {
            if (value.getIndex() == indexReservation && !value.isOrder()) {
                value.setOrder(true);
                value.setOrderIndex(currentUser.getIndex());
                value.setOrderUser(currentUser.getUserName());
                write.write("{\n" + value.toStringAdmin(firstCarIndex) + "},\n");
                firstCarIndex++;
                System.out.println("Выбранный авто зарезервирован!");
            } else if (value.getIndex() == indexReservation && value.isOrder()) {
                System.err.println("Выбранный авто не доступен к резервированию!");
                write.write("{\n" + value.toStringAdmin(firstCarIndex) + "},\n");
                firstCarIndex++;
            } else {
                write.write("{\n" + value.toStringAdmin(firstCarIndex) + "},\n");
                firstCarIndex++;
            }
        }
        write.close();
    }

    public void removeReservation(ArrayList<Cars> carsList, String pathToFile) throws IOException {
        Writer write = new FileWriter(pathToFile);
        Scanner numberDelete = new Scanner(System.in);
        int indexDelete = Checks.checkCarsIndexRange(numberDelete, carsList);
        int firstCarIndex = 1;
        for (Cars value : carsList) {
            if (value.getIndex() == indexDelete && value.isOrder()) {
                value.setOrder(false);
                value.setOrderUser("empty");
                value.setOrderIndex("empty");
                write.write("{\n" + value.toStringAdmin(firstCarIndex) + "},\n");
                firstCarIndex++;
                System.out.println("Резерв снят.");
            } else if (value.getIndex() == indexDelete && !value.isOrder()) {
                System.err.println("Выбранный авто не находится в резерве!");
                write.write("{\n" + value.toStringAdmin(firstCarIndex) + "},\n");
                firstCarIndex++;
            } else {
                write.write("{\n" + value.toStringAdmin(firstCarIndex) + "},\n");
                firstCarIndex++;
            }
        }
        write.close();
    }
}