package machine.gpio;

public class PinMode {
    private PinMode() {

    }

    public static final int INPUT = 0;
    public static final int OUTPUT = 1;
    public static final int INPUT_PULL_UP = 2;
    public static final int INPUT_PULL_DOWN = 3;
    public static final int OUTPUT_OPEN_DRAIN = 4;
}
