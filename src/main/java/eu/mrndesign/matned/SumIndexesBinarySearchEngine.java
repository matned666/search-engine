package eu.mrndesign.matned;

import java.util.*;
import java.util.stream.Collectors;

public class SumIndexesBinarySearchEngine implements SearchEngine<Integer[], Integer>{

    @Override
    public Integer[] find(Integer[] source, Integer target) {
        if (target == null || source == null) {
            return null;
        }
        return findResult(source, target);
    }

    private Integer[] findResult(Integer[] source, Integer target) {
        List<Integer> l = Arrays.asList(source);
        List<Integer> sortedL = new ArrayList<>(new HashSet<>(Arrays.asList(source))).stream()
                .filter(Objects::nonNull)
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        Integer matchIndex;
        for (int i = 0; i < sortedL.size(); i++) {
            matchIndex = matchBinarySearch(sortedL, i, 0, sortedL.size(), target);
            if(matchIndex != null) {
                return new Integer[]{l.indexOf(sortedL.get(i)), l.indexOf(sortedL.get(matchIndex))};
            }
        }
        return null;
    }

    private Integer matchBinarySearch(List<Integer> sortedList, int checkedIndex, int startIdx, int end, int target) {
        int midIndex = startIdx + sortedList.subList(startIdx, end).size() / 2;
        if (checkedIndex >= sortedList.size() || checkedIndex == midIndex) {
            return null;
        }
        Integer checkedNo = sortedList.get(checkedIndex);
        Integer midNo = sortedList.get(midIndex);
        if (target == checkedNo + midNo) {
            return midIndex;
        }
        if (midIndex == end-1) return null;
        if (midNo > target - checkedNo) {
            return matchBinarySearch(sortedList, checkedIndex, startIdx, midIndex, target);
        }
        return matchBinarySearch(sortedList, checkedIndex, midIndex, end, target);
    }

}