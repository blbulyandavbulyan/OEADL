package tests;

import classesfordisplay.Group;
import classesfordisplay.User;
import org.blbulyandavbulyan.oeadl.displayer.dialogs.ObjectDisplayerDialog;

import javax.swing.*;
import java.util.Properties;

public class Test1 {
    public static void main(String[] args) {
        User user = new User(1L, "testuser", "testuser@gmail.com", new Group(1L, 200, "admins", false));
        Properties properties = new Properties();
        properties.put("userId", "ИД");
        properties.put("userEmail", "электронная почта");
        properties.put("userName", "имя пользователя");
        properties.put("group", "Группа");
        Integer integer = 13;
        ObjectDisplayerDialog objectDisplayerDialog = new ObjectDisplayerDialog(User.class, user, properties::getProperty);
        objectDisplayerDialog.setVisible(true);
        objectDisplayerDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
