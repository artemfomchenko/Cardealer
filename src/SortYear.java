import java.util.Comparator;

public class SortYear implements Comparator<Cars> {
    @Override
    public int compare(Cars o1, Cars o2) {
        return o1.getYear() - o2.getYear();
    }
}
