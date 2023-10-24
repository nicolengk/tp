package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.property.Price;
import seedu.address.model.property.PropAddress;
import seedu.address.model.property.PropName;
import seedu.address.model.property.PropPhone;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Property}.
 */
class JsonAdaptedProperty {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Property's %s field is missing!";

    private final String name;

    private final String address;
    private final String phone;
    private final String price;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedProperty(@JsonProperty("name") String name, @JsonProperty("address") String address,
                               @JsonProperty("phone") String phone, @JsonProperty("price") String price,
                               @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.price = price;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedProperty(Property source) {
        name = source.getPropName().fullName;
        phone = source.getPropPhone().value;
        price = source.getPrice().value;
        address = source.getPropAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Property toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, PropName.class.getSimpleName()));
        }
        if (!PropName.isValidName(name)) {
            throw new IllegalValueException(PropName.MESSAGE_CONSTRAINTS);
        }
        final PropName modelPropName = new PropName(name);

        if (phone == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, PropPhone.class.getSimpleName()));
        }
        if (!PropPhone.isValidPhone(phone)) {
            throw new IllegalValueException(PropPhone.MESSAGE_CONSTRAINTS);
        }
        final PropPhone modelPropPhone = new PropPhone(phone);

        if (price == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        if (address == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, PropAddress.class.getSimpleName()));
        }
        if (!PropAddress.isValidAddress(address)) {
            throw new IllegalValueException(PropAddress.MESSAGE_CONSTRAINTS);
        }
        final PropAddress modelPropAddress = new PropAddress(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Property(modelPropName, modelPropAddress, modelPropPhone, modelPrice, modelTags);
    }

}
