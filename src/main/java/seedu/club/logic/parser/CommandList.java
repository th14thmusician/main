package seedu.club.logic.parser;

import java.util.ArrayList;
import java.util.Collections;

import seedu.club.logic.commands.AddCommand;
import seedu.club.logic.commands.ChangeProfilePhotoCommand;
import seedu.club.logic.commands.ClearCommand;
import seedu.club.logic.commands.CompressCommand;
import seedu.club.logic.commands.DecompressCommand;
import seedu.club.logic.commands.DeleteCommand;
import seedu.club.logic.commands.DeleteTagCommand;
import seedu.club.logic.commands.EditCommand;
import seedu.club.logic.commands.EmailCommand;
import seedu.club.logic.commands.ExitCommand;
import seedu.club.logic.commands.FindCommand;
import seedu.club.logic.commands.HelpCommand;
import seedu.club.logic.commands.ListCommand;
import seedu.club.logic.commands.LogInCommand;
import seedu.club.logic.commands.RedoCommand;
import seedu.club.logic.commands.RemoveGroupCommand;
import seedu.club.logic.commands.SelectCommand;
import seedu.club.logic.commands.UndoCommand;

/**
 * Stores list of commands
 */
public class CommandList {

    private final ArrayList<String> commandList = new ArrayList<>();

    public ArrayList<String> getCommandList() {
        commandList.add(AddCommand.COMMAND_FORMAT);
        commandList.add(ChangeProfilePhotoCommand.COMMAND_FORMAT);
        commandList.add(ClearCommand.COMMAND_WORD);
        commandList.add(CompressCommand.COMMAND_WORD);
        commandList.add(DecompressCommand.COMMAND_WORD);
        commandList.add(DeleteCommand.COMMAND_FORMAT);
        commandList.add(DeleteTagCommand.COMMAND_FORMAT);
        commandList.add(EditCommand.COMMAND_FORMAT);
        commandList.add(EmailCommand.COMMAND_FORMAT);
        commandList.add(ExitCommand.COMMAND_WORD);
        commandList.add(FindCommand.COMMAND_FORMAT);
        commandList.add(HelpCommand.COMMAND_WORD);
        commandList.add(ListCommand.COMMAND_WORD);
        commandList.add(LogInCommand.COMMAND_FORMAT);
        commandList.add(RedoCommand.COMMAND_WORD);
        commandList.add(RemoveGroupCommand.COMMAND_FORMAT);
        commandList.add(SelectCommand.COMMAND_WORD);
        commandList.add(UndoCommand.COMMAND_WORD);

        Collections.sort(commandList);
        return commandList;
    }
}