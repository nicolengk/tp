package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.customer.Customer;
import seedu.address.model.property.Property;


/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX = "The customer index provided is invalid";

    public static final String MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX = "The property index provided is invalid";
    public static final String MESSAGE_CUSTOMERS_LISTED_OVERVIEW = "%1$d customers listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code customer} for display to the user.
     */
    public static String format(Customer customer) {
        final StringBuilder builder = new StringBuilder();
        builder.append(customer.getName())
                .append("; Phone: ")
                .append(customer.getPhone())
                .append("; Email: ")
                .append(customer.getEmail())
                .append("; Budget: ")
                .append(customer.getBudget())
                .append("; Tags: ");
        customer.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code property} for display to the user.
     */
    public static String format(Property property) {
        final StringBuilder builder = new StringBuilder();
        builder.append(property.getPropName())
                .append("; Address: ")
                .append(property.getPropAddress())
                .append("; Phone: ")
                .append(property.getPropPhone())
                .append("; Price: ")
                .append(property.getPrice())
                .append("; Tags: ");
        property.getTags().forEach(builder::append);
        return builder.toString();
    }
}
