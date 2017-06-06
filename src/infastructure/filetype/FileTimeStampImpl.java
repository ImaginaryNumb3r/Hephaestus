package infastructure.filetype;

import infastructure.filetype.interfaces.aubtypes.AbsolutePath;

import java.time.LocalDateTime;

/**
 * @author Patrick
 * @since 06.06.2017
 */
class FileTimeStampImpl implements FileTimeStamp {
    private final long _lastModified;
    private final long _contentSize;
    private final String _name;
    private final AbsolutePath _path;
    private final LocalDateTime _measureTime;

    public FileTimeStampImpl(long lastModified, long contentSize, String name, AbsolutePath path, LocalDateTime measureTime) {
        _lastModified = lastModified;
        _contentSize = contentSize;
        _name = name;
        _path = path;
        _measureTime = measureTime;
    }

    public long lastModified() {
        return _lastModified;
    }

    public long contentSize() {
        return _contentSize;
    }

    public String name() {
        return _name;
    }

    public AbsolutePath path() {
        return _path;
    }

    public LocalDateTime measureTime() {
        return _measureTime;
    }

}
