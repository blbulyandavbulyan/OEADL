package entities;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

@OEADLProcessingClass
public class ClassContainsCollection {
    @OEADLField
    private Collection<Object> collection;
    @OEADLField(editable = false)
    private String whatIsThis = "dadd";
    @OEADLField
    private String testField = "2";
    @OEADLField(editable = false)
    private String testfield2 = "1243";
//    @OEADLField
//    private String nullString;
    @OEADLField
    private HashMap<String, Object> testMap = new HashMap();
//    @OEADLField
//    private int testPrimitiveValue;


    public ClassContainsCollection() {
        collection = new LinkedList<>();
        collection.add("dada");
        collection.add("nono");
        collection.add(new User(1L, "david", "davidblbulyan@gmail.com", new Group(1L, 2, "users", false)));
        collection.add(1);
        collection.add(new Group(1L, 30, "admins", true));
        collection.add(2);
        for (int i = 0; i < 100; i++)collection.add(i);
        testMap.put("david", "test123");
        testMap.put("admin", "test1234");
        testMap.put("testUser", new User(32L, "noadmin", "test@gmail.com", new Group(234L, 23, "users", false)));
    }
}
