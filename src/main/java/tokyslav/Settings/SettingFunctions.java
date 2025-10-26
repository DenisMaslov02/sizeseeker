package tokyslav.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.JSONArray;
import org.json.JSONObject;

public class SettingFunctions {
    public void createJSONFile() {
        File file = new File("src\\main\\java\\tokyslav\\Settings");
        Path settingsDir = file.toPath();
        try {
            Files.createDirectories(settingsDir);
            Path jsonFile = settingsDir.resolve("settings.json");
            if (Files.notExists(jsonFile)) {
                Files.write(jsonFile, createJSONArray().toString().getBytes(StandardCharsets.UTF_8));
            } else {
                readJSONFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readJSONFile() {

        // JSONObject jsonObject = new JSONObject();

        File file = new File("src\\main\\java\\tokyslav\\Settings\\settings.json");
        Path jsonFile = file.toPath();
        try {
            if (Files.exists(jsonFile)) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(Files.readString(jsonFile, StandardCharsets.UTF_8));
                System.out.println("JSON Content: " + jsonArray);
            } else {
                System.out.println("settings.json file does not exist.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray createJSONArray() {

        JSONArray jsonArraySettings = new JSONArray();

        jsonArraySettings.put(Boolean.TRUE);
        jsonArraySettings.put("Setting");

        JSONArray jsonArrayDefault = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Display Resolution:", "1920x1080");
        jsonObject.put("Background Color:", "#FFFFFF");

        jsonArrayDefault.put(jsonObject);

        jsonArraySettings.put(jsonArrayDefault);

        return jsonArraySettings;
    }
}
