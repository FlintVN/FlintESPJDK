package machine.gpio;

public class Pin {
    final int pin;

    private static native void setMode(int pin, int mode);
    private static native boolean readPin(int pin);
    private static native void writePin(int pin, boolean level);

    public Pin(int pin) {
        this.pin = pin;
    }

    public Pin(int pin, int mode) {
        this.pin = pin;
        setMode(pin, mode);
    }

    public Pin setMode(PinMode mode) {
        int m = switch(mode) {
            case INPUT -> 0;
            case OUTPUT -> 1;
            case INPUT_PULL_UP -> 2;
            case INPUT_PULL_DOWN -> 3;
            default -> 4;
        };
        Pin.setMode(pin, m);
        return this;
    }

    public boolean read() {
        return Pin.readPin(pin);
    }

    public void write(boolean level) {
        Pin.writePin(pin, level);
    }

    public void set() {
        Pin.writePin(pin, true);
    }

    public void reset() {
        Pin.writePin(pin, false);
    }
}
