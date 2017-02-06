package com.vaadin.demo.dashboard.view.charts;

import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.config.PieChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.data.PieDataset;
import com.byteowls.vaadin.chartjs.options.Hover;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.Tooltips;
import com.byteowls.vaadin.chartjs.options.elements.Rectangle;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.CategoryScale;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.byteowls.vaadin.chartjs.ChartJs;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by maryan on 03.02.17.
 */
public class ChartsView extends Panel implements View {

    private CssLayout dashboardPanels;
    private final VerticalLayout root;

    public ChartsView() {
        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();
        root = new VerticalLayout();;
        root.setSizeFull();
        root.setMargin(true);
        root.addStyleName("dashboard-view");
        setContent(root);
        Responsive.makeResponsive(root);

        Component content = buildContent();
        root.addComponent(content);
        root.setExpandRatio(content, 1);

    }

    private Component buildContent() {
        dashboardPanels = new CssLayout();
        dashboardPanels.addStyleName("dashboard-panels");
        Responsive.makeResponsive(dashboardPanels);

        dashboardPanels.addComponent(createContentWrapper(getPie()));
        dashboardPanels.addComponent(createContentWrapper(getBar()));
        dashboardPanels.addComponent(createContentWrapper(getSimpleLine()));
        dashboardPanels.addComponent(createContentWrapper(getStackedBar()));
        return dashboardPanels;
    }



    private Component getBar() {
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

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.addClickListener((a,b) -> {
            BarDataset dataset = (BarDataset) barConfig.data().getDatasets().get(a);
            ChartUtils.notification(a, b, dataset);
        });

//        chart.setWidth(100, Unit.PERCENTAGE);
        chart.setSizeFull();

        return chart;
    }

    private Component getSimpleLine() {
        LineChartConfig lineConfig = new LineChartConfig();
        lineConfig.data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new LineDataset().label("My First dataset").fill(false))
                .addDataset(new LineDataset().label("My Second dataset").fill(false))
                .addDataset(new LineDataset().label("Hidden dataset").hidden(true))
                .and()
                .options()
                .responsive(true)
                .title()
                .display(true)
                .text("Chart.js Line Chart")
                .and()
                .tooltips()
                .mode(Tooltips.Mode.LABEL)
                .and()
                .hover()
                .mode(Hover.Mode.DATASET)
                .and()
                .scales()
                .add(Axis.X, new CategoryScale()
                        .display(true)
                        .scaleLabel()
                        .display(true)
                        .labelString("Month")
                        .and()
                        .position(Position.TOP))
                .add(Axis.Y, new LinearScale()
                        .display(true)
                        .scaleLabel()
                        .display(true)
                        .labelString("Value")
                        .and()
                        .ticks()
                        .suggestedMin(-10)
                        .suggestedMax(250)
                        .and()
                        .position(Position.RIGHT))
                .and()
                .done();

        List<String> labels = lineConfig.data().getLabels();
        for (Dataset<?, ?> ds : lineConfig.data().getDatasets()) {
            LineDataset lds = (LineDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) Math.round(Math.random() * 100));
            }
            lds.dataAsList(data);
            lds.borderColor(ChartUtils.randomColor(0.3));
            lds.backgroundColor(ChartUtils.randomColor(0.5));
        }

        ChartJs chart = new ChartJs(lineConfig);
        chart.addClickListener((a,b) -> {
            LineDataset dataset = (LineDataset) lineConfig.data().getDatasets().get(a);
            ChartUtils.notification(a, b, dataset);
        });
        chart.setWidth(100, Unit.PERCENTAGE);
