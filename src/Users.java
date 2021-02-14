import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Users {
    private int index;
    private String userName;
    private String password;
    private boolean isAdmin = false;

    @Override
    public String toString() {
        return  "id: " + index + "\n"
                + "Логин: " + userName + "\n"
                //+ "Пароль: " + password + "\n" //убрал видимость паролей аккаунтов у админа
                + "Администратор: " + isAdmin + "\n";
    }

    public String toStringUserAdmin(int userLength) {
        return  "Индекс: " + userLength + "\n"
                + "Логин: " + userName + "\n"
                + "Пароль: " + password + "\n"
                + "Администратор: " + isAdmin + "\n";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void registration(int userLength) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите имя пользователя:");
        setUserName(input.nextLine());
        System.out.println("Введите пароль:");
        setPassword(input.nextLine());
        userLength++;

        String pathToUserList = "E://IdeaProjects/CarDealer/Users/listOfUsers.txt";
        Writer write = new FileWriter(pathToUserList, true);
        write.write("{\n"+ toStringUserAdmin(userLength) + "},\n");
        write.close();
        System.out.println("Вы успешно зарегистрированы!\n" +
                            "Выберите действие:");
    }

    public String login() {
        boolean correctLogin = false;
        String userType = "";
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Введите логин:");
            String username = input.nextLine();
            System.out.println("Введите пароль:");
            String password = input.nextLine();
            String pathToUserList = "E://IdeaProjects/CarDealer/Users/listOfUsers.txt";
            BufferedReader read = new BufferedReader(new FileReader(pathToUserList));
            String line;

            while ((line = read.readLine()) != null && !correctLogin) {
                if (line.equals("{")) {
                    int dbIndex = Integer.parseInt(read.readLine().replaceAll("Индекс: ", "").trim());
                    String dbUser = read.readLine().replaceAll("Логин: ", "").trim();
                    String dbPassword = read.readLine().replaceAll("Пароль: ", "").trim();
                    String DBIsAdmin = read.readLine().replaceAll("Администратор: ", "").trim();
                    if (dbIndex == dbIndex && username.equals(dbUser) && password.equals(dbPassword)) {
                        if (DBIsAdmin.equals("true")) {
                            correctLogin = true;
                            userType = "admin";
                        } else if (DBIsAdmin.equals("false")) {
                            correctLogin = true;
                            userType = "user";
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userType;
    }
}
