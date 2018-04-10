package seedu.club.ui;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import org.fxmisc.easybind.EasyBind;
import seedu.club.MainApp;
import seedu.club.commons.core.LogsCenter;
import seedu.club.commons.events.ui.MemberPanelSelectionChangedEvent;
import seedu.club.commons.events.ui.SendEmailRequestEvent;
import seedu.club.commons.events.ui.TaskPanelSelectionChangedEvent;
import seedu.club.model.email.Client;
import seedu.club.model.member.Member;
import seedu.club.model.task.Task;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {
    public static final String GMAIL_URL = "https://mail.google.com/mail/?view=cm&fs=1&to=%1$s"
            + "&su=%2$s&body=%3$s";
    public static final String OUTLOOK_URL = "https://outlook.office.com/?path=/mail/action/"
            + "compose&to=%1$s&subject=%2$s&body=%3$s";

    private static final String FXML = "MemberDetailsPanel.fxml";

    private static final Integer PHOTO_WIDTH = 130;
    private static final Integer PHOTO_HEIGHT = 152;
    private static final String DEFAULT_PHOTO = "/images/defaultProfilePhoto.png";
    private static final String PHONE_ICON = "/images/phone_icon.png";
    private static final String EMAIL_ICON = "/images/email_icon.png";
    private static final String EMPTY_STRING = "";
    private static final String[] TAG_COLORS = {"red", "yellow", "grey", "brown", "pink", "white",
                                                "orange", "blue", "violet"};

    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @FXML
    private WebView browser;
    @FXML
    private Label name;
    @FXML
    private ImageView profilePhoto;
    @FXML
    private Label phone;
    @FXML
    private Label matricNumber;
    @FXML
    private Label group;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView phoneIcon;
    @FXML
    private ImageView emailIcon;
    @FXML
    private ListView<ModifiedTaskCard> taskListView;


    public BrowserPanel(ObservableList<Task> taskList) {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);
        registerAsAnEventHandler(this);
    }

    //@@author yash-chowdhary
    /**
     * Loads the client page based on {@code client}
     */
    private void callClient(String client, String recipients, String subject, String body) {
        if (client.equalsIgnoreCase(Client.VALID_CLIENT_GMAIL)) {
            String gMailUrl = String.format(GMAIL_URL, recipients, subject, body);
            loadGmailPage(gMailUrl);
        } else if (client.equalsIgnoreCase(Client.VALID_CLIENT_OUTLOOK)) {
            String outlookUrl = String.format(OUTLOOK_URL, recipients, subject, body);
            loadOutlookPage(outlookUrl);
        }
    }

    /**
     * loads the 'Compose Email' page based on the {@code outlookUrl} in Outlook
     * adapted from https://www.codeproject.com/Questions/398241/how-to-open-url-in-java
     */
    private void loadOutlookPage(String outlookUrl) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(URI.create(outlookUrl));
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * loads the 'Compose Email' page based on the {@code gMailUrl} in GMail
     * adapted from https://www.codeproject.com/Questions/398241/how-to-open-url-in-java
     */
    private void loadGmailPage(String gMailUrl) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(URI.create(gMailUrl));
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setConnections(ObservableList<Task> taskList) {
        setMemberListView(taskList);
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        taskListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in task list panel changed to : '" + newValue + "'");
                        raise(new TaskPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    public void setMemberListView(ObservableList<Task> taskList) {
        ObservableList<TaskCard> mappedList = EasyBind.map(
                taskList, (task) -> new TaskCard(task, taskList.indexOf(task) + 1));
        taskListView.setItems(mappedList);
        taskListView.setCellFactory(listView -> new TaskListPanel.TaskListViewCell());
    }
    //@@author

    //@@author th14thmusician
    /**
     * Loads the details of member into a new panel with more details
     * @param member
     */
    private void loadMemberPage(Member member) {
        name.setText(member.getName().fullName);
        setProfilePhoto(member);
        phone.setText(member.getPhone().value);
        matricNumber.setText(member.getMatricNumber().value);
        email.setText(member.getEmail().value);
        group.setText(member.getGroup().groupName);
        group.setAlignment(Pos.CENTER);
        createTags(member);
        setIcons();
    }

    /**
     * Set Icon pictures
     */
    private void setIcons() {
        Image phoneImg;
        Image emailImg;
        phoneImg = new Image(MainApp.class.getResourceAsStream(PHONE_ICON),
                21, 21, false, true);
        phoneIcon.setImage(phoneImg);
        emailImg = new Image(MainApp.class.getResourceAsStream(EMAIL_ICON),
                21, 21, false, true);
        emailIcon.setImage(emailImg);
    }
    //@@author

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    @Subscribe
    private void handleMemberPanelSelectionChangedEvent(MemberPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadMemberPage(event.getNewSelection().member);
    }

    //@@author yash-chowdhary
    @Subscribe
    private void handleSendingEmailEvent(SendEmailRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event, "Sending email via "
                + event.getClient().toString()));
        callClient(event.getClient().toString(), event.getRecipients(), event.getSubject().toString(),
                event.getBody().toString());
    }

    //@@author amrut-prabhu
    /**
     * Sets the profile photo to the displayed photo shape.
     */
    private void setProfilePhoto(Member member) {
        Image photo;
        String photoPath = member.getProfilePhoto().getPhotoPath();
        if (photoPath.equals(EMPTY_STRING)) {
            photo = new Image(MainApp.class.getResourceAsStream(DEFAULT_PHOTO),
                    PHOTO_WIDTH, PHOTO_HEIGHT, false, true);
        } else {
            try {
                InputStream photoStream = MainApp.class.getResourceAsStream(photoPath);
                photo = new Image("file:" + photoPath, PHOTO_WIDTH, PHOTO_HEIGHT, false, false);
            } catch (NullPointerException npe) {
                photo = new Image(MainApp.class.getResourceAsStream("/images/default.png"), //DEFAULT_PHOTO),
                        PHOTO_WIDTH, PHOTO_HEIGHT, false, true);
            }
        }
        profilePhoto.setImage(photo);
    }
    //@@author

    //@@author yash-chowdhary
    /**
     * Creates the labels for tags by randomly generating a color from `TAG_COLORS`
     */
    private void createTags(Member member) {
        tags.getChildren().clear();
        member.getTags().forEach(tag -> {
            Label tagLabel = new Label(tag.tagName);
            tagLabel.getStyleClass().add(returnColor(tag.tagName));
            tags.getChildren().add(tagLabel);
        });
    }

    /**
     * Returns a color chosen uniformly at random from TAG_COLORS
     */
    private String returnColor(String tag) {
        return TAG_COLORS[Math.abs(tag.hashCode()) % TAG_COLORS.length];
    }
    //@@author
}
