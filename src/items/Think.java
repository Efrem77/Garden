package items;

import additionally.ExceptionOfChoosingGardenBed;
import additionally.GardenBed;

public interface Think {
        void think(GardenBed bed) throws ExceptionOfChoosingGardenBed;

}
