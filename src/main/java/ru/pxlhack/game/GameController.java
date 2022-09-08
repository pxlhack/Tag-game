package ru.pxlhack.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    @FXML
    private Button firstBtn, secondBtn, thirdBtn;

    @FXML
    private Button fourthBtn, fifthBtn, sixthBtn;

    @FXML
    private Button seventhBtn, eighthBtn, ninthBtn;

    private Button[][] btnMatrix;

    private int[][] matrix;


    private void swapBtnText(int i1, int j1, int i2, int j2) {
        String tmpBtn = btnMatrix[i1][j1].getText();
        btnMatrix[i1][j1].setText(btnMatrix[i2][j2].getText());
        btnMatrix[i2][j2].setText(tmpBtn);
    }


    private void swapInt(int i1, int j1, int i2, int j2) {
        int tmp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }

    private void click(int i, int j) {
        int iStart = 0, iEnd = 3;
        int jStart = 0, jEnd = 3;

        switch (i) {
            case 0 -> iEnd = 2;
            case 2 -> iStart = 1;
        }

        switch (j) {
            case 0 -> jEnd = 2;
            case 2 -> jStart = 1;
        }

        for (int k = iStart; k < iEnd; k++) {
            for (int l = jStart; l < jEnd; l++) {
                if (Math.abs(k - i) != Math.abs(l - j)) {
                    if (matrix[k][l] == 0) {
                        swapInt(i, j, k, l);
                        swapBtnText(i, j, k, l);
                    }
                }
            }
        }


        isFinished();

    }

    private void isFinished() {
        int k = 1;
        boolean flag = true;
        for (int l = 0; l < 3; l++) {
            for (int m = 0; m < 3; m++) {
                if (matrix[l][m] != k % 9) {
                    flag = false;
                    break;
                }
                k++;
            }

        }

        if (flag) {
            sayVictory();
            refreshMatrices();
        }
    }

    private void refreshMatrices() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        Collections.shuffle(list);

        matrix = new int[][]{
                {list.get(0), list.get(1), list.get(2)},
                {list.get(3), list.get(4), list.get(5)},
                {list.get(6), list.get(7), list.get(8)}
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 0) {
                    btnMatrix[i][j].setText("");
                } else {
                    btnMatrix[i][j].setText("" + matrix[i][j]);
                }
            }
        }

    }

    private void sayVictory() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("You won!");
        alert.setContentText("Good game!");
        alert.showAndWait();
    }

    @FXML
    private void firstBtnOnClick() {
        click(0, 0);
    }

    @FXML
    private void secondBtnOnClick() {
        click(0, 1);
    }

    @FXML
    private void thirdBtnOnClick() {
        click(0, 2);
    }

    @FXML
    private void fourthBtnOnClick() {
        click(1, 0);
    }

    @FXML
    private void fifthBtnOnClick() {
        click(1, 1);
    }

    @FXML
    private void sixthBtnOnClick() {
        click(1, 2);
    }

    @FXML
    private void seventhBtnOnClick() {
        click(2, 0);
    }

    @FXML
    private void eighthBtnOnClick() {
        click(2, 1);
    }

    @FXML
    private void ninthBtnOnclick() {
        click(2, 2);
    }

    @FXML
    public void startGameBtnOnClick() {
        refreshMatrices();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnMatrix = new Button[][]{
                {firstBtn, secondBtn, thirdBtn},
                {fourthBtn, fifthBtn, sixthBtn},
                {seventhBtn, eighthBtn, ninthBtn}
        };

        refreshMatrices();

    }


}