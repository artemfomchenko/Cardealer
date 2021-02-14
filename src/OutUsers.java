import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OutUsers {
    public ArrayList<Users> getUserList(String pathToUserFile) throws IOException {
        ArrayList<Users> listUsers = new ArrayList<Users>();
        String pathToUserList = pathToUserFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(pathToUserList));
            String line;
            while ((line = read.readLine()) != null) {
                if (line.equals("{")) {
                    Users users  = new Users();
                    users.setIndex(Integer.parseInt(read.readLine().replaceAll("Индекс: ", "").trim()));
                    users.setUserName(read.readLine().replaceAll("Логин: ", "").trim());
                    users.setPassword(read.readLine().replaceAll("Пароль: ", "").trim());
                    users.setAdmin(Boolean.parseBoolean(read.readLine().replaceAll("Администратор: ", "").trim()));
                    listUsers.add(users);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    public void printAllUser(ArrayList<Users> listUsers) {
        for (Users value : listUsers) {
            System.out.println(value);
        }
    }

    public void removeUser (ArrayList<Users> listUsers, String pathToUserFile) throws IOException {
        ArrayList<Users> listUsersAfterDelete = new ArrayList<>();
        Writer write = new FileWriter(pathToUserFile);
        Scanner numberDelete = new Scanner(System.in);
        System.out.println("Введите индекс удаляемого пользователя(аккаунта):");
        int indexDelete = numberDelete.nextInt();
        int firstUserIndex = 1;
        for (Users value : listUsers) {
            if (value.getIndex()!=indexDelete) {
                listUsersAfterDelete.add(value);
                write.write("{\n" + value.toStringUserAdmin(firstUserIndex) + "},\n");
                firstUserIndex++;
            }
        }
        write.close();
        System.out.println("Аккаунт удалён из базы.");
    }
}