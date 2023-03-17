package classesfordisplay;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

@OEADLProcessingClass
public class ClassContainsOnlyWrappers {
    @OEADLField
    Integer wrapperInt = 203;
    @OEADLField
    Long wrapperLong = 20344L;
    @OEADLField
    Short wrapperShort = 23;
    @OEADLField
    Float wrapperFloat = 2.3F;
    @OEADLField
    Double wrapperDouble = 2.455;
    @OEADLField
    Byte wrapperByte = 102;
    @OEADLField
    Boolean wrapperBoolean = false;
    @OEADLField
    Character wrapperChar = 'c';
    @OEADLField
    String testString = "blalaaaa";
}
