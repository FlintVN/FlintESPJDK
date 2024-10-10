package machine.gpio;

public class Pin {
    final int pin;

    private static native void setMode0(int pin, int mode);
    public static native boolean readPin(int pin);
    public static native void writePin(int pin, boolean level);

    public static void setMode(int pin, PinMode mode) {
        if(mode == PinMode.INPUT)
            Pin.setMode0(pin, 0);
        else if(mode == PinMode.OUTPUT)
            Pin.setMode0(pin, 1);
        else if(mode == PinMode.INPUT_PULL_UP)
            Pin.setMode0(pin, 2);
        else if(mode == PinMode.INPUT_PULL_DOWN)
            Pin.setMode0(pin, 3);
        else if(mode == PinMode.OUTPUT_OPEN_DRAIN)
            Pin.setMode0(pin, 4);
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
