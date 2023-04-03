package classesfordisplay;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

@OEADLProcessingClass
public class User {
//    @OEADLField
//    private int testField;
    @OEADLField(localizedNamePropertyKey = "userId")
    private Long userId;
    @OEADLField
    private char testChar = 'd';

    @OEADLField(localizedNamePropertyKey = "userName")
    private String userName;
    @OEADLField(localizedNamePropertyKey = "userEmail")
    private String userEmail;
    @OEADLField(localizedNamePropertyKey = "group")
    private Group group;
//    @OEADLField
//    String[] strings = {"test1", "test2", "teset3"};
    public User(Long userId, String userName, String userEmail, Group group) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.group = group;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
