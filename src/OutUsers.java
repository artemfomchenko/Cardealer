import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OutUsers {
    public ArrayList<Users> getUserList(String pathToUserFile) {
        ArrayList<Users> listUsers = new ArrayList<>();
        String pathToUserList = pathToUserFile;
        try {
            BufferedReader read = new BufferedReader(new FileReader(pathToUserList));
            String line;
            while ((line = read.readLine()) != null) {
                if (line.equals("{")) {
                    Users users = new Users();
                    users.setIndex(Integer.parseInt(read.readLine().
                            replaceAll("Индекс: ", "").trim()));
                    users.setUserName(read.readLine().
                            replaceAll("Логин: ", "").trim());
                    users.setPassword(read.readLine().
                            replaceAll("Пароль: ", "").trim());
                    users.setPhoneNumber(read.readLine().
                            replaceAll("Номер телефона: ", "").trim());
                    users.setAdmin(Boolean.parseBoolean(read.readLine().
                            replaceAll("Администратор: ", "").trim()));
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

    public void removeUser(ArrayList<Users> listUsers, String pathToUserFile) throws IOException {
        Writer write = new FileWriter(pathToUserFile);
        Scanner numberDelete = new Scanner(System.in);
        int indexDelete = Checks.checkUsersIndexRange(numberDelete, listUsers);
        int firstUserIndex = 1;
        for (Users value : listUsers) {
            if (value.getIndex() != indexDelete) {
                write.write("{\n" + value.toStringUserAdmin(firstUserIndex) + "},\n");
                firstUserIndex++;
            }
        }
        write.close();
        System.out.println("Аккаунт удалён из базы.");
    }

    public void makeAdmin(ArrayList<Users> listUsers, String pathToUserFile) throws IOException {
        Writer write = new FileWriter(pathToUserFile);
        Scanner numAdm = new Scanner(System.in);
        System.out.println("Введите индекс аккаунта:");
        int indexAddAdmin = Checks.checkUsersIndexRange(numAdm, listUsers);
        int firstUserIndex = 1;
        for (Users value : listUsers) {
            if (value.getIndex() == indexAddAdmin && !value.isAdmin()) {
                System.out.println(value.isAdmin());
                value.setAdmin(true);
                write.write("{\n" + value.toStringUserAdmin(firstUserIndex) + "},\n");
                firstUserIndex++;
                System.out.println("Вы дали права администратора.");
            } else if (value.getIndex() == indexAddAdmin && value.isAdmin()) {
                System.err.println("Пользователь уже имеет права администратора.");
                write.write("{\n" + value.toStringUserAdmin(firstUserIndex) + "},\n");
                firstUserIndex++;
            } else {
                write.write("{\n" + value.toStringUserAdmin(firstUserIndex) + "},\n");
                firstUserIndex++;
            }
        }
        write.close();
    }

    public void removeAdmin(ArrayList<Users> listUsers, String pathToUserFile) throws IOException {
        Writer write = new FileWriter(pathToUserFile);
        Scanner numAdm = new Scanner(System.in);
        System.out.println("Введите индекс аккаунта:");
        int indexRemoveAdmin = Checks.checkUsersIndexRange(numAdm, listUsers);
        int firstUserIndex = 1;
        for (Users value : listUsers) {
            if (value.getIndex() == indexRemoveAdmin && value.isAdmin()) {
                value.setAdmin(false);
                write.write("{\n" + value.toStringUserAdmin(firstUserIndex) + "},\n");
                firstUserIndex++;
                System.out.println("Вы удалили права администратора.");
            } else if (value.getIndex() == indexRemoveAdmin && !value.isAdmin()) {
                System.err.println("Пользователь не имеет прав администратора.");
                write.write("{\n" + value.toStringUserAdmin(firstUserIndex) + "},\n");
                firstUserIndex++;
            } else {
                write.write("{\n" + value.toStringUserAdmin(firstUserIndex) + "},\n");
                firstUserIndex++;
            }
        }
        write.close();
    }
}