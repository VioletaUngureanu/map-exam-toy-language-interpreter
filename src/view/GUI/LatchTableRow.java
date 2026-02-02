package view.GUI;

public class LatchTableRow {
    private final Integer location;
    private final Integer value;

    public LatchTableRow(Integer location, Integer value) {
        this.location = location;
        this.value = value;
    }

    public Integer getLocation() { return location; }
    public Integer getValue() { return value; }
}
