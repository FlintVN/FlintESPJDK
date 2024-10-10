package machine.gpio;

public class Port {
    private final byte[] pins;

    private static native void setMode0(byte[] pins, int mode);
    public static native int readPort(byte[] pins);
    public static native void writePort(byte[] pins, int value);

    public static void setMode(byte[] pins, PinMode mode) {
        if(mode == PinMode.INPUT)
            Port.setMode0(pins, Pin.INPUT);
        else if(mode == PinMode.OUTPUT)
            Port.setMode0(pins, Pin.OUTPUT);
        else if(mode == PinMode.INPUT_PULL_UP)
            Port.setMode0(pins, Pin.INPUT_PULL_UP);
        else if(mode == PinMode.INPUT_PULL_DOWN)
            Port.setMode0(pins, Pin.INPUT_PULL_DOWN);
        else
            Port.setMode0(pins, Pin.OUTPUT_OPEN_DRAIN);
    }

    public Port(byte[] pins) {
        if(pins == null || (pins.length < 1) || (pins.length > 32)) {
            if(pins == null)
                throw new NullPointerException("pins array cannot be null object");
            else
                throw new NullPointerException("The pin number must be from 1 to 32");
        }
        this.pins = pins;
    }

    public Port(Pin[] pins) {
        if(pins == null || (pins.length < 1) || (pins.length > 32)) {
            if(pins == null)
                throw new NullPointerException("pins array cannot be null object");
            else
                throw new NullPointerException("The pin number must be from 1 to 32");
        }
        byte[] pinArray = new byte[pins.length];
        for(int i = 0; i < pins.length; i++)
            pinArray[i] = (byte)pins[i].pin;
        this.pins = pinArray;
    }

    public Port(byte[] pins, PinMode mode) {
        this(pins);
        Port.setMode(pins, mode);
    }

    public Port(Pin[] pins, PinMode mode) {
        this(pins);
        Port.setMode(this.pins, mode);
    }

    public void setMode(PinMode mode) {
        Port.setMode(pins, mode);
    }

    public int readPort() {
        return Port.readPort(pins);
    }

    public void writePort(int value) {
        Port.writePort(pins, value);
    }
}
