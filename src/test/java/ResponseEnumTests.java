import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResponseEnumTests {

    @Test
    public void testExample() {

        System.out.println(ResponseEnum.SYSTEM_ERROR.value());
        System.out.println(ResponseEnum.SYSTEM_ERROR.note());

    }

    @Test
    public void testResponseValue() {
        Assertions.assertEquals(500, ResponseEnum.SYSTEM_ERROR.value());
    }

    @Test
    public void testResponseDescAndNote() {
        Assertions.assertNotNull(ResponseEnum.SYSTEM_ERROR.description());
        Assertions.assertNotNull(ResponseEnum.SYSTEM_ERROR.note());
    }

}
