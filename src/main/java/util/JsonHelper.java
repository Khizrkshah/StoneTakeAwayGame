package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import main.Launcher;
import main.Main;
import org.tinylog.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The Helper Class used for writing to and reading from the data.json file.
 */
public class JsonHelper {

    /**
     * Method used to write all the current Game Data into the data.json file.
     * @param gameData object of class GameData containing information to be written into data.json file
     */
    public static void write(GameData gameData) {
        File file = new File(Main.class.getClassLoader().getResource("data.json").getFile());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            List<GameData> gameDataList = new ArrayList<>();
            if (file.length() != 0) {
                gameDataList = mapper.readValue(file, new TypeReference<List<GameData>>() {
                });
            }
            gameDataList.add(gameData);
            Logger.debug("Writing game data list to Json file {}",gameDataList);
            writer.writeValue(file, gameDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to read the data.json file.
     * @return the data.json File
     */
    public static File read() {
        return new File(Main.class.getClassLoader().getResource("data.json").getFile());
    }
}






/*import org.json.simple.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

public class JsonHelper {

    public InputStream getFileFromResourceAsStream() {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("data.json");

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + "data.json");
        } else {
            return inputStream;
        }
    }

    public void printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printFile(File file) {

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getFileFromResource() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            return new File(classLoader.getResource("data.json").getFile());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void write(LocalDateTime time, String p1, String p2, Integer numberOfMoves, String winner) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", time.toString());
        jsonObject.put("p1", p1);
        jsonObject.put("p2", p2);
        jsonObject.put("numberOfMoves", numberOfMoves);
        jsonObject.put("winner", winner);

        try {
            FileWriter file = new FileWriter(getFileFromResource());
            file.write(jsonObject.toJSONString());
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}*/


