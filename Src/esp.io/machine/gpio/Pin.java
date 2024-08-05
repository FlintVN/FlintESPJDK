package machine.gpio;

public class Pin {
    public static final int INPUT = 0;
    public static final int OUTPUT = 1;
    public static final int INPUT_PULL_UP = 2;
    public static final int INPUT_PULL_DOWN = 3;
    public static final int OUTPUT_OPEN_DRAIN = 4;

    private int pin;

    public static native void setMode(int pin, int mode);
    public static native boolean readPin(int pin);
    public static native void writePin(int pin, boolean level);

    public Pin(int pin) {
        this.pin = pin;
    }

    public Pin(int pin, int mode) {
        this.pin = pin;
        Pin.setMode(pin, mode);
    }

    public void setMode(int mode) {
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
