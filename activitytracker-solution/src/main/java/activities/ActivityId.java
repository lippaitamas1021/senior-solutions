package activities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class ActivityId implements Serializable {

    private String depName;
    private long id;

    public ActivityId(String depName, long id) {
        this.depName = depName;
        this.id = id;
    }

    public ActivityId() {
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityId that = (ActivityId) o;
        return id == that.id && Objects.equals(depName, that.depName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depName, id);
    }
}
