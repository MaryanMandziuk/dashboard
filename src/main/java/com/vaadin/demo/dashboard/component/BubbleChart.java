package com.vaadin.demo.dashboard.component;

/**
 * Created by maryan on 15.02.17.
 */

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BubbleChartConfig;
import com.byteowls.vaadin.chartjs.data.BubbleDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
//import com.vaadin.demo.dashboard.component.ChartUtils;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class BubbleChart extends VerticalLayout {

    public BubbleChart() {
        setSizeFull();
        setMargin(true);
        Component layout = getChart();
        layout.setWidth(100, Sizeable.Unit.PERCENTAGE);
        addComponent(layout);
        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
    }

    private Component getChart() {

        BubbleChartConfig config = new BubbleChartConfig();
        config.
                data()
                .addDataset(new BubbleDataset().label("First dataset"))
                .addDataset(new BubbleDataset().label("Second dataset"))
                .addDataset(new BubbleDataset().label("Third dataset"))
                .addDataset(new BubbleDataset().label("Fourth dataset"))
                .and()
                .options()
                .responsive(true)
                .title()
                .display(true)
                .text("Chart.js Bubble Chart")
                .and()
                .done();

        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            BubbleDataset lds = (BubbleDataset) ds;
            lds.backgroundColor(ChartUtils.randomColor(.7));
            for (int i = 0; i < 15; i++) {
                lds.addData(ChartUtils.randomScalingFactor(), ChartUtils.randomScalingFactor(), Math.abs(ChartUtils.randomScalingFactor()) / 5);
            }
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) -> {
            BubbleDataset dataset = (BubbleDataset) config.data().getDatasets().get(a);
            ChartUtils.notification(a, b, dataset);
        });
        return chart;
    }
}
