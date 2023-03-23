package rockpaperscissorstournament;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A class that can load classes from the classpath.
 * In other words, this is an abhorrent abomination that
 * was summoned into this world by the deranged.
 * 
 * It's final because nobody should ever
 * extend this, touch it, view it, use it,
 * or even think about it.
 * 
 * Do so at your own risk, we've already lost 40+
 * interns and counting.
 */
public final class ClassLoader {

    /**
     * Returns a set of all classes, enums, and interfaces that implement a given
     * class
     * 
     * @param clazz - the class to check for implementers of
     * @return the set of classes
     */
    public static <E> Set<Class<E>> allImplementers(Class<E> clazz) {
        return allClasses(x -> clazz.isAssignableFrom(x) && !x.equals(clazz)).stream()
                .map(x -> (Class<E>) x)
                .collect(Collectors.toSet());
    }

    /**
     * Filters down classes to match a predicate
     * 
     * @param filter the predicate to filter by
     * @return a set of all classes in the classpath that match the predicate
     */
    public static Set<Class<?>> allClasses(Predicate<? super Class> filter) {
        return allClasses().parallelStream().filter(filter).collect(Collectors.toSet());
    }

    /**
     * @return a set of all classes in the classpath
     */
    public static Set<Class<?>> allClasses() {
        // sometimes i wonder if god turns away not because he doesn't care
        // but because he, too, lives in fear of what he created
        return allClasspaths().parallelStream()
                .map(p -> getFiles(p, x -> x.getPath().endsWith(".class")))
                .flatMap(List::stream)
                .map(ClassLoader::fromFileToClass)
                .filter(x -> x != null)
                .collect(Collectors.toSet());

    }

    /**
     * Returns a list of all directory files in the classpath.
     * Perform ungodly things with this at your own risk.
     * 
     * @return a list of all directory files in the classpath
     */
    public static List<File> allClasspaths() {
        String classpath = System.getProperty("java.class.path");
        if (classpath == null)
            return Collections.emptyList();

        String[] paths = classpath.split(File.pathSeparator);

        // forgive me father but i must stream
        return Arrays.asList(paths).stream().map(File::new).collect(Collectors.toList());
    }

    /**
     * Converts a file to a class. The file must be in the classpath and must be a
     * .class file.
     * 
     * @param f the file to convert
     * @return the class represented by that file. Contents are unknown.
     */
    private static Class<?> fromFileToClass(File f) {
        String parentDir = f.getParentFile().getParentFile().getAbsolutePath();
        String fileName = f.getAbsolutePath();
        String classAndPackage = fileName
                .substring(parentDir.length() + 1, fileName.length() - 6) // remove .class and directory
                .replaceAll("/|\\\\", "\\."); // replace / or \ with . (marks packages)

        try {
            return Class.forName(classAndPackage);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Returns a list of all files in a folder that match a predicate.
     * Searches through subdirectories.
     * 
     * @param folder the folder to search
     * @param filter the predicate to filter by
     * @return a list of all files in the folder that match the predicate
     */
    private static List<File> getFiles(File folder, Predicate<File> filter) {
        File[] files = folder.listFiles();

        if (files == null)
            return Collections.emptyList();

        List<File> fileList = new ArrayList<>();

        for (File f : files) {
            if (filter.test(f))
                fileList.add(f);

            if (f.isDirectory())
                fileList.addAll(getFiles(f, filter));
        }
        return fileList;
    }

    public static void main(String[] args) {
        // What you've all been waiting for:
        System.out.println("All Classpaths:");
        for (File f : allClasspaths())
            System.out.println("\t" + f.getPath());

        System.out.println("All Classes:");
        for (Class<?> c : allClasses())
            System.out.println("\t" + c);
    }
}
