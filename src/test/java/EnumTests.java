import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnumTests {

    @Test
    public void testDescriptionAnnotation() {
        assertEquals("foo", TestEnum.FOO.description());
        assertEquals("bar", TestEnum.BAR.description());
    }

    @Test
    public void testToArray(){
        assertFalse(TestEnum.FOO.toArray().isEmpty());
    }

}
