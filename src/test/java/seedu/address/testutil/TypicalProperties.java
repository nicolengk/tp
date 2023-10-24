package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.customer.Customer;
import seedu.address.model.property.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalProperties {
    public static final Property JDEN = new PropertyBuilder().withPropName("Jden")
            .withPropAddress("Jurong East Central 1").withPropPhone("91135235")
            .withPrice("50000000")
            .withTags("new", "launch").build();
    public static final Property DLEEDON = new PropertyBuilder().withPropName("DLeedon")
            .withPropAddress("Leedon Heights").withPropPhone("91135235")
            .withPrice("90000000")
            .withTags("luxury").build();
    public static final Property PALM_GARDENS = new PropertyBuilder().withPropName("Palm Gardens")
            .withPropAddress("Hong San Walk").withPropPhone("93235232")
            .withPrice("20000000")
            .withTags("cck").build();
    public static final Property PARC_RIVERA = new PropertyBuilder().withPropName("Parc Rivera")
            .withPropAddress("West Coast Vale").withPropPhone("93235232")
            .withPrice("1600000")
            .withTags("new", "launch").build();

    private TypicalProperties() {} // prevents instantiation

    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Property property: getTypicalProperties()) {
            ab.addProperty(property);
        }
        return ab;
    }
    public static List<Property> getTypicalProperties() {
        return new ArrayList<>(Arrays.asList(JDEN, DLEEDON, PALM_GARDENS, PARC_RIVERA));
    }
}
