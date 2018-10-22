package seedu.address.testutil;

import java.util.List;

import seedu.address.logic.anakincommands.AnakinEditDeckCommand.EditDeckDescriptor;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinUniqueCardList;
import seedu.address.model.anakindeck.Name;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * A utility class to help with building EditDeckDescriptor objects.
 */
public class EditDeckDescriptorBuilder {

    private EditDeckDescriptor descriptor;

    public EditDeckDescriptorBuilder() {
        descriptor = new EditDeckDescriptor();
    }

    public EditDeckDescriptorBuilder(EditDeckDescriptor descriptor) {
        this.descriptor = new EditDeckDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDeckDescriptor} with fields containing {@code Cards}'s details
     */
    public EditDeckDescriptorBuilder(AnakinDeck deck) {
        descriptor = new EditDeckDescriptor();
        descriptor.setName(deck.getName());
        descriptor.setCards(deck.getCards());

    }

    /**
     * Sets the {@code Name} of the {@code EditDeckDescriptor} that we are building.
     */
    public EditDeckDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Cards} of the {@code EditDeckDescriptor} that we are building.
     */
    public EditDeckDescriptorBuilder withCards(AnakinUniqueCardList cards) {
        descriptor.setCards(cards);
        return this;
    }
    

    public EditDeckDescriptor build() {
        return descriptor;
    }
}
