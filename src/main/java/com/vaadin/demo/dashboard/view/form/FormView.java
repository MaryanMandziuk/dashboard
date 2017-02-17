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
        root = new VerticalLayout();;
//        root.setSizeFull();
        root.setMargin(true);
        root.addStyleName("dashboard-view");
        setContent(root);
        Responsive.makeResponsive(root);


        root.addComponent(new Field("name","text", "default name"));
        root.addComponent(new Field("description","textarea", "default description"));
        root.addComponent(new Field("date","date", "22.06.2017"));
        root.addComponent(new Field("options","combo", "val1,val2,val3"));
//        root.addComponent(vertical1);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
