import java.util.Comparator;

public class SortModel implements Comparator<Cars> {
    @Override
    public int compare(Cars o1, Cars o2) {
        return o1.getModel().compareTo(o2.getModel());
    }
}
