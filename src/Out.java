import java.io.*;
import java.util.*;

public class Out {

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



//    public void removeCar (ArrayList<Cars> carsList, String pathToFile) {
//        Iterator<Cars> carsIterator = carsList.iterator();
//        Scanner numberDelete = new Scanner(System.in);
//        System.out.println("Введите индекс удаляемого автомобиля:");
//        int indexSize = numberDelete.nextInt();
//        int indexDelete = indexSize - 1;
//        while (carsIterator.hasNext()) {
//            Cars cars = new Cars();
//
//            Object o = carsIterator.next();
//            if (o.equals(indexDelete)) {
//                carsIterator.remove();
//                System.out.println("Объект удалён");//test
//            }
//            System.out.println(carsIterator.next());
//        }
//    }
}