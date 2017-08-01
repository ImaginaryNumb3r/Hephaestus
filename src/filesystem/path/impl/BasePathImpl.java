package filesystem.path.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public class BasePathImpl implements Path {
    private final Path _path;

    protected BasePathImpl(Path path) {
        _path = path;
    }

    @Override
    public FileSystem getFileSystem() {
        return _path.getFileSystem();
    }

    @Deprecated
    @Override
    public boolean isAbsolute() {
        return _path.isAbsolute();
    }

    @Override
    public Path getRoot() {
        return _path.getRoot();
    }

    @Deprecated
    @Override
    public Path getFileName() {
        return _path.getFileName();
    }

    @Override
    public Path getParent() {
        return _path.getParent();
    }

    @Override
    public int getNameCount() {
        return _path.getNameCount();
    }

    @Override
    public Path getName(int index) {
        return _path.getName(index);
    }

    @Override
    public Path subpath(int beginIndex, int endIndex) {
        return _path.subpath(beginIndex, endIndex);
    }

    @Override
    public boolean startsWith(Path other) {
        return _path.startsWith(other);
    }

    @Override
    public boolean startsWith(String other) {
        return _path.startsWith(other);
    }

    @Override
    public boolean endsWith(Path other) {
        return _path.endsWith(other);
    }

    @Override
    public boolean endsWith(String other) {
        return _path.endsWith(other);
    }

    @Override
    public Path normalize() {
        return _path.normalize();
    }

    @Override
    public Path resolve(Path other) {
        return _path.resolve(other);
    }

    @Override
    public Path resolve(String other) {
        return _path.resolve(other);
    }

    @Override
    public Path resolveSibling(Path other) {
        return _path.resolveSibling(other);
    }

    @Override
    public Path resolveSibling(String other) {
        return _path.resolveSibling(other);
    }

    @Override
    public Path relativize(Path other) {
        return _path.relativize(other);
    }

    @Override
    public URI toUri() {
        return _path.toUri();
    }

    @Override
    public Path toAbsolutePath() {
        return _path.toAbsolutePath();
    }

    @Override
    public Path toRealPath(LinkOption... options) throws IOException {
        return _path.toRealPath(options);
    }

    @Override
    public File toFile() {
        return _path.toFile();
    }

    @Override
    public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
        return _path.register(watcher, events, modifiers);
    }

    @Override
    public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events) throws IOException {
        return _path.register(watcher, events);
    }

    @Override
    public Iterator<Path> iterator() {
        return _path.iterator();
    }

    @Override
    public int compareTo(Path other) {
        return _path.compareTo(other);
    }

    @Override
    public boolean equals(Object other) {
        return _path.equals(other);
    }

    @Override
    public int hashCode() {
        return _path.hashCode();
    }

    @Override
    public String toString() {
        return _path.toString();
    }

    @Override
    public void forEach(Consumer<? super Path> action) {
        _path.forEach(action);
    }

    @Override
    public Spliterator<Path> spliterator() {
        return _path.spliterator();
    }
}
