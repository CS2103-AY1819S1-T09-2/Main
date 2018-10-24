package seedu.address.testutil;

import seedu.address.logic.anakincommands.AnakinEditCardCommand;
import seedu.address.logic.anakincommands.AnakinEditCardCommand.EditCardDescriptor;
import seedu.address.model.anakindeck.AnakinAnswer;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinQuestion;

/**
 * A utility class to help with building EditCardDescriptor objects.
 */
public class EditCardDescriptorBuilder {

    private EditCardDescriptor descriptor;

    public EditCardDescriptorBuilder() {
        descriptor = new EditCardDescriptor();
    }

    public EditCardDescriptorBuilder(EditCardDescriptor descriptor) {
        this.descriptor = new EditCardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCardDescriptor} with fields containing {@code person}'s details
     */
    public EditCardDescriptorBuilder(AnakinCard card) {
        descriptor = new AnakinEditCardCommand.EditCardDescriptor();
        descriptor.setQuestion(card.getQuestion());
        descriptor.setAnswer(card.getAnswer());
    }

    /**
     * Sets the {@code Answer} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withAnswer(String ans) {
        descriptor.setAnswer(new AnakinAnswer(ans));
        return this;
    }

    /**
     * Sets the {@code Question} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withQuestion(String question) {
        descriptor.setQuestion(new AnakinQuestion(question));
        return this;
    }


    public EditCardDescriptor build() {
        return descriptor;
    }
}
