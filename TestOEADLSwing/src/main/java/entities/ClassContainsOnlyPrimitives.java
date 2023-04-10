package entities;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

@OEADLProcessingClass
public class ClassContainsOnlyPrimitives {
    //int.class, long.class, short.class, float.class, double.class, byte.class, boolean.class, char.class,
    @OEADLField
    int primitiveInt = 203;
    @OEADLField
    long primitiveLong = 20344L;
    @OEADLField
    short primitiveShort = 23;
    @OEADLField
    float primitiveFloat = 2.3F;
    @OEADLField
    double primitiveDouble = 2.455;
    @OEADLField
    byte primitiveByte = 102;
    @OEADLField
    boolean primitiveBoolean = false;
    @OEADLField
    char primitiveChar = 'c';
    String testString = "blalaaaa";
}
