package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.ObservableList;
import main.Main;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Helper Class used for writing to and reading from the data.json file.
 */
public class JsonHelper {

    private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    /**
     * Method used to write all the current Game Data into the data.json file.
     * @param gameData object of class GameData containing information to be written into data.json file
     */
    public static void write(GameData gameData) {
        var gameDataList = read();
        File file = new File("data.json");
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            gameDataList.add(gameData);
            Logger.debug("Writing game data list to Json file {}",gameDataList);
            Logger.debug(file.toURI());
            Logger.debug(file.toString());
        try {
            writer.writeValue(file, gameDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.debug(file.toString());
    }

    /**
     * Used to read the data.json file.
     * @return the data.json File
     */
    public static List<GameData> read() {
        File file = new File("data.json");
        List<GameData> gameDataList = new ArrayList<>();

        try {
            if (file.length() != 0) {
                gameDataList = mapper.readValue(file, new TypeReference<List<GameData>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.error(e.getMessage());
        }
        return gameDataList;

    }

}

