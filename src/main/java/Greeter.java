import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Greeter {
    public String getInitials(String names) {
        final Iterable<String> split = Splitter.on(' ').split(Strings.nullToEmpty(names));
        return StreamSupport.stream(split.spliterator(), false)
                .map(n -> n.charAt(0))
                .map(c-> Character.toString(c))
                .collect(Collectors.joining());
    }
}
