package locations;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class JUnit4Test {

    @DisplayName("JUnit4Test")
    @Test
    public void testCreate() {
        Location location = new Location("Békéscsaba", 0, 72.913752);
        Assert.assertEquals("Békéscsaba", location.getName());
        Assert.assertEquals(0.0, location.getLat(), 1);
        Assert.assertEquals(72, location.getLon(), 1);
    }
}
