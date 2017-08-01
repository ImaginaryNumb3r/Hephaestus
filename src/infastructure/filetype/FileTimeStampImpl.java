package infastructure.filetype;

import core.datastructure.Lazy;
import core.util.TimeTable;
import infastructure.filetype.interfaces.aubtypes.AbsolutePath;

import java.time.LocalDateTime;

import static core.datastructure.Lazy.lazily;

/**
 * @author Patrick
 * @since 06.06.2017
 */
final class FileTimeStampImpl implements FileTimeStamp {
    private final long _lastModified;
    private final Lazy<LocalDateTime> _localDateTime;
    private final long _contentSize;
    private final String _name;
    private final AbsolutePath _path;
    private final LocalDateTime _measureTime;

    public FileTimeStampImpl(long lastModified, long contentSize, AbsolutePath path, LocalDateTime measureTime) {
        _lastModified = lastModified;
        _contentSize = contentSize;
        _name = path.getName();
        _path = path;
        _measureTime = measureTime;
        _localDateTime = lazily(() -> TimeTable.toLocalDateTime(lastModified));
    }

    public long lastModified() {
        return _lastModified;
    }

    public LocalDateTime lastModifiedDateTime() {
        return _localDateTime.get();
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
