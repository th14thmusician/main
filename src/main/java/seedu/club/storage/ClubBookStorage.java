package seedu.club.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.club.commons.exceptions.DataConversionException;
import seedu.club.model.ClubBook;
import seedu.club.model.ReadOnlyClubBook;

/**
 * Represents a storage for {@link ClubBook}.
 */
public interface ClubBookStorage {

    /**
     * Returns the file path of the data file.
     */
    String getAddressBookFilePath();

    /**
     * Returns ClubBook data as a {@link ReadOnlyClubBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyClubBook> readClubBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyClubBook> readClubBook(String filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyClubBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClubBook(ReadOnlyClubBook addressBook) throws IOException;

    /**
     * @see #saveClubBook(ReadOnlyClubBook)
     */
    void saveClubBook(ReadOnlyClubBook addressBook, String filePath) throws IOException;

}
