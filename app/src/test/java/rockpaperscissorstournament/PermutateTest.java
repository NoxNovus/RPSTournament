package rockpaperscissorstournament;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

class PermutateTest {
    @Test
    void testPermutateEmpty() {
        Iterator<Entry<Object, Object>> it = Permutate.iterPermutations(List.of().iterator(), List.of().iterator());
        assertFalse(it.hasNext());
    }

    @Test
    void testPermutateOneEmpty() {
        Iterator<?> it = Permutate.iterPermutations(List.of().iterator(), List.of(1).iterator());
        assertFalse(it.hasNext());
    }

    @Test
    void testPermutateCorrect() {
        Iterator<Entry<Integer, Integer>> it = Permutate.iterPermutations(
                List.of(1, 2).iterator(),
                List.of(3, 4).iterator());
        assertTrue(it.hasNext());

        List<?> permutations = iterToLst(it);
        Set<?> set = new HashSet<>(permutations);
        assertEquals(permutations.size(), set.size(), "There should be no duplicates");
        assertEquals(4, permutations.size(), "There should be 4 permutations");
        assertAll("All permutations should be correct",
                () -> assertTrue(set.contains(new SimpleEntry<>(1, 3))),
                () -> assertTrue(set.contains(new SimpleEntry<>(1, 4))),
                () -> assertTrue(set.contains(new SimpleEntry<>(2, 3))),
                () -> assertTrue(set.contains(new SimpleEntry<>(2, 4))));
    }

    @Test
    void testCombinationsCorrectNoDuplicates() {
        Iterator<Entry<Integer, Integer>> it = Permutate.iterCombinations(
                List.of(1, 2).iterator(),
                List.of(3, 4).iterator());
        assertTrue(it.hasNext());

        List<?> permutations = iterToLst(it);
        Set<?> set = new HashSet<>(permutations);
        System.out.println(permutations);
        assertEquals(permutations.size(), set.size(), "There should be no duplicates");
        assertEquals(4, permutations.size(), "There should be 4 permutations");
        assertAll("All permutations should be correct",
                () -> assertTrue(set.contains(new SimpleEntry<>(1, 3))),
                () -> assertTrue(set.contains(new SimpleEntry<>(1, 4))),
                () -> assertTrue(set.contains(new SimpleEntry<>(2, 3))),
                () -> assertTrue(set.contains(new SimpleEntry<>(2, 4))));
    }

    @Test
    void testCombinationsCorrectWithDuplicates() {
        Iterator<Entry<Integer, Integer>> it = Permutate.iterCombinations(
                List.of(1, 2).iterator(),
                List.of(1, 2).iterator());
        assertTrue(it.hasNext());

        List<?> permutations = iterToLst(it);
        Set<?> set = new HashSet<>(permutations);
        assertEquals(2, permutations.size(), "There should be 4 permutations");
        assertAll("All permutations should be correct",
                () -> assertTrue(set.contains(new SimpleEntry<>(1, 2))),
                () -> assertTrue(set.contains(new SimpleEntry<>(2, 1))));
    }

    private <E> List<E> iterToLst(Iterator<E> it) {
        List<E> lst = new ArrayList<>();
        while (it.hasNext())
            lst.add(it.next());
        return lst;
    }
}
