package eu.mrndesign.matned;

public class SumIndexesBruteSearch implements SearchEngine<Integer[], Integer>{

    @Override
    public Integer[] find(Integer[] source, Integer target) {
        if (source == null || target == null) return null;
        for (int i = 0; i < source.length; i++) {
            if (source[i] == null) continue;
            for (int j = 1; j < source.length; j++) {
                if (i == j || source[j] == null) continue;
                if (source[i] + source[j] == target) return new Integer[]{i, j};
            }
        }
        return null;
    }
}
