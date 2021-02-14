import java.util.Comparator;

public class SortLogin implements Comparator<Users> {
    @Override
    public int compare(Users o1, Users o2) {
        return o1.getUserName().compareTo(o2.getUserName());
    }
}
