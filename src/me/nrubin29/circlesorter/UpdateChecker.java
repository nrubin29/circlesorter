package me.nrubin29.circlesorter;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

class UpdateChecker implements Runnable {

    private static final String VERSION = "1.0";

    private JFrame frame;

    public UpdateChecker(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        String remoteVersion, information;

        try {
            URL url = new URL("https://github.com/nrubin29/circlesorter/raw/master/version.html?raw=true");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            remoteVersion = in.readLine();
            StringBuilder infoBuilder = new StringBuilder();
            while (in.ready()) infoBuilder.append(in.readLine());
            information = infoBuilder.toString();

            in.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (!remoteVersion.equals(VERSION)) {
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Update discovered! Changelog:\n" + information + "\nDownloading and quitting. Please reopen when the update is installed.");
            try {
                URL url = new URL("https://github.com/nrubin29/circlesorter/raw/master/circlesorter.jar?raw=true");
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()));
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();
                rbc.close();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}