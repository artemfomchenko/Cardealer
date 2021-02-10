import java.util.Comparator;

public class SortPrice implements Comparator <Cars> {
    @Override
    public int compare(Cars o1, Cars o2) {
        return o1.getPrice() - o2.getPrice();
    }
}
