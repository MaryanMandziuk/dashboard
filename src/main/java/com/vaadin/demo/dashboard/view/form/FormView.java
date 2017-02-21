package com.vaadin.demo.dashboard.view.form;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
/**
 * Created by maryan on 16.02.17.
 */
public class FormView extends Panel implements View {

    private final VerticalLayout root;

    public FormView() {
        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();


        String map = "name:text:default name\n" +
                    "description:textarea:default description\n" +
                    "date:date:24.05.2017\n" +
                    "options:combo:val1,val2,val3\n";
        root = new ParseMap(map);
        setContent(root);
        Responsive.makeResponsive(root);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
