package seedu.club.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.club.commons.exceptions.IllegalValueException;
import seedu.club.model.ClubBook;
import seedu.club.model.ReadOnlyClubBook;

/**
 * An Immutable ClubBook that is serializable to XML format
 */
@XmlRootElement(name = "addressbook")
public class XmlSerializableClubBook {

    @XmlElement
    private List<XmlAdaptedMember> members;
    @XmlElement
    private List<XmlAdaptedTag> tags;

    /**
     * Creates an empty XmlSerializableClubBook.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableClubBook() {
        members = new ArrayList<>();
        tags = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableClubBook(ReadOnlyClubBook src) {
        this();
        members.addAll(src.getPersonList().stream().map(XmlAdaptedMember::new).collect(Collectors.toList()));
        tags.addAll(src.getTagList().stream().map(XmlAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this addressbook into the model's {@code ClubBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedMember} or {@code XmlAdaptedTag}.
     */
    public ClubBook toModelType() throws IllegalValueException {
        ClubBook clubBook = new ClubBook();
        for (XmlAdaptedTag t : tags) {
            clubBook.addTag(t.toModelType());
        }
        for (XmlAdaptedMember m : members) {
            clubBook.addPerson(m.toModelType());
        }
        return clubBook;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableClubBook)) {
            return false;
        }

        XmlSerializableClubBook otherAb = (XmlSerializableClubBook) other;
        return members.equals(otherAb.members) && tags.equals(otherAb.tags);
    }
}
