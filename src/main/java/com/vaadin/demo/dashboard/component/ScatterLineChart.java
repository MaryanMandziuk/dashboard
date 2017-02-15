package com.vaadin.demo.dashboard.component;

/**
 * Created by maryan on 15.02.17.
 */
import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.ScatterChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.ScatterDataset;
import com.byteowls.vaadin.chartjs.options.Hover.Mode;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class ScatterLineChart extends VerticalLayout{

    public ScatterLineChart() {
        setSizeFull();
        setMargin(true);
        Component layout = getChart();
        layout.setWidth(100, Sizeable.Unit.PERCENTAGE);
        addComponent(layout);
        setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
    }

    private Component getChart() {
        ScatterChartConfig config = new ScatterChartConfig();
        config
                .data()
                .addDataset(new ScatterDataset().label("First dataset").xAxisID("x-axis-1").yAxisID("y-axis-1"))
                .addDataset(new ScatterDataset().label("Second  dataset").xAxisID("x-axis-1").yAxisID("y-axis-2"))
                .addDataset(new ScatterDataset().label("Third  dataset").xAxisID("x-axis-1").yAxisID("y-axis-2"))
                .and();
        config.
                options()
                .responsive(true)
                .hover()
                .mode(Mode.SINGLE)
                .and()
                .title()
                .display(true)
                .text("Chart.js Scatter Chart - Multi Axis")
                .and()
                .scales()
                .add(Axis.X, new LinearScale().position(Position.BOTTOM).gridLines().zeroLineColor("rgba(0,0,0,1)").and())
                .add(Axis.Y, new LinearScale().display(true).position(Position.LEFT).id("y-axis-1"))
                .add(Axis.Y, new LinearScale().display(true).position(Position.RIGHT).id("y-axis-2").ticks().reverse(true).and().gridLines().drawOnChartArea(false).and())
                .and()
                .done();

        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            ScatterDataset lds = (ScatterDataset) ds;
            lds.borderColor(ChartUtils.randomColor(.4));
            lds.backgroundColor(ChartUtils.randomColor(.1));
            lds.pointBorderColor(ChartUtils.randomColor(.7));
            lds.pointBackgroundColor(ChartUtils.randomColor(.5));
            lds.pointBorderWidth(1);
            for (int i = 0; i < 7; i++) {
                lds.addData(ChartUtils.randomScalingFactor(), ChartUtils.randomScalingFactor());
            }
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);

        chart.addClickListener((a,b) -> {
            ScatterDataset dataset = (ScatterDataset) config.data().getDatasets().get(a);
            ChartUtils.notification(a, b, dataset);
        });
        return chart;
    }
}
