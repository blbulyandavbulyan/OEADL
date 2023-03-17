package classesfordisplay;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

@OEADLProcessingClass
public class ClassUsesPrimitiveArrays {
    @OEADLField
    int[] primitiveIntArray = {203, 204, 20203, 90};
    @OEADLField
    long[] primitiveLongArray = {20344L, 1L, 2L, 3L, 304049L, 3049L};
    @OEADLField
    short[] primitiveShortArray = {23, 2000, 3049, 30930};
    @OEADLField
    float[] primitiveFloatArray = {2.3F, 2.4F, 2.5F};
    @OEADLField
    double[] primitiveDoubleArray = {2.455, 3232.34};
    @OEADLField
    byte[] primitiveByteArray = {12, 20, -20};
    @OEADLField
    boolean[] primitiveBooleanArray = {false, true, false, true};
    @OEADLField
    char[] primitiveCharArray = {'a', 'c', 'b', 'c', 'd'};
    String[] testString = {"blalaaaa", "dad", "dadewaeew"};
}