//        chart.setSizeFull();

        return chart;
    }

    private Component getPie() {
        PieChartConfig config = new PieChartConfig();
        config
                .data()
                .labels("Red", "Green", "Yellow", "Grey", "Dark Grey")
                .addDataset(new PieDataset().label("Dataset 1"))
                .and();

        config.
                options()
                .responsive(true)
                .title()
                .display(true)
                .text("Chart.js Pie Chart (Data Refresh)")
                .and()
                .animation()
                //.animateScale(true)
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
        chart.addClickListener((a,b) -> {
            PieDataset dataset = (PieDataset) config.data().getDatasets().get(a);
            ChartUtils.notification(a, b, dataset);
        });


//        chart.setWidth(100, Unit.PERCENTAGE);
        chart.setSizeFull();
        return chart;
    }

    private Component getStackedBar() {
        BarChartConfig config = new BarChartConfig();
        config.data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new BarDataset().label("Dataset 1").backgroundColor("rgba(220,220,220,0.5)"))
                .addDataset(new BarDataset().label("Dataset 2").backgroundColor("rgba(151,187,205,0.5)"))
                .addDataset(new BarDataset().label("Dataset 3").backgroundColor("rgba(151,187,145,0.5)"))
                .and()
                .options()
                .responsive(true)
                .title()
                .display(true)
                .text("Chart.js Bar Chart - Stacked")
                .and()
                .tooltips()
                .mode(Tooltips.Mode.LABEL)
                .and()
                .scales()
                .add(Axis.X, new DefaultScale()
                        .stacked(true))
                .add(Axis.Y, new DefaultScale()
                        .stacked(true))
                .and()
                .done();

        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            BarDataset lds = (BarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.random() > 0.5 ? -1 : 1) * Math.round(Math.random() * 100));
            }
            lds.dataAsList(data);
        }

        ChartJs chart = new ChartJs(config);
        chart.addClickListener((a, b) -> {
            BarDataset dataset = (BarDataset) config.data().getDatasets().get(a);
            ChartUtils.notification(a, b, dataset);
        });
//        chart.setWidth(100, Unit.PERCENTAGE);
        chart.setSizeFull();
        return chart;
    }


    private Component createContentWrapper(final Component content) {
        final CssLayout slot = new CssLayout();
        slot.setWidth("100%");
        slot.addStyleName("dashboard-panel-slot");

        CssLayout card = new CssLayout();
        card.setWidth("100%");
        card.addStyleName(ValoTheme.LAYOUT_CARD);

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.addStyleName("dashboard-panel-toolbar");
        toolbar.setWidth("100%");

        Label caption = new Label(content.getCaption());
        caption.addStyleName(ValoTheme.LABEL_H4);
        caption.addStyleName(ValoTheme.LABEL_COLORED);
        caption.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        content.setCaption(null);

        MenuBar tools = new MenuBar();
        tools.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
        MenuBar.MenuItem max = tools.addItem("", FontAwesome.EXPAND, new MenuBar.Command() {

            @Override
            public void menuSelected(final MenuBar.MenuItem selectedItem) {
                if (!slot.getStyleName().contains("max")) {
                    selectedItem.setIcon(FontAwesome.COMPRESS);
                    toggleMaximized(slot, true);
                } else {
                    slot.removeStyleName("max");
                    selectedItem.setIcon(FontAwesome.EXPAND);
                    toggleMaximized(slot, false);
                }
            }
        });
        max.setStyleName("icon-only");
        MenuBar.MenuItem root = tools.addItem("", FontAwesome.COG, null);
        root.addItem("Configure", new MenuBar.Command() {
            @Override
            public void menuSelected(final MenuBar.MenuItem selectedItem) {
                Notification.show("Not implemented in this demo");
            }
        });
        root.addSeparator();
        root.addItem("Close", new MenuBar.Command() {
            @Override
            public void menuSelected(final MenuBar.MenuItem selectedItem) {
                Notification.show("Not implemented in this demo");
            }
        });

        toolbar.addComponents(caption, tools);
        toolbar.setExpandRatio(caption, 1);
        toolbar.setComponentAlignment(caption, Alignment.MIDDLE_LEFT);

        card.addComponents(toolbar, content);
        slot.addComponent(card);
        return slot;
    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    private void toggleMaximized(final Component panel, final boolean maximized) {
        for (Iterator<Component> it = root.iterator(); it.hasNext();) {
            it.next().setVisible(!maximized);
        }
        dashboardPanels.setVisible(true);

        for (Iterator<Component> it = dashboardPanels.iterator(); it.hasNext();) {
            Component c = it.next();
            c.setVisible(!maximized);
        }

        if (maximized) {
            panel.setVisible(true);
            panel.addStyleName("max");
        } else {
            panel.removeStyleName("max");
        }
    }
}
