package esp.zero;

import esp.machine.*;

public class Led {
    private Pin pin;

    public Led(int gpio)
    {
        this.pin = new Pin(gpio, PinMode.OUTPUT);
    }

    public Led(int gpio, PinMode mode)
    {
        this.pin = new Pin(gpio, mode);
    }

    public void on()
    {
        pin.set();
    }

    public void off()
    {
        pin.reset();
    }

    public void toggle() 
    {
        try {
            Thread.sleep(500);
            on();
            Thread.sleep(500);
            off();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("toogle toggle exception");
        }
    }
    public void toggle(int millis) 
    {
        try {
            Thread.sleep(millis);
            on();
            Thread.sleep(millis);
            off();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("toogle toggle exception");
        }
    }
    



}
