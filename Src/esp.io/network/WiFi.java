package network;

public class WiFi {
    private WiFi() {

    }

    public static native boolean isSupported();

    public static native void connect(String ssid, String password, int authMode);
    public static native boolean isConnected();
    public static native AccessPointRecord getAPinfo();
    public static native void disconnect();

    public static native void softAP(String ssid, String password, int authMode, int channel, int maxConnection);
    public static native void softAPdisconnect();

    public static native void startScan(boolean block);
    public static native AccessPointRecord[] getScanResults();
    public static native void stopScan();

    public static void connect(String ssid) {
        WiFi.connect(ssid, null, WiFiAuthMode.OPEN);
    }

    public static void connect(String ssid, String password) {
        WiFi.connect(ssid, password, WiFiAuthMode.WPA2_PSK);
    }

    public static void softAP(String ssid) {
        WiFi.softAP(ssid, null, WiFiAuthMode.OPEN, 1, 5);
    }

    public static void softAP(String ssid, String password) {
        WiFi.softAP(ssid, password, WiFiAuthMode.WPA2_PSK, 1, 5);
    }

    public static void softAP(String ssid, String password, byte authMode) {
        WiFi.softAP(ssid, password, authMode, 0, 5);
    }

    public static synchronized AccessPointRecord[] scanNetworks() {
        WiFi.startScan(true);
        return WiFi.getScanResults();
    }
}
