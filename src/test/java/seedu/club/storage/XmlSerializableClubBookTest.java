package seedu.club.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.commons.util.FileUtil;
import seedu.club.commons.util.XmlUtil;
import seedu.club.model.ClubBook;
import seedu.club.model.util.SampleDataUtil;
import seedu.club.testutil.MemberUtil;
import seedu.club.testutil.TypicalMembers;
import seedu.club.testutil.TypicalPolls;

public class XmlSerializableClubBookTest {

    private static final String TEST_DATA_FOLDER = FileUtil.getPath("src/test/data/XmlSerializableClubBookTest/");
    private static final File TYPICAL_MEMBERS_FILE = new File(TEST_DATA_FOLDER + "typicalMembersClubBook.xml");
    private static final File INVALID_MEMBER_FILE = new File(TEST_DATA_FOLDER + "invalidMemberClubBook.xml");
    private static final File TYPICAL_POLLS_FILE = new File(TEST_DATA_FOLDER + "typicalPollsClubBook.xml");
    private static final File INVALID_POLL_FILE = new File(TEST_DATA_FOLDER + "invalidPollClubBook.xml");
    private static final File INVALID_TAG_FILE = new File(TEST_DATA_FOLDER + "invalidTagClubBook.xml");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalMembersFile_success() throws Exception {
        XmlSerializableClubBook dataFromFile = XmlUtil.getDataFromFile(TYPICAL_MEMBERS_FILE,
                XmlSerializableClubBook.class);
        ClubBook clubBookFromFile = dataFromFile.toModelType();
        ClubBook typicalMembersClubBook = TypicalMembers.getTypicalClubBook();
        assertEquals(clubBookFromFile, typicalMembersClubBook);
    }

    @Test
    public void toModelType_invalidMemberFile_throwsIllegalValueException() throws Exception {
        XmlSerializableClubBook dataFromFile = XmlUtil.getDataFromFile(INVALID_MEMBER_FILE,
                XmlSerializableClubBook.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_invalidTagFile_throwsIllegalValueException() throws Exception {
        XmlSerializableClubBook dataFromFile = XmlUtil.getDataFromFile(INVALID_TAG_FILE,
                XmlSerializableClubBook.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_typicalPollsFile_success() throws Exception {
        XmlSerializableClubBook dataFromFile = XmlUtil.getDataFromFile(TYPICAL_POLLS_FILE,
                XmlSerializableClubBook.class);
        ClubBook clubBookFromFile = dataFromFile.toModelType();
        ClubBook typicalPollsClubBook = TypicalPolls.getTypicalClubBook();
        assertEquals(clubBookFromFile, typicalPollsClubBook);
    }

    @Test
    public void toModelType_invalidPollFile_throwsIllegalValueException() throws Exception {
        XmlSerializableClubBook dataFromFile = XmlUtil.getDataFromFile(INVALID_POLL_FILE,
                XmlSerializableClubBook.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void equals() throws Exception {
        XmlSerializableClubBook xmlSerializableClubBookOne = new XmlSerializableClubBook(SampleDataUtil.getSampleClubBook());
        XmlSerializableClubBook xmlSerializableClubBookTwo = new XmlSerializableClubBook(SampleDataUtil.getSampleClubBook());
        XmlSerializableClubBook xmlSerializableClubBookThree = new XmlSerializableClubBook();

        assertEquals(xmlSerializableClubBookOne, xmlSerializableClubBookOne);
        assertEquals(xmlSerializableClubBookOne, xmlSerializableClubBookTwo);

        assertNotEquals(xmlSerializableClubBookOne, xmlSerializableClubBookThree);
    }
}
