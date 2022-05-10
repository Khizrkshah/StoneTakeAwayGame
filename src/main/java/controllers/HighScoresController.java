package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Launcher;
import model.HighScores;
import util.GameData;
import util.JsonHelper;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HighScoresController {

    @FXML
    private TableView highScoresTable;

    @FXML
    private TableColumn<GameData, String> nameColumn;

    @FXML
    private TableColumn<GameData, Long> numberOfWinsColumn;



    @FXML
    void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberOfWinsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        List<GameData> gameDataList;
        ObservableList<HighScores> highScoresObservableList = FXCollections.observableArrayList();

        File file = JsonHelper.read();

        try{
            if(file.length() != 0){
                gameDataList = mapper.readValue(file, new TypeReference<List<GameData>>() {
                });
                Map<String,Long> map = gameDataList.stream()
                        .collect(Collectors.groupingBy(GameData::getWinner,Collectors.counting()));
                map.entrySet().stream()
                        .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                        .limit(5)
                        .forEach(entry -> {
                            HighScores highScores = new HighScores();
                            highScores.setName(entry.getKey());
                            highScores.setNumberOfWins(entry.getValue().longValue());
                            highScoresObservableList.add(highScores);
                        });
            }
            highScoresTable.setItems(highScoresObservableList);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void mainMenuButtonPressed(ActionEvent event) {
        Launcher.highScoresStage.hide();
        Launcher.mainMenuStage.show();

    }

}
