//@@author amrut-prabhu
package seedu.club.storage;

import static seedu.club.commons.core.Messages.MESSAGE_INVALID_PHOTO_PATH;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import seedu.club.commons.core.LogsCenter;
import seedu.club.commons.exceptions.PhotoException;

/**
 * To copy the profile photo to this application's resources.
 */
public class ProfilePhotoStorage implements  PhotoStorage {

    private static final String FILE_EXTENSION = ".png";
    private static final String SAVE_PHOTO_DIRECTORY = "pictures/";

    private static final String URL_PREFIX = "file:///";

    private static final Logger logger = LogsCenter.getLogger(ProfilePhotoStorage.class);

    @Override
    public void readOriginalPhotoFile(String originalPhotoPath, String newPhotoName)
            throws PhotoException {

        BufferedImage originalPhoto = null;

        try {
            logger.info("Profile Photo is being read from " + originalPhotoPath);

            URL photoUrl = new URL(URL_PREFIX + originalPhotoPath);
            originalPhoto = ImageIO.read(photoUrl);

            String saveAs = newPhotoName + FILE_EXTENSION;
            File newPath = new File(SAVE_PHOTO_DIRECTORY, saveAs);

            createPhotoFileCopy(originalPhoto, newPath);
        } catch (IOException ioe) {
            logger.info("Error while reading photo file");
            throw new PhotoException(String.format(MESSAGE_INVALID_PHOTO_PATH, originalPhotoPath));
        }
    }

    @Override
    public void createPhotoFileCopy(BufferedImage originalPhoto, File newPath) throws IOException {
        try {
            logger.info("Profile Photo is being copied to " + newPath);

            ImageIO.write(originalPhoto, "png", newPath);
        } catch (IOException ioe) {
            logger.info("Error while copying photo file");
            assert false : "Photo copy creation should not fail.";
        }
    }
}
