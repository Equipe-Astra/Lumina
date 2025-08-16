package Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeminiService {

    public static String enviarPrompt(String prompt) throws IOException {
        String apiKey = "AIzaSyAWvJrtILTQAfhm4_PSnT7eSZeKNo8meRY";
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key="+apiKey;

        String jsonInputString = "{\n" +
                "  \"contents\": [\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"parts\": [\n" +
                "        { \"text\": \"" + prompt.replace("\"", "\\\"") + "\" }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int status = connection.getResponseCode();
        InputStream inputStream = (status < 400) ? connection.getInputStream() : connection.getErrorStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }
}
