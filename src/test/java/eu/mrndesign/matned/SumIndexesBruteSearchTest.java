package eu.mrndesign.matned;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SumIndexesBruteSearchTest {

    private static final Integer[] array = new Integer[]{2, 5, 7, 34, 1, 3, 5, 19, 33, 90, 12, 16, 27, 8, 6, 0, 45, 22, 35, 56, 54, 3, 4, 8, 2, 1, -100, null};
    private static final Integer[] emptyArray = new Integer[]{};
    private final SearchEngine<Integer[], Integer> sumIndexesBruteSearch = new SumIndexesBruteSearch();

    @ParameterizedTest
    @CsvSource({
            "-100,15, 26",
            "-10, 9,  26",
            "-98, 0,  26",
            "  1, 4,  15",
            "  7, 0,  1",
            " 11, 1, 14",
            " 68, 8, 18",
            "110, 19, 20",
            "112, 9,  17",
            "102, 9,  10",
            " 35, 0, 8"
    })
    void ShouldReturnExpectedPairOfIndexes_WhenGivenProperTarget(int target, int expected1, int expected2){
        Integer[] expected = new Integer[]{expected1, expected2};

        Integer[] result = sumIndexesBruteSearch.find(array, target);

        assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "1557",
            "-101",
            "730",
            "0",
            "160"
    })
    void ShouldReturnNull_WhenGivenTargetHasNoMatches(int target){
        Integer[] result = sumIndexesBruteSearch.find(array, target);

        assertNull(result);
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "10"
    })
    void ShouldReturnNull_WhenGivenEmptyArray(int target){
        Integer[] result = sumIndexesBruteSearch.find(emptyArray, target);

        assertNull(result);
    }

    @Test
    void ShouldReturnNull_WhenNullTargetOrSourceGiven_4Variants(){
//      variant 1
        Integer[] result1 = sumIndexesBruteSearch.find(array, null);
        assertNull(result1, "Only target is null - variant 1");
//      variant 2
        Integer[] result2 = sumIndexesBruteSearch.find(emptyArray, null);
        assertNull(result2, "Only target is null, array is empty - variant 2");
//      variant 3
        Integer[] result3 = sumIndexesBruteSearch.find(null, 45);
        assertNull(result3, "Only source array is null - variant 3");
//      variant 4
        Integer[] result4 = sumIndexesBruteSearch.find(null, null);
        assertNull(result4, "Source and target are null - variant 4");
    }

}