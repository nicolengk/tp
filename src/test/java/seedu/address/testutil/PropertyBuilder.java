package seedu.address.testutil;

import seedu.address.model.customer.Address;
import seedu.address.model.customer.Budget;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.property.Price;
import seedu.address.model.property.PropAddress;
import seedu.address.model.property.PropName;
import seedu.address.model.property.PropPhone;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Property objects.
 */
public class PropertyBuilder {

    public static final String DEFAULT_PROPNAME = "Jden";
    public static final String DEFAULT_PROPADDRESS = "Jurong East Central 1";
    public static final String DEFAULT_PROPPHONE = "85355255";
    public static final String DEFAULT_PRICE = "1230000";

    private PropName propName;
    private PropAddress propAddress;
    private PropPhone propPhone;
    private Price price;
    private Set<Tag> tags;

    /**
     * Creates a {@code PropertyBuilder} with the default details.
     */
    public PropertyBuilder() {
        propName = new PropName(DEFAULT_PROPNAME);
        propAddress = new PropAddress(DEFAULT_PROPADDRESS);
        propPhone = new PropPhone(DEFAULT_PROPPHONE);
        price = new Price(DEFAULT_PRICE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PropertyBuilder with the data of {@code PropertyToCopy}.
     */
    public PropertyBuilder(Property propertyToCopy) {
        propName = propertyToCopy.getPropName();
        propAddress = propertyToCopy.getPropAddress();
        propPhone = propertyToCopy.getPropPhone();
        price = propertyToCopy.getPrice();
        tags = new HashSet<>(propertyToCopy.getTags());
    }

    /**
     * Sets the {@code PropName} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPropName(String propName) {
        this.propName = new PropName(propName);
        return this;
    }

    /**
     * Sets the {@code PropAddress} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPropAddress(String propAddress) {
        this.propAddress = new PropAddress(propAddress);
        return this;
    }

    /**
     * Sets the {@code PropPhone} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPropPhone(String propPhone) {
        this.propPhone = new PropPhone(propPhone.trim());
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Property} that we are building.
     */
    public PropertyBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Property build() {
        return new Property(propName, propAddress, propPhone, price, tags);
    }

}
