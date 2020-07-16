import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Main extends Application {
    private GUI pane = new GUI();
    Scene scene0 = new Scene(pane, 1050, 500);
    private Vertex[] vertexS = new Vertex[26];//存放所有顶点的数组
    private LinkedList<Edge>[] v_edge_List = new LinkedList[vertexS.length];//顶点的所有边的链,为linkedList的数组//vertexs[0]对应v_edge_List[0]
    private String mode;//交通模式
    private String outputMethod;//输出方式
    private String path;//最佳路线
    private double shortest_length;
    private String output;//储存输出的字符串
    private int totalBusTime;
    private int[][] mapIndex = {
            {43, 175},//A
            {84, 376},//B
            {107, 482},//C
            {171, 128},//D
            {193, 192},//E
            {196, 270},//F
            {198, 347},//G
            {213, 462},//H
            {296, 55},//I
            {304, 147},//J
            {304, 243},//K
            {312, 322},//L
            {317, 436},//M
            {391, 32},//N
            {413, 113},//O
            {435, 195},//P
            {475, 250},//Q
            {429, 322},//R
            {521, 304},//S
            {495, 154},//T
            {557, 90},//U
            {634, 14},//V
            {688, 79},//W
            {637, 82},//X
            {656, 253},//Y
            {558, 291},//Z
    };
    private String historySting = "";

    @Override
    public void start(Stage primaryStage) {
        for (int i = 65; i <= 90; i++) {//A——65,Z——90
            vertexS[i - 65] = new Vertex(intToChar(i));//创建顶点
        }

        for (int i = 0; i < vertexS.length; i++) {
            v_edge_List[i] = new LinkedList<>();
        }

        Edge AtoB = new Edge(vertexS[0], vertexS[1], 1.7);//车行
        AtoB.canBus = false;
        AtoB.canWalk = false;
        Edge AtoD = new Edge(vertexS[0], vertexS[3], 1.2);
        AtoD.canBus = false;
        v_edge_List[0].add(AtoB);
        v_edge_List[0].add(AtoD);

        Edge BtoA = new Edge(vertexS[1], vertexS[0], 1.7);//车行
        BtoA.canBus = false;
        BtoA.canWalk = false;
        Edge BtoC = new Edge(vertexS[1], vertexS[2], 0.81);//车行
        BtoC.canBus = false;
        BtoC.canWalk = false;
        Edge BtoG = new Edge(vertexS[1], vertexS[6], 1.12);
        BtoG.canBus = true;
        BtoG.busTime = 2;
        v_edge_List[1].add(BtoC);
        v_edge_List[1].add(BtoG);
        v_edge_List[1].add(BtoA);

        Edge CtoB = new Edge(vertexS[2], vertexS[1], 0.81);//车行
        CtoB.canBus = false;
        CtoB.canWalk = false;
        Edge CtoH = new Edge(vertexS[2], vertexS[7], 1.03);
        CtoH.canBus = false;
        v_edge_List[2].add(CtoB);
        v_edge_List[2].add(CtoH);

        Edge DtoA = new Edge(vertexS[3], vertexS[0], 1.2);
        DtoA.canBus = false;
        Edge DtoE = new Edge(vertexS[3], vertexS[4], 0.51);
        DtoE.canBus = false;
        Edge DtoI = new Edge(vertexS[3], vertexS[8], 1.32);
        DtoI.canBus = false;
        v_edge_List[3].add(DtoE);
        v_edge_List[3].add(DtoA);
        v_edge_List[3].add(DtoI);

        Edge EtoD = new Edge(vertexS[4], vertexS[3], 0.51);
        EtoD.canBus = false;
        Edge EtoF = new Edge(vertexS[4], vertexS[5], 0.68);
        EtoF.canBus = false;
        v_edge_List[4].add(EtoD);
        v_edge_List[4].add(EtoF);

        Edge FtoE = new Edge(vertexS[5], vertexS[4], 0.68);
        FtoE.canBus = true;
        Edge FtoK = new Edge(vertexS[5], vertexS[10], 1.14);
        FtoK.canBus = true;
        FtoK.busTime = 4;
        Edge FtoG = new Edge(vertexS[5], vertexS[6], 0.74);
        FtoG.canBus = true;
        v_edge_List[5].add(FtoE);
        v_edge_List[5].add(FtoG);
        v_edge_List[5].add(FtoK);

        Edge GtoF = new Edge(vertexS[6], vertexS[5], 0.74);
        GtoF.canBus = true;
        Edge GtoB = new Edge(vertexS[6], vertexS[1], 1.12);
        GtoB.canBus = true;
        GtoB.busTime = 2;
        Edge GtoL = new Edge(vertexS[6], vertexS[11], 1.01);
        GtoL.canBus = true;
        GtoL.busTime = 3;
        Edge GtoH = new Edge(vertexS[6], vertexS[7], 0.97);
        GtoH.canBus = false;
        v_edge_List[6].add(GtoF);
        v_edge_List[6].add(GtoH);
        v_edge_List[6].add(GtoL);
        v_edge_List[6].add(GtoB);

        Edge HtoC = new Edge(vertexS[7], vertexS[2], 1.03);
        HtoC.canBus = false;
        Edge HtoG = new Edge(vertexS[7], vertexS[6], 0.97);
        HtoG.canBus = false;
        Edge HtoM = new Edge(vertexS[7], vertexS[12], 0.94);
        HtoM.canBus = false;
        v_edge_List[7].add(HtoM);
        v_edge_List[7].add(HtoG);
        v_edge_List[7].add(HtoC);

        Edge ItoD = new Edge(vertexS[8], vertexS[3], 1.32);
        ItoD.canBus = false;
        Edge ItoN = new Edge(vertexS[8], vertexS[13], 0.9);
        ItoN.canBus = false;
        Edge ItoJ = new Edge(vertexS[8], vertexS[9], 0.95);
        ItoJ.canBus = false;
        v_edge_List[8].add(ItoN);
        v_edge_List[8].add(ItoJ);
        v_edge_List[8].add(ItoD);

        Edge JtoI = new Edge(vertexS[9], vertexS[8], 0.95);
        JtoI.canBus = false;
        Edge JtoK = new Edge(vertexS[9], vertexS[10], 0.62);
        JtoK.canBus = false;
        v_edge_List[9].add(JtoK);
        v_edge_List[9].add(JtoI);

        Edge KtoJ = new Edge(vertexS[10], vertexS[9], 0.62);
        KtoJ.canBus = false;
        Edge KtoF = new Edge(vertexS[10], vertexS[5], 1.14);
        KtoF.canBus = true;
        KtoF.busTime = 4;
        Edge KtoP = new Edge(vertexS[10], vertexS[15], 1.33);
        KtoP.canBus = true;
        KtoP.busTime = 0;
        Edge KtoL = new Edge(vertexS[10], vertexS[11], 0.66);
        KtoL.canBus = true;
        v_edge_List[10].add(KtoJ);
        v_edge_List[10].add(KtoL);
        v_edge_List[10].add(KtoF);
        v_edge_List[10].add(KtoP);

        Edge LtoK = new Edge(vertexS[11], vertexS[10], 0.66);
        LtoK.canBus = true;
        Edge LtoG = new Edge(vertexS[11], vertexS[6], 1.01);
        LtoG.canBus = true;
        LtoG.busTime = 3;
        Edge LtoR = new Edge(vertexS[11], vertexS[17], 1.10);
        LtoR.canBus = true;
        LtoR.busTime = 2;
        Edge LtoM = new Edge(vertexS[11], vertexS[12], 0.92);
        LtoM.canBus = false;
        v_edge_List[11].add(LtoK);
        v_edge_List[11].add(LtoM);
        v_edge_List[11].add(LtoG);
        v_edge_List[11].add(LtoR);

        Edge MtoL = new Edge(vertexS[12], vertexS[11], 0.92);
        MtoL.canBus = false;
        Edge MtoH = new Edge(vertexS[12], vertexS[7], 0.94);
        MtoH.canBus = false;
        v_edge_List[12].add(MtoL);
        v_edge_List[12].add(MtoH);

        Edge NtoI = new Edge(vertexS[13], vertexS[8], 0.9);
        NtoI.canBus = false;
        Edge NtoO = new Edge(vertexS[13], vertexS[14], 0.83);
        NtoO.canBus = false;
        v_edge_List[13].add(NtoO);
        v_edge_List[13].add(NtoI);

        Edge OtoN = new Edge(vertexS[14], vertexS[13], 0.83);
        OtoN.canBus = false;
        Edge OtoU = new Edge(vertexS[14], vertexS[20], 1.30);
        OtoU.canBus = true;
        OtoU.busTime = 0;
        Edge OtoP = new Edge(vertexS[14], vertexS[15], 0.55);
        OtoP.canBus = true;
        OtoP.busTime = 0;
        v_edge_List[14].add(OtoP);
        v_edge_List[14].add(OtoN);
        v_edge_List[14].add(OtoU);

        Edge PtoO = new Edge(vertexS[15], vertexS[14], 0.55);
        PtoO.canBus = true;
        PtoO.busTime = 0;
        Edge PtoK = new Edge(vertexS[15], vertexS[10], 1.33);
        PtoK.canBus = true;
        PtoK.busTime = 0;
        Edge PtoT = new Edge(vertexS[15], vertexS[19], 0.62);//人行
        PtoT.canBus = false;
        PtoT.canDrive = false;
        Edge PtoQ = new Edge(vertexS[15], vertexS[16], 0.62);//人行
        PtoQ.canBus = false;
        PtoQ.canDrive = false;
        v_edge_List[15].add(PtoO);
        v_edge_List[15].add(PtoT);
        v_edge_List[15].add(PtoQ);
        v_edge_List[15].add(PtoK);

        Edge QtoP = new Edge(vertexS[16], vertexS[15], 0.62);//人行
        QtoP.canBus = false;
        QtoP.canDrive = false;
        Edge QtoT = new Edge(vertexS[16], vertexS[19], 0.93);//人行
        QtoT.canBus = false;
        QtoT.canDrive = false;
        Edge QtoR = new Edge(vertexS[16], vertexS[17], 0.73);//人行
        QtoR.canBus = false;
        QtoR.canDrive = false;
        Edge QtoS = new Edge(vertexS[16], vertexS[18], 0.51);//人行
        QtoS.canBus = false;
        QtoS.canDrive = false;
        v_edge_List[16].add(QtoS);
        v_edge_List[16].add(QtoR);
        v_edge_List[16].add(QtoP);
        v_edge_List[16].add(QtoT);

        Edge RtoL = new Edge(vertexS[17], vertexS[11], 1.1);
        RtoL.canBus = true;
        RtoL.busTime = 2;
        Edge RtoQ = new Edge(vertexS[17], vertexS[16], 0.73);//人行
        RtoQ.canBus = false;
        RtoQ.canDrive = false;
        Edge RtoS = new Edge(vertexS[17], vertexS[18], 0.87);
        RtoS.canBus = true;
        RtoS.busTime = 0;
        v_edge_List[17].add(RtoQ);
        v_edge_List[17].add(RtoS);
        v_edge_List[17].add(RtoL);

        Edge StoQ = new Edge(vertexS[18], vertexS[16], 0.51);//人行
        StoQ.canBus = false;
        StoQ.canDrive = false;
        Edge StoR = new Edge(vertexS[18], vertexS[17], 0.87);
        StoR.canBus = true;
        StoR.busTime = 0;
        Edge StoZ = new Edge(vertexS[18], vertexS[25], 0.38);
        StoZ.canBus = true;
        StoZ.busTime = 2;
        v_edge_List[18].add(StoZ);
        v_edge_List[18].add(StoQ);
        v_edge_List[18].add(StoR);

        Edge TtoP = new Edge(vertexS[19], vertexS[15], 0.62);//人行
        TtoP.canBus = false;
        TtoP.canDrive = false;
        Edge TtoQ = new Edge(vertexS[19], vertexS[16], 0.93);//人行
        TtoQ.canBus = false;
        TtoQ.canDrive = false;
        Edge TtoU = new Edge(vertexS[19], vertexS[20], 1.12);
        TtoU.canBus = true;
        TtoU.busTime = 5;
        Edge TtoZ = new Edge(vertexS[19], vertexS[25], 1.89);
        TtoZ.canBus = false;
        v_edge_List[19].add(TtoP);
        v_edge_List[19].add(TtoQ);
        v_edge_List[19].add(TtoU);
        v_edge_List[19].add(TtoZ);

        Edge UtoO = new Edge(vertexS[20], vertexS[14], 1.30);
        UtoO.canBus = true;
        UtoO.busTime = 0;
        Edge UtoV = new Edge(vertexS[20], vertexS[21], 0.99);
        UtoV.canBus = false;
        Edge UtoT = new Edge(vertexS[20], vertexS[19], 1.12);
        UtoT.canBus = true;
        UtoT.busTime = 5;
        Edge UtoZ = new Edge(vertexS[20], vertexS[25], 1.70);
        UtoZ.canBus = true;
        Edge UtoX = new Edge(vertexS[20], vertexS[23], 0.65);//汽车不能走
        UtoX.canBus = false;
        UtoX.canDrive = false;
        v_edge_List[20].add(UtoV);
        v_edge_List[20].add(UtoT);
        v_edge_List[20].add(UtoO);
        v_edge_List[20].add(UtoZ);
        v_edge_List[20].add(UtoX);

        Edge VtoW = new Edge(vertexS[21], vertexS[22], 1.10);
        VtoW.canBus = false;
        Edge VtoX = new Edge(vertexS[21], vertexS[23], 0.58);
        VtoX.canBus = false;
        Edge VtoU = new Edge(vertexS[21], vertexS[20], 0.99);//汽车不能走
        VtoU.canBus = false;
        VtoU.canDrive = false;
        v_edge_List[21].add(VtoX);
        v_edge_List[21].add(VtoW);
        v_edge_List[21].add(VtoU);

        Edge WtoX = new Edge(vertexS[22], vertexS[23], 0.57);
        WtoX.canBus = false;
        Edge WtoV = new Edge(vertexS[22], vertexS[21], 1.1);//汽车不能走
        WtoV.canBus = false;
        WtoV.canDrive = false;
        v_edge_List[22].add(WtoX);
        v_edge_List[22].add(WtoV);

        Edge XtoV = new Edge(vertexS[23], vertexS[21], 0.58);
        XtoV.canBus = false;
        Edge XtoU = new Edge(vertexS[23], vertexS[20], 0.65);
        XtoU.canBus = false;
        Edge XtoY = new Edge(vertexS[23], vertexS[24], 1.40);
        XtoY.canBus = false;
        Edge XtoW = new Edge(vertexS[23], vertexS[22], 0.57);//汽车不能走
        XtoW.canBus = false;
        XtoW.canDrive = false;
        v_edge_List[23].add(XtoV);
        v_edge_List[23].add(XtoU);
        v_edge_List[23].add(XtoY);
        v_edge_List[23].add(XtoW);

        Edge YtoX = new Edge(vertexS[24], vertexS[23], 1.40);
        YtoX.canBus = false;
        Edge YtoZ = new Edge(vertexS[24], vertexS[25], 0.97);
        YtoZ.canBus = true;
        YtoZ.busTime = 1;
        v_edge_List[24].add(YtoZ);
        v_edge_List[24].add(YtoX);

        Edge ZtoS = new Edge(vertexS[25], vertexS[18], 0.38);
        ZtoS.canBus = true;
        ZtoS.busTime = 2;
        Edge ZtoT = new Edge(vertexS[25], vertexS[19], 1.89);
        ZtoT.canBus = false;
        Edge ZtoU = new Edge(vertexS[25], vertexS[20], 1.70);
        ZtoU.canBus = true;
        Edge ZtoY = new Edge(vertexS[25], vertexS[24], 0.97);
        ZtoY.canBus = true;
        ZtoY.busTime = 1;
        v_edge_List[25].add(ZtoS);
        v_edge_List[25].add(ZtoY);
        v_edge_List[25].add(ZtoU);
        v_edge_List[25].add(ZtoT);

        pane.buttons[0].setOnMouseClicked(event -> {//重置
            pane.texts[0].setText("");
            pane.texts[1].setText("");
            pane.labels[3].setText("");
            pane.texts[2].setText("");
            pane.showHistory.setContentText("");
            pane.labels[3].setText("注：输出模式1选择起点和终点，在该界面上显示最佳路线与相应时间。" +
                    "输出模式2选择起点，在外部文件中得到所有点到该点的最短路径。输出模式3直接得到所有点之间的最佳路线。");
            initial();//使顶点变成unknown
            mode = "";
            outputMethod = "";
        });

        pane.buttons[1].setOnMouseClicked(event -> {//徒步
            if (pane.texts[2].getText().equals("")) {
                pane.labels[3].setText("请填写输出模式！");
            } else if (pane.texts[2].getText().equals("1") || (pane.texts[2].getText().equals("2")) || (pane.texts[2].getText().equals("3"))) {
                mode = "徒步";
                outputMethod = pane.texts[2].getText();
                initial();
                switch (outputMethod) {
                    case "1":
                        if (pane.texts[0].getText().equals("") || pane.texts[1].getText().equals("")) {
                            pane.labels[3].setText("请填写起终点！");
                            break;
                        } else if (pane.texts[0].getText().equals(pane.texts[1].getText())) {
                            pane.labels[3].setText("起终点请勿重复！");
                            break;
                        } else {
                            String str1 = pane.texts[0].getText();
                            String str2 = pane.texts[1].getText();
                            int startIndex = str1.charAt(0) - 65;
                            int destIndex = str2.charAt(0) - 65;
                            if (!(startIndex >= 0 && startIndex <= 25) || !(destIndex >= 0 && destIndex <= 25)) {
                                pane.labels[3].setText("起终点请输入大写字母！");
                                break;
                            }
                            findWalkPath(startIndex, destIndex);
                            plusHistory(mode, startIndex, destIndex);
                            print(path, shortest_length);
                        }
                        break;
                    case "2":
                        if (pane.texts[0].getText().equals("")) {
                            pane.labels[3].setText("请填写起点！");
                        } else {
                            String str1 = pane.texts[0].getText();//起点
                            int startIndex = str1.charAt(0) - 65;
                            if (!(startIndex >= 0 && startIndex <= 25)) {
                                pane.labels[3].setText("起点请输入大写字母！");
                                break;
                            }
                            try {
                                FileWriter fw = new FileWriter(new File("C:\\Users\\19716\\Desktop\\从" + pane.texts[0].getText() + "出发的徒步路线.txt"));
                                for (int i = 0; i <= 25; i++) {
                                    initial();
                                    findWalkPath(startIndex, i);
                                    double finallyLength = (new BigDecimal(shortest_length))
                                            .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位小数
                                    output = intToChar(startIndex + 65) + "到" + intToChar(i + 65) + "的最佳路线为：" + path + "\n" + "该路线距离为：" + finallyLength + " km" + "\n";
                                    fw.write(output);
                                }
                                fw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pane.labels[3].setText("输出成功！请去桌面获取文件！");
                        }
                        break;
                    case "3":
                        try {
                            FileWriter fw = new FileWriter(new File("C:\\Users\\19716\\Desktop\\所有点的徒步路线.txt"));
                            for (int i = 0; i <= 25; i++) {
                                for (int j = 0; j <= 25; j++) {
                                    initial();
                                    findWalkPath(i, j);
                                    double finallyLength = (new BigDecimal(shortest_length))
                                            .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位小数
                                    output = intToChar(i + 65) + "到" + intToChar(j + 65) + "的最佳路线为：" + path + "\n" + "该路线距离为：" + finallyLength + " km" + "\n";
                                    fw.write(output);
                                }
                            }
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pane.labels[3].setText("输出成功！请去桌面获取文件！");
                        break;
                }
            } else {
                pane.labels[3].setText("输出模式请填写1或2或3！");
            }
        });

        pane.buttons[2].setOnMouseClicked(event -> {//驾车
            if (pane.texts[2].getText().equals("")) {
                pane.labels[3].setText("请填写输出模式！");
            } else if (pane.texts[2].getText().equals("1") || (pane.texts[2].getText().equals("2")) || (pane.texts[2].getText().equals("3"))) {
                mode = "驾车";
                outputMethod = pane.texts[2].getText();
                initial();
                switch (outputMethod) {
                    case "1":
                        if (pane.texts[0].getText().equals("") || pane.texts[1].getText().equals("")) {
                            pane.labels[3].setText("请填写起终点！");
                            break;
                        } else if (pane.texts[0].getText().equals(pane.texts[1].getText())) {
                            pane.labels[3].setText("起终点请勿重复！");
                            break;
                        } else if (pane.texts[0].getText().equals("Q") || pane.texts[1].getText().equals("Q")) {
                            pane.labels[3].setText("无法驾车到达，Q周围都是人行道！");
                            break;
                        } else {
                            String str1 = pane.texts[0].getText();
                            String str2 = pane.texts[1].getText();
                            int startIndex = str1.charAt(0) - 65;
                            int destIndex = str2.charAt(0) - 65;
                            if (!(startIndex >= 0 && startIndex <= 25) || !(destIndex >= 0 && destIndex <= 25)) {
                                pane.labels[3].setText("起终点请输入大写字母！");
                                break;
                            }
                            findCarPath(startIndex, destIndex);
                            plusHistory(mode, startIndex, destIndex);
                            print(path, shortest_length);
                        }
                        break;
                    case "2":
                        if (pane.texts[0].getText().equals("")) {
                            pane.labels[3].setText("请填写起点！");
                        } else if (pane.texts[0].getText().equals("Q") || pane.texts[1].getText().equals("Q")) {
                            pane.labels[3].setText("无法驾车到达，Q周围都是人行道！");
                            break;
                        } else {
                            String str1 = pane.texts[0].getText();//起点
                            int startIndex = str1.charAt(0) - 65;
                            if (!(startIndex >= 0 && startIndex <= 25)) {
                                pane.labels[3].setText("起点请输入大写字母！");
                                break;
                            }
                            try {
                                FileWriter fw = new FileWriter(new File("C:\\Users\\19716\\Desktop\\从" + pane.texts[0].getText() + "出发的驾车路线.txt"));
                                for (int i = 0; i <= 25; i++) {
                                    if (i == 16) continue;
                                    initial();
                                    findCarPath(startIndex, i);
                                    double finallyLength = (new BigDecimal(shortest_length))
                                            .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位小数
                                    output = intToChar(startIndex + 65) + "到" + intToChar(i + 65) + "的最佳路线为：" + path + "\n" + "该路线距离为：" + finallyLength + " km" + "\n";
                                    fw.write(output);
                                }
                                fw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pane.labels[3].setText("输出成功！请去桌面获取文件！");
                        }
                        break;
                    case "3":
                        try {
                            FileWriter fw = new FileWriter(new File("C:\\Users\\19716\\Desktop\\所有点的驾车路线.txt"));
                            for (int i = 0; i <= 25; i++) {
                                if (i == 16) continue;
                                for (int j = 0; j <= 25; j++) {
                                    if (j == 16) continue;
                                    initial();
                                    findCarPath(i, j);
                                    double finallyLength = (new BigDecimal(shortest_length))
                                            .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位小数
                                    output = intToChar(i + 65) + "到" + intToChar(j + 65) + "的最佳路线为：" + path + "\n" + "该路线距离为：" + finallyLength + " km" + "\n";
                                    fw.write(output);
                                }
                            }
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pane.labels[3].setText("输出成功！请去桌面获取文件！");
                        break;
                }
            } else {
                pane.labels[3].setText("输出模式请填写1或2或3！");
            }
        });

        pane.buttons[3].setOnMouseClicked(event -> {//公交
            if (pane.texts[2].getText().equals("")) {
                pane.labels[3].setText("请填写输出模式！");
            } else if (pane.texts[2].getText().equals("1") || (pane.texts[2].getText().equals("2")) || (pane.texts[2].getText().equals("3"))) {//有输出模式
                mode = "公交";
                outputMethod = pane.texts[2].getText();
                initial();
                if (pane.texts[0].getText().equals("Q") || pane.texts[1].getText().equals("Q")) {
                    pane.labels[3].setText("无法乘公交到达，Q周围都是人行道！");
                } else if (judgeBusStation()) {
                    pane.labels[3].setText("起点或终点不是公交站点！\n请前往B、F、G、K、L、R、T、Y、Z处上车！");
                } else {
                    switch (outputMethod) {
                        case "1":
                            if (pane.texts[0].getText().equals("") || pane.texts[1].getText().equals("")) {
                                pane.labels[3].setText("请填写起终点！");
                                break;
                            } else if (pane.texts[0].getText().equals(pane.texts[1].getText())) {
                                pane.labels[3].setText("起终点请勿重复！");
                                break;
                            } else if (pane.texts[0].getText().equals("Q") || pane.texts[1].getText().equals("Q")) {
                                pane.labels[3].setText("无法驾车到达，Q周围都是人行道！");
                                break;
                            } else {
                                String str1 = pane.texts[0].getText();
                                String str2 = pane.texts[1].getText();
                                int startIndex = str1.charAt(0) - 65;
                                int destIndex = str2.charAt(0) - 65;
                                if (!(startIndex >= 0 && startIndex <= 25) || !(destIndex >= 0 && destIndex <= 25)) {
                                    pane.labels[3].setText("起终点请输入大写字母！");
                                    break;
                                }
                                findBusPath(startIndex, destIndex);
                                plusHistory(mode, startIndex, destIndex);
                                print(path, shortest_length);
                            }
                            break;
                        case "2":
                            if (pane.texts[0].getText().equals("")) {
                                pane.labels[3].setText("请填写起点！");
                                break;
                            } else if (pane.texts[0].getText().equals("Q") || pane.texts[1].getText().equals("Q")) {
                                initial();
                                pane.labels[3].setText("无法驾车到达，Q周围都是人行道！");
                                break;
                            } else {
                                String str1 = pane.texts[0].getText();//起点
                                int startIndex = str1.charAt(0) - 65;
                                if (!(startIndex >= 0 && startIndex <= 25)) {
                                    pane.labels[3].setText("起点请输入大写字母！");
                                    break;
                                }
                                try {
                                    FileWriter fw = new FileWriter(new File("C:\\Users\\19716\\Desktop\\从"
                                            + pane.texts[0].getText() + "出发的公交路线.txt"));
                                    for (int i = 0; i <= 25; i++) {
                                        if (i == 0 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 12 || i == 13 || i == 16 || i == 21 || i == 22 || i == 23)
                                            continue;
                                        initial();
                                        findBusPath(startIndex, i);
                                        double finallyLength = (new BigDecimal(shortest_length))
                                                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位小数
                                        if (totalBusTime == 0) {
                                            output = "无" + intToChar(startIndex + 65) + "到" + intToChar(i + 65) + "的公交路线" + "\n";
                                        } else {
                                            output = intToChar(startIndex + 65) + "到" + intToChar(i + 65) + "的最佳路线为：" + path +
                                                    "\n" + "该路线距离为：" + finallyLength + " km" + "\n" + "预计耗时： " + totalBusTime + " min" + "\n";
                                        }
                                        fw.write(output);
                                    }
                                    fw.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                pane.labels[3].setText("输出成功！请去桌面获取文件！");
                            }
                            break;
                        case "3":
                            try {
                                FileWriter fw = new FileWriter(new File("C:\\Users\\19716\\Desktop\\所有点的公交路线.txt"));
                                for (int i = 0; i <= 25; i++) {
                                    if (i == 0 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 12 || i == 13 || i == 16 || i == 21 || i == 22 || i == 23)
                                        continue;
                                    for (int j = 0; j <= 25; j++) {
                                        if (j == 0 || j == 2 || j == 3 || j == 4 || j == 7 || j == 8 || j == 9 || j == 12 || j == 13 || j == 16 || j == 21 || j == 22 || j == 23)
                                            continue;
                                        initial();
                                        findBusPath(i, j);
                                        double finallyLength = (new BigDecimal(shortest_length))
                                                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位小数
                                        if (totalBusTime == 0) {
                                            output = "无" + intToChar(i + 65) + "到" + intToChar(j + 65) + "的公交路线" + "\n";
                                        } else {
                                            output = intToChar(i + 65) + "到" + intToChar(j + 65) + "的最佳路线为：" + path + "\n" +
                                                    "该路线距离为：" + finallyLength + " km" + "\n" + "预计耗时： " + totalBusTime + " min" + "\n";
                                        }
                                        fw.write(output);
                                    }
                                }
                                fw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pane.labels[3].setText("输出成功！请去桌面获取文件！");
                            break;
                    }
                }
            } else {
                pane.labels[3].setText("输出模式请填写1或2或3！");
            }
        });

        primaryStage.setTitle("Project 2");
        primaryStage.setScene(scene0);
        primaryStage.show();
    }

    public void initial() {
        for (int i = 0; i < 26; i++) {
            vertexS[i].setKnown(false);
            vertexS[i].setAdjuDist(1000.0);
            vertexS[i].nowBusTime = 1000;
        }
        totalBusTime = 0;
        pane.times = 0;
        for (Node x : pane.getChildren()) {
            if (x instanceof Line) {
                x.setOpacity(0);
//                pane.getChildren().remove(x);
            }
        }
    }

    public String intToChar(int i) {
        char c = (char) i;
        return "" + c;
    }

    public void findWalkPath(int startIndex, int destinationIndex) {
        Vertex start = vertexS[startIndex];

        start.setParent(null);
        start.setAdjuDist(0);//顶点处的距离为0

        Vertex v, w;
        Edge e;//v为e的起点,w为e的终点
        while (true) {
            int index = getMinVertex();//获得未知的邻接的最小的点
            v = vertexS[index];
            if (isEnd()) {
                break;
            }
            v.setKnown(true);
            for (int i = 0; i < v_edge_List[index].size(); i++) {
                e = v_edge_List[index].get(i);
                if (e.canWalk) {
                    w = e.getEndVertex();
                    if (!w.isKnown()) {
                        if (v.getAdjuDist() + e.getWeight() < w.getAdjuDist()) {
                            w.setAdjuDist(v.getAdjuDist() + e.getWeight());
                            w.setParent(v);

                        }
                    }
                }
            }
        }

        Vertex destination = vertexS[destinationIndex];
        shortest_length = destination.getAdjuDist();
        path = destination.getName();//初始化path
        Vertex tmp = destination;//防止destination的改变
        while ((tmp.getParent() != null) && (!tmp.equals(start))) {
            path = //更新路径
                    tmp.getParent().getName() + "-->" +
                            path;//此处path为上一次循环的旧路径
            if (outputMethod.equals("1")) {
                Vertex forDraw = tmp;
                tmp = tmp.getParent();//递归
                drawLine(tmp.getName().charAt(0) - 65, forDraw.getName().charAt(0) - 65);
            } else {
                tmp = tmp.getParent();
            }
        }
    }

    public void findCarPath(int startIndex, int destinationIndex) {
        Vertex start = vertexS[startIndex];

        start.setParent(null);
        start.setAdjuDist(0);//顶点处的距离为0

        Vertex v, w;
        Edge e;//v为e的起点,w为e的终点
        while (true) {
            int index = getMinVertex();//获得未知的邻接的最小的点
            v = vertexS[index];
            if (isEnd()) {
                break;
            }
            v.setKnown(true);
            for (int i = 0; i < v_edge_List[index].size(); i++) {
                e = v_edge_List[index].get(i);
                if (e.canDrive) {
                    w = e.getEndVertex();
                    if (!w.isKnown()) {
                        if (v.getAdjuDist() + e.getWeight() < w.getAdjuDist()) {
                            w.setAdjuDist(v.getAdjuDist() + e.getWeight());
                            w.setParent(v);
                        }
                    }
                }
            }
        }
        Vertex destination = vertexS[destinationIndex];
        shortest_length = destination.getAdjuDist();
        path = destination.getName();//初始化path
        Vertex tmp = destination;//防止destination的改变
        while ((tmp.getParent() != null) && (!tmp.equals(start))) {
            path = //更新路径
                    tmp.getParent().getName() + "-->" +
                            path;//此处path为上一次循环的旧路径
            if (outputMethod.equals("1")) {
                Vertex forDraw = tmp;
                tmp = tmp.getParent();//递归
                drawLine(tmp.getName().charAt(0) - 65, forDraw.getName().charAt(0) - 65);
            } else {
                tmp = tmp.getParent();
            }
        }
    }

    public void findBusPath(int startIndex, int destinationIndex) {
        Vertex start = vertexS[startIndex];

        start.setParent(null);
        start.nowBusTime = 0;
        start.setAdjuDist(0);//顶点处的距离为0

        Vertex v, w;
        Edge e;//v为e的起点,w为e的终点
        while (true) {
            int index = getMinVertex();//获得未知的邻接的最小的点
            v = vertexS[index];
            if (isEnd()) {
                break;
            }
            v.setKnown(true);
            for (int i = 0; i < v_edge_List[index].size(); i++) {
                e = v_edge_List[index].get(i);
                if (e.canBus) {
                    w = e.getEndVertex();
                    if (!w.isKnown()) {
                        if (v.nowBusTime + e.busTime <= w.nowBusTime) {//bus为时间优先
                            w.nowBusTime = v.nowBusTime + e.busTime;
                            w.setAdjuDist(v.getAdjuDist() + e.getWeight());
                            w.setParent(v);
                        }
                    }
                }
            }
        }
        Vertex destination = vertexS[destinationIndex];
        totalBusTime = destination.nowBusTime;
        shortest_length = destination.getAdjuDist();
        path = destination.getName();//初始化path
        Vertex tmp = destination;//防止destination的改变
        while ((tmp.getParent() != null) && (!tmp.equals(start))) {
            path = //更新路径
                    tmp.getParent().getName() + "-->" +
                            path;//此处path为上一次循环的旧路径
            if (outputMethod.equals("1")) {
                Vertex forDraw = tmp;
                tmp = tmp.getParent();//递归
                drawLine(tmp.getName().charAt(0) - 65, forDraw.getName().charAt(0) - 65);
            } else {
                tmp = tmp.getParent();
            }
        }
    }

    public boolean judgeBusStation() {
        return pane.texts[0].getText().equals("A") || pane.texts[0].getText().equals("C")
                || pane.texts[0].getText().equals("D")
                || pane.texts[0].getText().equals("E") || pane.texts[0].getText().equals("H")
                || pane.texts[0].getText().equals("I") || pane.texts[0].getText().equals("J")
                || pane.texts[0].getText().equals("M") || pane.texts[0].getText().equals("N")
                || pane.texts[0].getText().equals("O") || pane.texts[0].getText().equals("P")
                || pane.texts[0].getText().equals("S") || pane.texts[0].getText().equals("U")
                || pane.texts[0].getText().equals("V") || pane.texts[0].getText().equals("W")
                || pane.texts[0].getText().equals("X")
                || pane.texts[1].getText().equals("A") || pane.texts[1].getText().equals("C")
                || pane.texts[1].getText().equals("D")
                || pane.texts[1].getText().equals("E") || pane.texts[1].getText().equals("H")
                || pane.texts[1].getText().equals("I") || pane.texts[1].getText().equals("J")
                || pane.texts[1].getText().equals("M") || pane.texts[1].getText().equals("N")
                || pane.texts[1].getText().equals("O") || pane.texts[1].getText().equals("P")
                || pane.texts[1].getText().equals("S") || pane.texts[1].getText().equals("U")
                || pane.texts[1].getText().equals("V") || pane.texts[1].getText().equals("W")
                || pane.texts[1].getText().equals("X");
    }

    public int getMinVertex() {//返回未知的、路径最短的点 的下标
        int index = 0;
        double min = 1000.0;
        for (int i = 0; i < 26; i++) {
            if (!vertexS[i].isKnown() && vertexS[i].getAdjuDist() < min) {
                min = vertexS[i].getAdjuDist();
                index = i;
            }
        }
        return index;
    }

    public boolean isEnd() {
        boolean flag = true;
        for (int i = 0; i < 26; i++) {
            if (mode.equals("公交")) {
                if (i == 0 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 12 || i == 13 || i == 16 || i == 21 || i == 22 || i == 23)
                    continue;
            }
            if (mode.equals("驾车")) {//驾车时得略过Q点
                if (i == 16)
                    continue;
            }
            flag = flag && vertexS[i].isKnown();
        }
        return flag;
    }

    public void print(String path, double shortest_length) {
        double finallyLength = (new BigDecimal(shortest_length)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位小数
        System.out.println("最佳路线为：" + path);
        System.out.println("该路线距离为：" + finallyLength + " km");
        //时间
        if (mode.equals("徒步")) {
            int walkTime = (int) (finallyLength / 0.07);
            pane.labels[3].setText("最佳路线为：" + path + "\n" + "该路线距离为：" + finallyLength + " km"
                    + "\n" + "预计耗时： " + walkTime + " min");
            System.out.println("预计耗时： " + walkTime + " min\n");
        } else if (mode.equals("驾车")) {
            int carTime = (int) finallyLength;
            if (carTime == 0) carTime = 1;
            pane.labels[3].setText("最佳路线为：" + path + "\n" + "该路线距离为：" + finallyLength + " km"
                    + "\n" + "预计耗时： " + carTime + " min (车速为60km/h)");
            System.out.println("预计耗时： " + carTime + " min\n");
        } else if (mode.endsWith("公交")) {
            int walkTime = (int) (finallyLength / 0.07);
            if (walkTime <= totalBusTime) {
                pane.labels[3].setText("最佳路线为：" + path + "\n" + "该路线距离为：" + finallyLength + " km"
                        + "\n" + "预计耗时： " + totalBusTime + " min" + "\n步行耗时约: " + walkTime + " min 选择公交较好！");
            } else {
                pane.labels[3].setText("最佳路线为：" + path + "\n" + "该路线距离为：" + finallyLength + " km"
                        + "\n" + "预计耗时： " + totalBusTime + " min" + "\n步行耗时约: " + walkTime + " min 选择步行较好！");
            }
        }
    }

    public void drawLine(int i, int j) {
        Line line = new Line(mapIndex[i][0], mapIndex[i][1], mapIndex[j][0], mapIndex[j][1]);
        line.setStroke(Color.RED);
        line.setStrokeWidth(7);
        pane.getChildren().add(line);
    }

    public void plusHistory(String mode, int startIndex, int destinationIndex) {
        historySting += mode + "从 " + intToChar(startIndex + 65) + " 前往 " + intToChar(destinationIndex + 65) + " " + "\n";
        pane.showHistory.setContentText(historySting);
    }

}