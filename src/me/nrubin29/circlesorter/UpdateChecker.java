package me.nrubin29.circlesorter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

class UpdateChecker implements Runnable {

    private static final String VERSION = "0.1";

    @Override
    public void run() {
        String remoteVersion;

        try {
            URL url = new URL("http://162.243.229.150/version.html");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            remoteVersion = in.readLine();
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (!remoteVersion.equals(VERSION)) {
            try {
                URL url = new URL("http://162.243.229.150/circlesorter.jar");
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile()));
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();
                rbc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}