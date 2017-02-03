package com.vaadin.demo.dashboard.view.charts;

import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Hover;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.Tooltips;
import com.byteowls.vaadin.chartjs.options.elements.Rectangle;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.CategoryScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import com.byteowls.vaadin.chartjs.ChartJs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maryan on 03.02.17.
 */
public class ChartsView extends VerticalLayout implements View {

    public ChartsView() {
        BarChartConfig barConfig = new BarChartConfig();
        barConfig.horizontal();
        barConfig.
                data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new BarDataset().backgroundColor("rgba(30,144,255,0.5)").label("Dataset 1"))
                .addDataset(new BarDataset().backgroundColor("rgba(30,144,255,0.5)").label("Dataset 2"))
                .addDataset(new BarDataset().backgroundColor("rgba(30,144,255,0.5)").label("Dataset 3"))
                .and()
                .options()
                .responsive(true)
                .title()
                .display(true)
                .text("Chart.js Horizontal Bar Chart")
                .and()
                .elements()
                .rectangle()
                .borderWidth(4)
                .borderColor("rgb(30,144,255)")
                .borderSkipped(Rectangle.RectangleEdge.LEFT)
                .and()
                .and()
                .legend()
                .fullWidth(true)
                .position(Position.RIGHT)
                .and()
                .done();

        List<String> labels1 = barConfig.data().getLabels();
        for (Dataset<?, ?> ds : barConfig.data().getDatasets()) {
            BarDataset lds = (BarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels1.size(); i++) {
                data.add((double) (Math.round(Math.random() * 100)));
            }
            lds.dataAsList(data);
        }

        ChartJs chart1 = new ChartJs(barConfig);
        chart1.setJsLoggingEnabled(true);
        chart1.addClickListener((a,b) -> {
            BarDataset dataset = (BarDataset) barConfig.data().getDatasets().get(a);
            ChartUtils.notification(a, b, dataset);
        });



        addComponent(chart1);
        addComponent(getChart());
        addComponent(getChart());
    }

    public ChartJs getChart() {
        BarChartConfig barConfig = new BarChartConfig();
        barConfig.horizontal();
        barConfig.
                data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new BarDataset().backgroundColor("rgba(30,144,255,0.5)").label("Dataset 1"))
                .addDataset(new BarDataset().backgroundColor("rgba(30,144,255,0.5)").label("Dataset 2").hidden(true))
                .addDataset(new BarDataset().backgroundColor("rgba(30,144,255,0.5)").label("Dataset 3"))
                .and()
                .options()
                .responsive(true)
                .title()
                .display(true)
                .text("Chart.js Horizontal Bar Chart")
                .and()
                .elements()
                .rectangle()
                .borderWidth(4)
                .borderColor("rgb(30,144,255)")
                .borderSkipped(Rectangle.RectangleEdge.RIGHT)
                .and()
                .and()
                .legend()
                .fullWidth(true)
                .position(Position.RIGHT)
                .and()
                .done();

        List<String> labels1 = barConfig.data().getLabels();
        for (Dataset<?, ?> ds : barConfig.data().getDatasets()) {
            BarDataset lds = (BarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels1.size(); i++) {
                data.add((double) (Math.round(Math.random() * 100)));
            }
            lds.dataAsList(data);
        }

        ChartJs chart1 = new ChartJs(barConfig);
        chart1.setJsLoggingEnabled(true);
        chart1.addClickListener((a,b) -> {
            BarDataset dataset = (BarDataset) barConfig.data().getDatasets().get(a);
            ChartUtils.notification(a, b, dataset);
        });


        return chart1;
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
