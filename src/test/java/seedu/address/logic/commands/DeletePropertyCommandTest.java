package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PropertyBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCustomerAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPropertyAtIndex;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CUSTOMER;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePropertyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Property propertyToDelete = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        DeletePropertyCommand delpropCommand = new DeletePropertyCommand(INDEX_FIRST_PROPERTY);

        String expectedMessage = String.format(DeletePropertyCommand.MESSAGE_DELETE_PROPERTY_SUCCESS,
                Messages.format(propertyToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new PropertyBook(), new UserPrefs());
        expectedModel.deleteProperty(propertyToDelete);

        assertCommandSuccess(delpropCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        DeletePropertyCommand deletePropertyCommand = new DeletePropertyCommand(outOfBoundIndex);

        assertCommandFailure(deletePropertyCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Property propertyToDelete = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        DeletePropertyCommand deletePropertyCommand = new DeletePropertyCommand(INDEX_FIRST_PROPERTY);

        String expectedMessage = String.format(DeletePropertyCommand.MESSAGE_DELETE_PROPERTY_SUCCESS,
                Messages.format(propertyToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new PropertyBook(), new UserPrefs());
        expectedModel.deleteProperty(propertyToDelete);
        showNoCustomer(expectedModel);

        assertCommandSuccess(deletePropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCustomerAtIndex(model, INDEX_FIRST_PROPERTY);

        Index outOfBoundIndex = INDEX_SECOND_CUSTOMER;
        // ensures that outOfBoundIndex is still in bounds of budget book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPropertyBook().getPropertyList().size());

        DeletePropertyCommand deletePropertyCommand = new DeletePropertyCommand(outOfBoundIndex);

        assertCommandFailure(deletePropertyCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePropertyCommand deleteFirstCommand = new DeletePropertyCommand(INDEX_FIRST_PROPERTY);
        DeletePropertyCommand deleteSecondCommand = new DeletePropertyCommand(INDEX_SECOND_PROPERTY);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePropertyCommand deleteFirstCommandCopy = new DeletePropertyCommand(INDEX_FIRST_PROPERTY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeletePropertyCommand deleteCommand = new DeletePropertyCommand(targetIndex);
        String expected = DeletePropertyCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCustomer(Model model) {
        model.updateFilteredCustomerList(p -> false);

        assertTrue(model.getFilteredCustomerList().isEmpty());
    }
}
