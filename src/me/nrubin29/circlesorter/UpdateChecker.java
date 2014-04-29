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

    static final String VERSION = "1.1"; // TODO: Don't forget to change in every new version!

    private final JFrame frame;
    private final boolean development;

    public UpdateChecker(JFrame frame, boolean development) {
        this.frame = frame;
        this.development = development;
    }

    @Override
    public void run() {
        if (development) return;

        String remoteVersion, information;

        try {
            URL url = new URL("https://github.com/nrubin29/circlesorter/raw/master/version.html?raw=true");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            remoteVersion = in.readLine();
            StringBuilder infoBuilder = new StringBuilder();
            while (in.ready()) infoBuilder.append(in.readLine()).append("\n");
            information = infoBuilder.toString();

            in.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (!VERSION.equals(remoteVersion)) {
            frame.dispose();
            JOptionPane.showMessageDialog(frame, "Update discovered!\n\nHere's what's new:\n" + information + "\nDownloading and quitting. Please reopen when the update is installed.", "Update", JOptionPane.INFORMATION_MESSAGE);
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