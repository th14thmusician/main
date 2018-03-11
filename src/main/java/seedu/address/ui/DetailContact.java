package seedu.address.ui;

import com.google.common.eventbus.Subscribe;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.Person;


import java.net.URL;
import java.util.logging.Logger;

/**
 * An UI component that displays more detailed information of a {@code Person}.
 */
public class DetailContact extends UiPart<Region> {

    private static final String FXML = "MemberListDetails.fxml";
    private final Logger logger = LogsCenter.getLogger(this.getClass());

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label image;
    @FXML
    private Label phoneTitle;
    @FXML
    private Label emailTitle;
    @FXML
    private Label groupTitle;
    @FXML
    private Label addressTitle;
    @FXML
    private Label group;

    public DetailContact(){
        super(FXML);
        loadDefaultPage();
        registerAsAnEventHandler(this);
    }

    public void loadMemberDetails(Person person){
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        image.setText("Insert contact picture");
        phoneTitle.setText("Phone:");
        emailTitle.setText("Email:");
        addressTitle.setText("Address:");
        tags.getChildren().clear();
        groupTitle.setText("Group:");
        person.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
    @Subscribe
    private void handlePersonPanelSelectionChangedEvent(PersonPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadMemberDetails(event.getNewSelection().person);
    }

    private void loadDefaultPage() {
        name.setText("");
        phone.setText("");
        address.setText("");
        email.setText("");
        image.setText("");
        phoneTitle.setText("");
        emailTitle.setText("");
        groupTitle.setText("");
        addressTitle.setText("");
        group.setText("");
    }



}
