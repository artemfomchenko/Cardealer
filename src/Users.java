import java.io.*;
import java.util.Scanner;

public class Users {
    private int index;
    private String userName;
    private String password;
    private boolean isAdmin = false;

    @Override
    public String toString() {
        return "{\n"
                + "Пользователь: " + userName + "\n"
                + "Пароль: " + password + "\n"
                + "Администратор: " + isAdmin + "\n"
                + "},\n";
    }

    public String toStringUserAdmin(int userLenght) {
        return  "Индекс: " + userLenght + "\n"
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

    public void registration() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите имя пользователя:");
        setUserName(input.nextLine());
        System.out.println("Введите пароль:");
        setPassword(input.nextLine());
        //userLenght++;
        String pathToUserList = "E://IdeaProjects/CarDealer/Users/listOfUsers.txt";
        Writer write = new FileWriter(pathToUserList, true);
        write.write(toString());
        write.close();
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
                    String dbUser = read.readLine().replaceAll("Пользователь: ", "").trim();
                    String dbPassword = read.readLine().replaceAll("Пароль: ", "").trim();
                    String DBIsAdmin = read.readLine().replaceAll("Администратор: ", "").trim();
                    if (username.equals(dbUser) && password.equals(dbPassword)) {
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
