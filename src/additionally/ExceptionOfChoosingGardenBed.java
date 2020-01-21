package additionally;

public class ExceptionOfChoosingGardenBed extends Exception {
    public ExceptionOfChoosingGardenBed() {
        super("\n" + "Нельзя отправлять коротышку на грядку, в которой он уже находится!!!");
    }
}
