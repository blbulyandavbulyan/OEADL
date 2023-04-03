package classesfordisplay;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

@OEADLProcessingClass
public class ClassContainsEnum {
    public enum Type {TYPE1, TYPE2, TYPE3};
    @OEADLField
    private Type type;
    @OEADLField
    private String name;
    @OEADLField
    private Integer testInteger = 1;
    public ClassContainsEnum(Type type, String name) {
        this.type = type;
        this.name = name;
    }
}
