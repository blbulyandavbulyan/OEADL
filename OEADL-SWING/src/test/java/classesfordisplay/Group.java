package classesfordisplay;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

@OEADLProcessingClass
public class Group {
    @OEADLField
    private Long id;
    @OEADLField
    private Integer rank;
    @OEADLField
    private String name;
    @OEADLField
    private boolean banned;

    public Group(Long id, Integer rank, String name, boolean banned) {
        this.id = id;
        this.rank = rank;
        this.name = name;
        this.banned = banned;
    }
}
