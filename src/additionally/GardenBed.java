package additionally;


public enum GardenBed {
    HEEL(1,"Холм"),
    STRAWBERRY(53, "Грядка клубники"),
    CUCUMBERS(72,"Грядка огурцов"),
    TOMATOES(32,"Грядка помидоров"),
    POTATO(6,"Грядка картошки"),
    TRAP(60,"Капкан"),
    HOUSE(99,"Дом");


    private int xg;
    private String name;



    GardenBed(int xg, String name) {
        this.xg = xg;
        this.name=name;
    }

    public int getXg() {
        return xg;
    }
    public String getName() {
        return name;
    }
}