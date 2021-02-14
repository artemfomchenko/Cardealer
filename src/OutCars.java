import java.io.*;
import java.util.*;

public class OutCars {

    public ArrayList<Cars> getCarsList(String pathToFile) throws IOException {
        ArrayList<Cars> carsList = new ArrayList<Cars>();
        String pathToCarsList = pathToFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(pathToCarsList));
            String line;
            while ((line = read.readLine()) != null) {
                if (line.equals("{")) {
                    Cars cars = new Cars();
                    cars.setIndex(Integer.parseInt(read.readLine().replaceAll("Индекс: ", "").trim()));
                    cars.setBrand(read.readLine().replaceAll("Марка: ", "").trim());
                    cars.setModel(read.readLine().replaceAll("Модель: ", "").trim());
                    cars.setColor(read.readLine().replaceAll("Цвет: ", "").trim());
                    cars.setYear(Integer.parseInt(read.readLine().replaceAll("Год выпуска: ", "").trim()));
                    cars.setPrice(Integer.parseInt(read.readLine().replaceAll("Стоимость: ", "").trim()));
                    carsList.add(cars);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carsList;
    }

    public void printAllCars(ArrayList<Cars> carsList) {
        for (Cars value : carsList) {
            System.out.println(value);
        }
    }

    public void removeCar (ArrayList<Cars> carsList, String pathToFile) throws IOException {
        ArrayList<Cars> carsListAfterDelete = new ArrayList<>();
        Writer write = new FileWriter(pathToFile);
        Scanner numberDelete = new Scanner(System.in);
        System.out.println("Введите индекс удаляемого автомобиля:");
        int indexDelete = numberDelete.nextInt();
        int firstCarIndex = 1;
        for (Cars value : carsList) {
            if (value.getIndex()!=indexDelete) {
                carsListAfterDelete.add(value);
                write.write("{\n" + value.toStringAdmin(firstCarIndex) + "},\n");
                firstCarIndex++;
            }
        }
        write.close();
        System.out.println("Авто удалено из базы.");
    }
}