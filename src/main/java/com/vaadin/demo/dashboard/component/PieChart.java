package com.vaadin.demo.dashboard.component;

/**
 * Created by maryan on 15.02.17.
 */
import java.util.ArrayList;
import java.util.List;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;


import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;


public class PieChart extends VerticalLayout {

    public PieChart() {
        setSizeFull();
        setMargin(true);
        Component layout = getChart();
        layout.setWidth(100, Unit.PERCENTAGE);
        addComponent(layout);
        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
    }

    private Component getChart() {
        PieChartConfig config = new PieChartConfig();
        config
            .data()
                .labels("Label 1", "Label 2", "Label 3", "Label 4", "Label 5", "Label 6")
                .addDataset(new PieDataset().label("Dataset 1"))
                .addDataset(new PieDataset().label("Dataset 2"))
                .and();
        
        config.
            options()
                .responsive(true)
                .title()
                    .display(true)
                    .text("Chart.js Pie Chart")
                    .and()
                .animation()
                    .animateScale(true)
                    .animateRotate(true)
                    .and()
               .done();
        
        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            PieDataset lds = (PieDataset) ds;
            List<Double> data = new ArrayList<>();
            List<String> colors = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.round(Math.random() * 100)));
                colors.add(ChartUtils.randomColor(0.7));
            }
            lds.backgroundColor(colors.toArray(new String[colors.size()]));
            lds.dataAsList(data);
        }
        
        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) -> {
            PieDataset dataset = (PieDataset) config.data().getDatasets().get(a);
            ChartUtils.notification(a, b, dataset);
        });

        return chart; 
    }
}