package com.vaadin.demo.dashboard.view.form;

import com.vaadin.server.Responsive;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by maryan on 18.02.17.
 */
public class ParseMap extends VerticalLayout{

    public ParseMap(final String map) {
        setSizeFull();
        parse(map);
        setMargin(true);
        addStyleName("dashboard-view");
    }

    private void parse(String map) {
        String byline[] = map.split("\n");
        for(int i = 0; i < byline.length; i++) {
            String byValue[] = byline[i].split(":");
            addComponent(new Field(byValue[0], byValue[1], byValue[2]));
        }
    }
}
