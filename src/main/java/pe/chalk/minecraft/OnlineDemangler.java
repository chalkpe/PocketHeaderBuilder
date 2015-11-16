package pe.chalk.minecraft;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author ChalkPE <chalkpe@gmail.com>
 * @since 2015-11-16
 */
public class OnlineDemangler {
    private OnlineDemangler(){}

    public static String demangle(String input) throws IOException {
        final URL demanglerUrl = new URL("http://demangler.com/");
        HttpURLConnection connection = (HttpURLConnection) demanglerUrl.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
    }
}
