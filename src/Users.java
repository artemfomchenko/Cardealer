import java.io.*;
import java.util.Scanner;

public class Users {
    private int index;
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean isAdmin = false;

    @Override
    public String toString() {
        return "id: " + index + "\n"
                + "Логин: " + userName + "\n"
                + "Номер телефона: " + phoneNumber + "\n"
                + "Администратор: " + isAdmin + "\n";
    }

    public String toStringUserAdmin(int userLength) {
        return "Индекс: " + userLength + "\n"
                + "Логин: " + userName + "\n"
                + "Пароль: " + password + "\n"
                + "Номер телефона: " + phoneNumber + "\n"
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        setUserName(Checks.checkUsersLogin(input)); //Введите имя пользователя
        setPassword(Checks.checkUsersPassword(input)); //введите пароль
        setPhoneNumber(Checks.checkUsersPhoneNumber(input)); //Введите свой номер телефона
        userLength++;

        String pathToUserList = "E://IdeaProjects/CarDealer/Users/listOfUsers.txt";
        Writer write = new FileWriter(pathToUserList, true);
        write.write("{\n" + toStringUserAdmin(userLength) + "},\n");
        write.close();
        System.out.println("Вы успешно зарегистрированы!\n" +
                "Выберите действие:");
    }

    public String login(CurrentUser currentUser) {
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
                    String dbPhoneNumber = read.readLine().replaceAll("Номер телефона: ", "").trim();
                    String DBIsAdmin = read.readLine().replaceAll("Администратор: ", "").trim();
                    if (username.equals(dbUser) && password.equals(dbPassword)) {
                        if (DBIsAdmin.equals("true")) {
                            correctLogin = true;
                            userType = "admin";
                        } else if (DBIsAdmin.equals("false")) {
                            correctLogin = true;
                            userType = "user";
                            currentUser.setIndex(Integer.toString(dbIndex));
                            currentUser.setUserName(dbUser);
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
