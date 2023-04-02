package classesfordisplay;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

import java.util.Collection;
import java.util.LinkedList;

@OEADLProcessingClass
public class ClassContainsCollection {
    @OEADLField
    private Collection<Integer> collection;
    @OEADLField
    private LinkedList<String> testList;

    public ClassContainsCollection(Collection<Integer> collection) {
        this.collection = collection;
        testList = new LinkedList<>();
        testList.add("dada");
        testList.add("nono");
    }
}
