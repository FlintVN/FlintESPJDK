package network;

public class AccessPointRecord {
    public final byte[] mac;
    public final String ssid;
    public final byte rssi;
    public final byte authMode;

    public AccessPointRecord(byte[] mac, String ssid, byte rssi, byte authMode) {
        this.mac = mac;
        this.ssid = ssid;
        this.rssi = rssi;
        this.authMode = authMode;
    }
}
