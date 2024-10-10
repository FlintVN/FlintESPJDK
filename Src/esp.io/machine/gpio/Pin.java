package machine.gpio;

public class Pin {
    static final int INPUT = 0;
    static final int OUTPUT = 1;
    static final int INPUT_PULL_UP = 2;
    static final int INPUT_PULL_DOWN = 3;
    static final int OUTPUT_OPEN_DRAIN = 4;

    final int pin;

    private static native void setMode0(int pin, int mode);
    public static native boolean readPin(int pin);
    public static native void writePin(int pin, boolean level);

    public static void setMode(int pin, PinMode mode) {
        if(mode == PinMode.INPUT)
            Pin.setMode0(pin, Pin.INPUT);
        else if(mode == PinMode.OUTPUT)
            Pin.setMode0(pin, Pin.OUTPUT);
        else if(mode == PinMode.INPUT_PULL_UP)
            Pin.setMode0(pin, Pin.INPUT_PULL_UP);
        else if(mode == PinMode.INPUT_PULL_DOWN)
            Pin.setMode0(pin, Pin.INPUT_PULL_DOWN);
        else
            Pin.setMode0(pin, Pin.OUTPUT_OPEN_DRAIN);
    }

    public Pin(int pin) {
        this.pin = pin;
    }

    public Pin(int pin, PinMode mode) {
        this.pin = pin;
        Pin.setMode(pin, mode);
    }

    public void setMode(PinMode mode) {
        Pin.setMode(pin, mode);
    }

    public boolean readPin() {
        return Pin.readPin(pin);
    }

    public void writePin(boolean level) {
        Pin.writePin(pin, level);
    }

    public void setPin() {
        Pin.writePin(pin, true);
    }

    public void resetPin() {
        Pin.writePin(pin, false);
    }
}
