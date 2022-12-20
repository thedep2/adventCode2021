package fr.thedep.year2022.day1;

import fr.thedep.utils.ReadFilesUtils;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public final class Day7 {

    private static final String INPUTS
            = """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
            """;

    private Day7() {}

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exo1(INPUTS.lines().toList()));
        System.out.println(exo1(ReadFilesUtils.readFileInString("main", "year2022day7exo1.txt")));
        System.out.println(exo2(INPUTS.lines().toList()));
        System.out.println(exo2(ReadFilesUtils.readFileInString("main", "year2022day7exo1.txt")));
    }

    @NotNull
    private static String exo1(List<String> inputs) {

        final List<Output> outputs = inputs.stream()
                                           .map(Output::getOutput)
                                           .toList();

        final Folder rootFolder = new Folder("/", null);
        List<Folder> allFolders = new ArrayList<>();

        AtomicReference<Folder> currentDirectory = new AtomicReference<>();

        outputs.forEach(output -> {
            switch (output) {
                case ChangeDirectory cd -> {
                    if ("/".equals(cd.newDirectory)) {
                        currentDirectory.set(rootFolder);
                    } else if ("..".equals(cd.newDirectory)) {
                        currentDirectory.set(currentDirectory.get().getParent());
                    } else {
                        final Folder folder = (Folder) currentDirectory.get().nodes().get(cd.newDirectory);
                        currentDirectory.set(folder);
                    }
                }
                case ListDirectory ls -> {
                }
                case FolderResponse fr -> {
                    final Folder currentFolder = currentDirectory.get();
                    final Folder newFolder = new Folder(fr.name(), currentFolder);
                    currentFolder.addNode(newFolder);
                    allFolders.add(newFolder);
                }
                case SizeFile sf -> {
                    final Folder folder = currentDirectory.get();
                    folder.addNode(new File(sf.size(), sf.name()));
                }
                default -> throw new IllegalStateException("Unexpected value: " + output);
            }
        });

        for (var folder : allFolders) {
            folder.calculSize();
        }

        allFolders.stream()
                  .sorted(Comparator.comparing(Folder::getName))
                  .forEach(folder -> System.out.println("folder = " + folder.getName() + " - " + folder.size()));

        final long sum = allFolders.stream()
                                   .filter(folder -> !"/".equals(folder.getName()))
                                   .mapToLong(Folder::size)
                                   .filter(integer -> integer <= 100_000)
                                   .sum();

        return "" + sum;
    }

    @NotNull
    private static String exo2(List<String> inputs) {
        final List<Output> outputs = inputs.stream()
                                           .map(Output::getOutput)
                                           .toList();

        final Folder rootFolder = new Folder("/", null);
        List<Folder> allFolders = new ArrayList<>();
        allFolders.add(rootFolder);

        AtomicReference<Folder> currentDirectory = new AtomicReference<>();

        outputs.forEach(output -> {
            switch (output) {
                case ChangeDirectory cd -> {
                    if ("/".equals(cd.newDirectory)) {
                        currentDirectory.set(rootFolder);
                    } else if ("..".equals(cd.newDirectory)) {
                        currentDirectory.set(currentDirectory.get().getParent());
                    } else {
                        final Folder folder = (Folder) currentDirectory.get().nodes().get(cd.newDirectory);
                        currentDirectory.set(folder);
                    }
                }
                case ListDirectory ls -> {
                }
                case FolderResponse fr -> {
                    final Folder currentFolder = currentDirectory.get();
                    final Folder newFolder = new Folder(fr.name(), currentFolder);
                    currentFolder.addNode(newFolder);
                    allFolders.add(newFolder);
                }
                case SizeFile sf -> {
                    final Folder folder = currentDirectory.get();
                    folder.addNode(new File(sf.size(), sf.name()));
                }
                default -> throw new IllegalStateException("Unexpected value: " + output);
            }
        });

        for (var folder : allFolders) {
            folder.calculSize();
        }

        allFolders.stream()
                  .sorted(Comparator.comparing(Folder::getName))
                  .forEach(folder -> System.out.println("folder = " + folder.getName() + " - " + folder.size()));

        long sizeOfRoot = rootFolder.size();
        long spaceLeft = 70000000 - sizeOfRoot;
        long spaceToRemove = 30000000 - spaceLeft;
        System.out.println("spaceToRemove = " + spaceToRemove);

        final long candidate = allFolders.stream()
                                         .mapToLong(Folder::size)
                                         .filter(integer -> integer > spaceToRemove)
                                         .min()
                                         .getAsLong();

        return "" + candidate;
    }

    sealed interface Node {}

    sealed interface Output {

        private static Output getOutput(String line) {
            if (line.startsWith("$")) {
                if (line.startsWith("$ cd "))
                    return new ChangeDirectory(line.substring(5));
                else
                    return new ListDirectory();
            } else {
                if (line.startsWith("dir "))
                    return new FolderResponse(line.substring(4));
                else {
                    final String[] parsed = line.split(" ");
                    return new SizeFile(Integer.parseInt(parsed[0]), parsed[1]);
                }
            }
        }
    }

    sealed interface Command extends Output {}

    sealed interface Response extends Output {}

    static final class Folder implements Node {

        private final String name;
        private final Map<String, Node> nodes;
        private final Folder parent;
        private boolean sizeComputed = false;
        private long size;

        Folder(String name, Folder parent) {
            this.name = name;
            this.parent = parent;
            this.nodes = new HashMap<>();
            this.size = 0;
        }

        public String getName() {
            return name;
        }

        public Map<String, Node> nodes() {return nodes;}

        public void addNode(Node node) {
            String nameForMap = switch (node) {
                case Folder folder -> folder.getName();
                case File file -> file.name();
            };
            nodes.put(nameForMap, node);
        }

        public void addSize(long sizeToAdd) {
            size += sizeToAdd;
        }

        public long size() {
            return size;
        }

        public void calculSize() {
            if (!sizeComputed) {
                for (Node node : nodes.values()) {
                    if (Objects.requireNonNull(node) instanceof Folder subFolder) {
                        subFolder.calculSize();
                        addSize(subFolder.size());
                    } else if (node instanceof File file) {
                        addSize(file.size());
                    }
                }
                sizeComputed = true;
            }
        }

        public Folder getParent() {
            return parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Folder folder = (Folder) o;
            return getName().equals(folder.getName()) && Objects.equals(getParent(), folder.getParent());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getParent());
        }

        @Override
        public String toString() {
            return "Folder{" +
                   "name='" + name + '\'' +
                   ", nodes=" + nodes +
                   ", sizeComputed=" + sizeComputed +
                   ", size=" + size +
                   '}';
        }

    }

    record File(int size, String name) implements Node {}

    record SizeFile(int size, String name) implements Response {}

    record FolderResponse(String name) implements Response {}

    record ChangeDirectory(String newDirectory) implements Command {}

    record ListDirectory() implements Command {}

}
