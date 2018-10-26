package seedu.address.testutil;

import java.util.List;

import seedu.address.logic.anakincommands.EditDeckCommand.EditDeckDescriptor;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Deck;
import seedu.address.model.anakindeck.Name;
import seedu.address.model.anakindeck.UniqueCardList;

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
     * Returns an {@code EditDeckDescriptor} with fields containing {@code person}'s details
     */
    public EditDeckDescriptorBuilder(Deck deck) {
        descriptor = new EditDeckDescriptor();
        descriptor.setName(deck.getName());

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
    public EditDeckDescriptorBuilder withCards(List<Card> cardlist) {
        UniqueCardList uniqueCardList = new UniqueCardList();
        uniqueCardList.setCards(cardlist);
        return this;
    }


    public EditDeckDescriptor build() {
        return descriptor;
    }
}
