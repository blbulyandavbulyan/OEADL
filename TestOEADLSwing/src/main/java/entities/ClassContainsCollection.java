package entities;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

import java.util.Collection;
import java.util.LinkedList;

@OEADLProcessingClass
public class ClassContainsCollection {
    @OEADLField
    private Collection<Object> collection;

    public ClassContainsCollection() {
        collection = new LinkedList<>();
        collection.add("dada");
        collection.add("nono");
        collection.add(new User(1L, "david", "davidblbulyan@gmail.com", new Group(1L, 2, "users", false)));
        collection.add(1);
        collection.add(new Group(1L, 30, "admins", true));
    }
}
