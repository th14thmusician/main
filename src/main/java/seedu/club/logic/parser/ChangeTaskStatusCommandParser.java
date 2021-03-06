package seedu.club.logic.parser;
//@@author yash-chowdhary
import static java.util.Objects.requireNonNull;
import static seedu.club.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.club.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.club.commons.core.index.Index;
import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.logic.commands.ChangeTaskStatusCommand;
import seedu.club.logic.parser.exceptions.ParseException;
import seedu.club.model.task.Status;

/**
 * Parses input arguments and creates a new ChangeTaskStatus object
 */
public class ChangeTaskStatusCommandParser implements Parser<ChangeTaskStatusCommand> {

    @Override
    public ChangeTaskStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ChangeTaskStatusCommand.MESSAGE_USAGE));
        }

        try {
            Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS)).get();
            return new ChangeTaskStatusCommand(index, status);
        } catch (IllegalValueException ive) {
            throw new ParseException(ive.getMessage(), ive);
        }
    }
}
