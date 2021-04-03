import java.util.ArrayList;
import java.util.List;

public class GetThem {
  public List<int[]> getThem() {
    List<int[]> list1 = new ArrayList<int[]>();

    for (int[] x : list1) {
      if (x[0] == 4)
        list1.add(x);
    }

    return list1;
  }
}
