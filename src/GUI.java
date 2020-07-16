import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class GUI extends Pane {
    Image image1 = new Image("背景.jpg");
    ImageView background = new ImageView(image1);
    Label[] labels = new Label[5];
    TextField[] texts = new TextField[3];
    Button[] buttons = new Button[5];
    //bonus
    Button[] mapButtons = new Button[26];
    int times = 0;
    //bonus
    Button alertButton = new Button("?");
    //bonus
    Button history = new Button("查看历史");
    Alert showHistory = new Alert(Alert.AlertType.INFORMATION);

    GUI() {
        for (int i = 0; i < labels.length; i++)
            labels[i] = new Label();
        for (int i = 0; i < texts.length; i++)
            texts[i] = new TextField();
        for (int i = 0; i < buttons.length; i++)
            buttons[i] = new Button();
        for (int i = 0; i < mapButtons.length; i++)
            mapButtons[i] = new Button();

        background.setFitHeight(500);
        background.setFitWidth(743);
        background.setLayoutX(0);
        background.setLayoutY(0);

        labels[0].setText("Let's go \n              Traveling !");
        labels[0].setLayoutX(750);
        labels[0].setLayoutY(10);
        labels[0].setFont(Font.font(30));

        labels[1].setText("起点： ");
        labels[1].setLayoutX(750);
        labels[1].setLayoutY(140);
        labels[1].setFont(Font.font(21));

        labels[2].setText("终点： ");
        labels[2].setLayoutX(750);
        labels[2].setLayoutY(170);
        labels[2].setFont(Font.font(21));

        labels[4].setText("输出模式： ");
        labels[4].setLayoutX(750);
        labels[4].setLayoutY(110);
        labels[4].setFont(Font.font(21));

        labels[3].setText("注：输出模式1选择起点和终点，在该界面上显示最佳路线与相应时间。" +
                "输出模式2选择起点，在外部文件中得到所有点到该点的最短路径。输出模式3直接得到所有点之间的最佳路线。");//显示最佳路线的部分
        labels[3].setLayoutX(750);
        labels[3].setLayoutY(335);
        labels[3].setFont(Font.font(17));
        labels[3].setMaxWidth(290);
        labels[3].setMaxHeight(190);
        labels[3].setWrapText(true);//自动换行

        texts[0].setLayoutX(860);
        texts[0].setLayoutY(145);
        texts[0].setMinHeight(25);//起点

        texts[1].setLayoutX(860);
        texts[1].setLayoutY(175);
        texts[1].setMinHeight(25);//终点

        texts[2].setLayoutX(860);
        texts[2].setLayoutY(115);
        texts[2].setMinHeight(25);//输出模式

        buttons[0].setLayoutX(920);
        buttons[0].setLayoutY(270);
        buttons[0].setFont(Font.font(25));
        buttons[0].setStyle("-fx-background-radius: 10");
        buttons[0].setText("重置");

        buttons[1].setLayoutX(780);
        buttons[1].setLayoutY(220);
        buttons[1].setFont(Font.font(15));
        buttons[1].setText("走路去");

        buttons[2].setLayoutX(780);
        buttons[2].setLayoutY(255);
        buttons[2].setFont(Font.font(15));
        buttons[2].setText("开车去");

        buttons[3].setLayoutX(780);
        buttons[3].setLayoutY(290);
        buttons[3].setFont(Font.font(15));
        buttons[3].setText("坐公交");

        //bonus
        buttons[4].setLayoutX(900);
        buttons[4].setLayoutY(220);
        buttons[4].setFont(Font.font(20));
        buttons[4].setStyle("-fx-background-radius: 10");
        buttons[4].setText("交换起终点");

        //A处透明按钮
        mapButtons[0].setLayoutX(37);
        mapButtons[0].setLayoutY(159);
        mapButtons[0].setMinSize(50, 50);
        mapButtons[0].setOpacity(0);

        //B处透明按钮
        mapButtons[1].setLayoutX(76);
        mapButtons[1].setLayoutY(365);
        mapButtons[1].setMinSize(50, 50);
        mapButtons[1].setOpacity(0);

        //C处透明按钮
        mapButtons[2].setLayoutX(96);
        mapButtons[2].setLayoutY(466);
        mapButtons[2].setMinSize(50, 50);
        mapButtons[2].setOpacity(0);

        //D处透明按钮
        mapButtons[3].setLayoutX(166);
        mapButtons[3].setLayoutY(113);
        mapButtons[3].setMinSize(50, 50);
        mapButtons[3].setOpacity(0);

        //E处透明按钮
        mapButtons[4].setLayoutX(186);
        mapButtons[4].setLayoutY(178);
        mapButtons[4].setMinSize(50, 50);
        mapButtons[4].setOpacity(0);

        //F处透明按钮
        mapButtons[5].setLayoutX(190);
        mapButtons[5].setLayoutY(257);
        mapButtons[5].setMinSize(50, 50);
        mapButtons[5].setOpacity(0);

        //G处透明按钮
        mapButtons[6].setLayoutX(189);
        mapButtons[6].setLayoutY(336);
        mapButtons[6].setMinSize(50, 50);
        mapButtons[6].setOpacity(0);

        //H处透明按钮
        mapButtons[7].setLayoutX(204);
        mapButtons[7].setLayoutY(450);
        mapButtons[7].setMinSize(50, 50);
        mapButtons[7].setOpacity(0);

        //I处透明按钮
        mapButtons[8].setLayoutX(289);
        mapButtons[8].setLayoutY(41);
        mapButtons[8].setMinSize(50, 50);
        mapButtons[8].setOpacity(0);

        //J处透明按钮
        mapButtons[9].setLayoutX(290);
        mapButtons[9].setLayoutY(138);
        mapButtons[9].setMinSize(50, 50);
        mapButtons[9].setOpacity(0);

        //K处透明按钮
        mapButtons[10].setLayoutX(294);
        mapButtons[10].setLayoutY(228);
        mapButtons[10].setMinSize(50, 50);
        mapButtons[10].setOpacity(0);

        //L处透明按钮
        mapButtons[11].setLayoutX(303);
        mapButtons[11].setLayoutY(316);
        mapButtons[11].setMinSize(50, 50);
        mapButtons[11].setOpacity(0);

        //M处透明按钮
        mapButtons[12].setLayoutX(305);
        mapButtons[12].setLayoutY(421);
        mapButtons[12].setMinSize(50, 50);
        mapButtons[12].setOpacity(0);

        //N处透明按钮
        mapButtons[13].setLayoutX(376);
        mapButtons[13].setLayoutY(21);
        mapButtons[13].setMinSize(50, 50);
        mapButtons[13].setOpacity(0);

        //O处透明按钮
        mapButtons[14].setLayoutX(400);
        mapButtons[14].setLayoutY(100);
        mapButtons[14].setMinSize(50, 50);
        mapButtons[14].setOpacity(0);

        //P处透明按钮
        mapButtons[15].setLayoutX(426);
        mapButtons[15].setLayoutY(181);
        mapButtons[15].setMinSize(50, 50);
        mapButtons[15].setOpacity(0);

        //Q处透明按钮
        mapButtons[16].setLayoutX(463);
        mapButtons[16].setLayoutY(237);
        mapButtons[16].setMinSize(50, 50);
        mapButtons[16].setOpacity(0);

        //R处透明按钮
        mapButtons[17].setLayoutX(419);
        mapButtons[17].setLayoutY(310);
        mapButtons[17].setMinSize(50, 50);
        mapButtons[17].setOpacity(0);

        //S处透明按钮
        mapButtons[18].setLayoutX(512);
        mapButtons[18].setLayoutY(292);
        mapButtons[18].setMinSize(50, 50);
        mapButtons[18].setOpacity(0);

        //T处透明按钮
        mapButtons[19].setLayoutX(477);
        mapButtons[19].setLayoutY(143);
        mapButtons[19].setMinSize(50, 50);
        mapButtons[19].setOpacity(0);

        //U处透明按钮
        mapButtons[20].setLayoutX(545);
        mapButtons[20].setLayoutY(80);
        mapButtons[20].setMinSize(50, 50);
        mapButtons[20].setOpacity(0);

        //V处透明按钮
        mapButtons[21].setLayoutX(621);
        mapButtons[21].setLayoutY(7);
        mapButtons[21].setMinSize(50, 50);
        mapButtons[21].setOpacity(0);

        //W处透明按钮
        mapButtons[22].setLayoutX(677);
        mapButtons[22].setLayoutY(65);
        mapButtons[22].setMinSize(50, 50);
        mapButtons[22].setOpacity(0);

        //X处透明按钮
        mapButtons[23].setLayoutX(624);
        mapButtons[23].setLayoutY(72);
        mapButtons[23].setMinSize(50, 50);
        mapButtons[23].setOpacity(0);

        //Y处透明按钮
        mapButtons[24].setLayoutX(645);
        mapButtons[24].setLayoutY(245);
        mapButtons[24].setMinSize(50, 50);
        mapButtons[24].setOpacity(0);

        //Z处透明按钮
        mapButtons[25].setLayoutX(560);
        mapButtons[25].setLayoutY(270);
        mapButtons[25].setMinSize(50, 50);
        mapButtons[25].setOpacity(0);

        alertButton.setLayoutX(1025);
        alertButton.setLayoutY(0);
        alertButton.setFont(Font.font(14));

        history.setLayoutX(974);
        history.setLayoutY(470);
        history.setFont(Font.font(14));

        this.getChildren().add(background);
        for (int i = 0; i < labels.length; i++)
            this.getChildren().add(labels[i]);
        for (int i = 0; i < texts.length; i++)
            this.getChildren().add(texts[i]);
        for (int i = 0; i < buttons.length; i++)
            this.getChildren().add(buttons[i]);
        for (int i = 0; i < mapButtons.length; i++)
            this.getChildren().add(mapButtons[i]);
        this.getChildren().add(alertButton);
        this.getChildren().add(history);

        buttons[4].setOnMouseClicked(event -> {
            if (texts[0].getText().equals("") || texts[1].getText().equals("")) {
                labels[3].setText("请填写起终点！");
            } else {
                String tmp = texts[0].getText();
                texts[0].setText(texts[1].getText());
                texts[1].setText(tmp);
            }
        });

        alertButton.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("提示信息");
            alert.setHeaderText("地图注意事项：");
            alert.setContentText("单行道：U——V、V——W、W——X、X——U\n车行道：AB、BC\n人行道：PT、TQ、PQ、QR、QS\n公交站点：B F G K L R T Y Z\n步行不受单行道限制。\n公交最佳路线为时间最短路线。" +
                    "\n您可点击地图中的点得到起终点。");
            alert.showAndWait();
        });

        history.setOnMouseClicked(event -> {
            showHistory.setTitle("您的搜索历史");
            showHistory.setHeaderText("您曾搜索了：");
            showHistory.show();
        });

        for (int i = 0; i < mapButtons.length; i++) {
            int finalI = i;
            mapButtons[i].setOnMouseClicked(event -> {
                if (times % 2 == 0)
                    texts[0].setText(intToChar(finalI + 65));
                else
                    texts[1].setText(intToChar(finalI + 65));
                times++;
            });
        }
    }

    private String intToChar(int i) {
        char c = (char) i;
        return "" + c;
    }
}