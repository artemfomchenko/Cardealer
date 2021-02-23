public class CurrentUser {
    private String index;
    private String userName;

    @Override
    public String toString() {
        return "id: " + index + "\n"
                + "Логин: " + userName + "\n";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
