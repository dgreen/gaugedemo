/*
 * Derived from code by
 *
 * Copyright (c) 2016 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dgreen.gaugedemo;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GaugeDemo extends Application {
    private        Gauge          g1;
    private        Gauge          g2;
    private        Gauge          g3;
    private        Gauge          g4;
    private        Gauge          g5;
    private        Gauge          g6;
    
    private        TextArea       notesArea;
    private        Label          notesLabel;
    private        VBox           notesBox;

    private static final int PREF_SIZE = 300;
    
    @Override public void init() {
        
        g1 = buildGaugeProperties("g1")
                               .title("Cash and Pledge Contributions")
                               .value(1500.)
                               .minValue(0.)
                               .threshold(3941.)
                               .maxValue(3941.*1.5)
                               .build();
        
        g2 = buildGaugeProperties("g2")
                               .title("Program Support vs Budget")
                               .value(3000.)
                               .minValue(0.)
                               .threshold(6000.)
                               .maxValue(6000.*1.5)
                               .build();

        g3 = buildGaugeProperties("g3")
                               .title("Admin & Fundraising vs Budget")
                               .value(700.0)
                               .minValue(0.)
                               .threshold(2607.)
                               .maxValue(2607.*1.5)
                               .build();

        g4 = buildGaugeProperties("g4")
                               .title("Days Expended")
                               .value(85.)
                               .maxValue(365.*1.5)
                               .threshold(365)
                               .minValue(0.)
                               .build();

        g5 = buildGaugeProperties("g5")
                               .title("Net Investment Income")
                               .value(700.)
                               .minValue(-2778.*.5)
                               .threshold(2778.)
                               .maxValue(2778.*1.5)
                               .angleRange(120.)   // NOTE smaller range to distinguish
                               .build();
                
        g6 = buildGaugeProperties("g6")
                               .title("Program to Total Expense Ratio")
                               .value(84.0)
                               .minValue(0.)
                               .threshold(75.)
                               .maxValue(100.)
                               .barColor(Color.RED)         // note colors
                               .thresholdColor(Color.GREEN)
                               .angleRange(120.)            // note angle ragne
                               .build();
        
        notesArea = new TextArea("No issues to highlight at this time");        
        notesArea.wrapTextProperty();
        notesLabel = new Label("Notes");
        notesLabel.setTextFill(Color.DARKBLUE);
        notesLabel.setFont(new Font("Arial", 18));
        
        notesBox = new VBox(0, notesLabel, notesArea);
        notesBox.setPadding(new Insets(10,10,10,10));
        notesArea.setFont(new Font("Arial", 16));
        notesArea.setStyle("-fx-text-inner-color: darkblue; -fx-control-inner-background:#EEEEEE;");
        
    }

    @Override public void start(Stage stage) {
        Pane pane = new Pane(g6, 
                             g3, 
                             g5,
                             g1,
                             g2,
                             g4,
                             notesBox
                            );
        pane.setBackground(new Background(new BackgroundFill(Gauge.BRIGHT_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        
        // Row 1
        g1.relocate(                     0,                0);
        g2.relocate(        PREF_SIZE + 20,                0);
        g3.relocate(      2*PREF_SIZE + 40,                0);
        g4.relocate(      3*PREF_SIZE + 60,                0);
        

        // Row 2
        g5.relocate(                     0,    PREF_SIZE + 40);
        g6.relocate(        PREF_SIZE + 20,    PREF_SIZE + 40);

        notesBox.relocate(2*PREF_SIZE + 40,    PREF_SIZE + 45);
        
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }
    
    private GaugeBuilder buildGaugeProperties(String name) {
        return GaugeBuilder.create()
                .skinType(Gauge.SkinType.TILE_KPI)
                .prefSize(PREF_SIZE, PREF_SIZE)
                .backgroundPaint(Gauge.BRIGHT_COLOR)
                .thresholdColor(Color.DARKBLUE)
                .titleColor(Color.DARKBLUE)
                .barColor(Color.DARKORANGE)
                .valueColor(Color.DARKBLUE)
                .needleColor(Color.BLACK)
                .thresholdVisible(true)
//                .title("Program to Total Expense Ratio") -- fetch properties
//                .value(84.0)
//                .minValue(0.)
//                .threshold(75.)
//                .maxValue(100.)
               ;
    }

    @Override public void stop() {
        System.exit(0);
    }


    public static void main(String[] args) {
        // load params
        launch(args);
    }
}