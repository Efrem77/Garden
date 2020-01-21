package additionally;

public class DeathException extends RuntimeException {
    public DeathException() {
        super("Герой потерял слишком много крови, пока ему бежали помогать, земля ему пухом ");
    }
}
