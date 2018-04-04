package seedu.club.logic.commands;
//@@author yash-chowdhary
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.club.commons.core.Messages;
import seedu.club.logic.commands.exceptions.CommandException;
import seedu.club.model.task.exceptions.TasksAlreadyListedException;

/**
 * Lists all tasks of the currently logged-in member in the club book.
 */
public class ViewMyTasksCommand extends Command {
    public static final String  COMMAND_WORD = "viewmytasks";
    public static final ArrayList<String> COMMAND_ALIASES = new ArrayList<>(
            Arrays.asList(COMMAND_WORD, "mytasks")
    );

    public static final String MESSAGE_SUCCESS = "Listed all your tasks.";
    public static final String MESSAGE_ALREADY_LISTED = "All your tasks are already listed.";

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(model);
        try {
            if (requireToSignUp()) {
                return new CommandResult(Messages.MESSAGE_REQUIRE_SIGN_UP);
            } else if (requireToLogIn()) {
                return new CommandResult(Messages.MESSAGE_REQUIRE_LOG_IN);
            }
            model.viewMyTasks();
        } catch (TasksAlreadyListedException tale) {
            throw new CommandException(MESSAGE_ALREADY_LISTED);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
