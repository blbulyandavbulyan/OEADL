package entities;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

@OEADLProcessingClass
public class ClassUsesWrappedArrays {
    @OEADLField
    Integer[] primitiveIntArray = {203, 204, 20203, 90};
    @OEADLField
    Long[] primitiveLongArray = {20344L, 1L, 2L, 3L, 304049L, 3049L};
    @OEADLField
    Short[] primitiveShortArray = {23, 2000, 3049, 30930};
    @OEADLField
    Float[] primitiveFloatArray = {2.3F, 2.4F, 2.5F};
    @OEADLField
    Double[] primitiveDoubleArray = {2.455, 3232.34};
    @OEADLField
    Byte[] primitiveByteArray = {12, 20, -20};
    @OEADLField
    Boolean[] primitiveBooleanArray = {false, true, false, true};
    @OEADLField
    Character[] primitiveCharArray = {'a', 'c', 'b', 'c', 'd'};
    String[] testString = {"blalaaaa", "dad", "dadewaeew"};
}
