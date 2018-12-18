import com.google.common.annotations.VisibleForTesting;
import org.junit.jupiter.api.Test;

public class GreeterIT {

    @Test
    public void given_name_should_get_initial() {
        final String mickey_mouse = new Greeter().getInitials("Mickey Mouse");
        System.out.println(mickey_mouse);
    }

}
