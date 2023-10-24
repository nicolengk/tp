package seedu.address.model.property;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Property {

    // Identity fields
    private final PropName propName;
    private final PropAddress propAddress;
    private final PropPhone propPhone;
    private final Price price;
    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Property(PropName propName, PropAddress propAddress, PropPhone propPhone, Price price, Set<Tag> tags) {
        requireAllNonNull(propName, propPhone, price, propAddress, tags);
        this.propName = propName;
        this.propPhone = propPhone;
        this.price = price;
        this.propAddress = propAddress;
        this.tags.addAll(tags);
    }

    public PropName getPropName() {
        return propName;
    }
    public PropAddress getPropAddress() {
        return propAddress;
    }

    public PropPhone getPropPhone() {
        return propPhone;
    }

    public Price getPrice() {
        return price;
    }



    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameProperty(Property otherProperty) {
        if (otherProperty == this) {
            return true;
        }

        return otherProperty != null
                && otherProperty.getPropName().equals(getPropName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Property)) {
            return false;
        }

        Property otherPerson = (Property) other;
        return propName.equals(otherPerson.propName)
                && propPhone.equals(otherPerson.propPhone)
                && price.equals(otherPerson.price)
                && propAddress.equals(otherPerson.propAddress)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(propName, propAddress, propPhone, price, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", propName)
                .add("address", propAddress)
                .add("phone", propPhone)
                .add("price", price)
                .add("tags", tags)
                .toString();
    }

}
