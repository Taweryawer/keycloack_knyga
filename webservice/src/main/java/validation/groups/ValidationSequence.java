package validation.groups;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({ Default.class, Extended.class })
public interface ValidationSequence {
}
