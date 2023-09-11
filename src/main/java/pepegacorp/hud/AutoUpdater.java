package pepegacorp.hud;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class AutoUpdater {
    static int version = 1;

    private static String currentVersion = "HUD-1."+version+"-SNAPSHOT.jar"; // Replace with your app's current version
    private static String jarUrl = "https://github.com/e-ed/virtual-keyboard/releases/download/release/HUD-1."+(++version)+"-SNAPSHOT.jar"; // Replace with your JAR URL
    
    public static void main(String[] args) {

        checkForUpdate();

    }
    private static void checkForUpdate() {
        try {
            URL url = new URL(jarUrl);
            System.out.println(jarUrl);
           
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          
            connection.setRequestMethod("HEAD");
   

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                           

                String remoteVersion = connection.getHeaderField("Content-Disposition");
                if (remoteVersion != null && !remoteVersion.isEmpty()) {
                    //remoteVersion = remoteVersion.replace("attachment; filename=\"", "").replace("\"", "").split("HUD-")[1];
                    remoteVersion = remoteVersion.replace("attachment; filename=", "").replace("\"", "");

                    System.out.println(remoteVersion);

                    if (!currentVersion.equals(remoteVersion)) {
                        // Download and replace the JAR file
                        downloadAndReplaceJar();
                        currentVersion = remoteVersion;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadAndReplaceJar() {
        try {
            JOptionPane.showMessageDialog(null, "Downloading new version!! owo pls wait");
            URL url = new URL(jarUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (InputStream in = connection.getInputStream(); FileOutputStream out = new FileOutputStream(currentVersion)) { // Replace with your JAR file name
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Done, new version is " + currentVersion);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
