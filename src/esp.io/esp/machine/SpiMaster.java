package esp.machine;

import java.io.IOException;
import flint.machine.SpiDataMode;
import flint.machine.SpiMasterInterface;

public class SpiMaster implements SpiMasterInterface {
    private int spiId;
    private int mode;
    private int speed;
    private int maxTransferSize;
    private int mosiPin;
    private int misoPin;
    private int clkPin;
    private int csPin;

    private static int getSpiId(SpiDevice spi) {
        return switch(spi) {
            case SPI1 -> 0;
            case SPI2 -> 1;
            default -> 2;
        };
    }

    private native void initInstance();

    public SpiMaster(SpiDevice spi) {
        this.spiId = getSpiId(spi);
        initInstance();
    }

    @Override
    public native void open() throws IOException;

    @Override
    public native void close() throws IOException;

    @Override
    public native boolean isOpen();

    private void checkStateBeforeConfig() {
        if(isOpen())
            throw new IllegalStateException();
    }

    public native int getSpeed() throws IOException;

    public void setSpeed(int speed) {
        checkStateBeforeConfig();
        this.speed = speed;
    }

    public int getMaxTransferSize() {
        return this.maxTransferSize;
    }

    public void setMaxTransferSize(int value) {
        checkStateBeforeConfig();
        this.maxTransferSize = value;
    }

    public SpiDataMode getDataMode() {
        return switch(this.mode & 0x07) {
            case 0 -> SpiDataMode.MSB_MODE0;
            case 1 -> SpiDataMode.MSB_MODE1;
            case 2 -> SpiDataMode.MSB_MODE2;
            case 3 -> SpiDataMode.MSB_MODE3;
            case 4 -> SpiDataMode.LSB_MODE0;
            case 5 -> SpiDataMode.LSB_MODE1;
            case 6 -> SpiDataMode.LSB_MODE2;
            default -> SpiDataMode.LSB_MODE3;
        };
    }

    public void setDataMode(SpiDataMode dataMode) {
        checkStateBeforeConfig();
        int m = switch(dataMode) {
            case MSB_MODE0 -> 0;
            case MSB_MODE1 -> 1;
            case MSB_MODE2 -> 2;
            case MSB_MODE3 -> 3;
            case LSB_MODE0 -> 4;
            case LSB_MODE1 -> 5;
            case LSB_MODE2 -> 6;
            default -> 7;
        };
        this.mode = (this.mode & 0xFFFFFFF8) | m;
    }

    public boolean getCsLevel() {
        return (this.mode & 0x08) != 0;
    }

    public void setCsLevel(boolean level) {
        checkStateBeforeConfig();
        if(level)
            this.mode |= 0x08;
        else
            this.mode &= ~0x08;
    }

    public int getMosiPin() {
        return this.mosiPin;
    }

    public void setMosiPin(int pin) {
        checkStateBeforeConfig();
        this.mosiPin = pin;
    }

    public int getMisoPin() {
        return this.misoPin;
    }

    public void setMisoPin(int pin) {
        checkStateBeforeConfig();
        this.misoPin = pin;
    }

    public int getClkPin() {
        return this.clkPin;
    }

    public void setClkPin(int pin) {
        checkStateBeforeConfig();
        this.clkPin = pin;
    }

    public int getCsPin() {
        return this.csPin;
    }

    public void setCsPin(int pin) {
        checkStateBeforeConfig();
        this.csPin = pin;
    }

    @Override
    public native int read() throws IOException;

    @Override
    public int read(byte[] b) throws IOException {
        return readWrite(null, 0, b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int count) throws IOException {
        return readWrite(null, 0, b, off, count);
    }

    @Override
    public native void write(int b) throws IOException;

    @Override
    public void write(byte[] b) throws IOException {
        readWrite(b, 0, null, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int count) throws IOException {
        readWrite(b, off, null, 0, count);
    }

    @Override
    public int readWrite(byte[] tx, byte[] rx, int count) throws IOException {
        return readWrite(tx, 0, rx, 0, count);
    }

    @Override
    public native int readWrite(byte[] tx, int txOff, byte[] rx, int rxOff, int count) throws IOException;
}
