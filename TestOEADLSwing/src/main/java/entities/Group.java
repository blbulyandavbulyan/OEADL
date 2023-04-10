package entities;

import org.blbulyandavbulyan.oeadl.annotations.OEADLField;
import org.blbulyandavbulyan.oeadl.annotations.OEADLProcessingClass;

@OEADLProcessingClass
public class Group {
    @OEADLField(localizedNamePropertyKey = "id")
    private Long id;
    @OEADLField(localizedNamePropertyKey = "rank")
    private Integer rank;
    @OEADLField(localizedNamePropertyKey = "name")
    private String name;
    @OEADLField(localizedNamePropertyKey = "banned")
    private boolean banned;

    public Group(Long id, Integer rank, String name, boolean banned) {
        this.id = id;
        this.rank = rank;
        this.name = name;
        this.banned = banned;
    }
}
