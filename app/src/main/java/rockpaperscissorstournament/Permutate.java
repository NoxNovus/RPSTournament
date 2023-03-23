package rockpaperscissorstournament;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

public final class Permutate {

    /**
     * Returns an iterator over all permutations of the elements of a and b.
     * 
     * @param <E> The type of the elements of a.
     * @param <T> The type of the elements of b.
     * @param a   The first iterator.
     * @param b   The second iterator.
     * @return An iterator over all permutations of the elements of a and b.
     *         Note that duplicates and null values are not checked for
     */
    public static <E, T> Iterator<Entry<E, T>> iterPermutations(Iterator<E> a, Iterator<T> b) {
        List<T> bList = new ArrayList<>();
        while (b.hasNext())
            bList.add(b.next());

        return new Iterator<Entry<E, T>>() {
            private int i = 0;
            private E lastA = null;

            @Override
            public boolean hasNext() {
                return (a.hasNext() || lastA != null) && i < bList.size();
            }

            @Override
            public Entry<E, T> next() {
                if (lastA == null)
                    lastA = a.next();

                Entry<E, T> x = new SimpleEntry<>(lastA, bList.get(i));

                i = i + 1;
                if (i == bList.size()) {
                    i = 0;
                    lastA = null;
                }

                return x;
            }
        };
    }

    /**
     * Returns an iterator over all combinations of the elements of a and b.
     * 
     * @param <E> The type of the elements of a.
     * @param <T> The type of the elements of b.
     * @param a   The first iterator.
     * @param b   The second iterator.
     * @return An iterator over all combinations of the elements of a and b.
     *         Element's .equals() method is used to check for duplicates.
     *         Note that duplicates and null values are not checked for
     */
    public static <E, T> Iterator<Entry<E, T>> iterCombinations(Iterator<E> a, Iterator<T> b) {
        // Same as iterPermutations, but ignores an entry when E.equals(T)
        // WIP
        return iterPermutations(a, b);
    }
}