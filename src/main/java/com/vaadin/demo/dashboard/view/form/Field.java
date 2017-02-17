package com.vaadin.demo.dashboard.view.form;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maryan on 16.02.17.
 */
public class Field extends HorizontalLayout {


    public Field(String id, String type, String defaultValue) {
        setWidth("100%");
        Component comp = chooseType(type, defaultValue);
        comp.setWidth("90%");
        setMargin(true);
        Label label = new Label(id);
        label.setWidth("100%");


        Label icon = new Label(
                FontAwesome.QUESTION_CIRCLE.getHtml(),
                ContentMode.HTML);
        icon.setWidth("100%");


        addComponent(label);
        setComponentAlignment(label, Alignment.TOP_CENTER);
        addComponent(comp);

        addComponent(icon);
        setComponentAlignment(icon, Alignment.TOP_LEFT);
        setExpandRatio(label, 2.0f   );
        setExpandRatio(comp, 4.0f   );
        setExpandRatio(icon, 1.0f   );

    }

    private Component chooseType(String type, String defaultValue) {
        Component comp = null;
        switch (type) {
            case "text": TextField textField = new TextField();
                textField.setValue(defaultValue);
                comp = textField;
                break;
            case "textarea": TextArea textArea = new TextArea();
                textArea.setValue(defaultValue);
                comp = textArea;
                break;
            case "date": DateField dateField = new DateField();
                try {
                    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                    Date date =  df.parse(defaultValue);
                    dateField.setValue(date);
                    comp = dateField;
                } catch (ParseException ex) {

                }
                break;
            case "combo": ComboBox comboBox = new ComboBox();
                String[] values = defaultValue.split(",");
                for(int i = 0; i < values.length; i++) {
                    Object itemId = comboBox.addItem();
                    comboBox.setItemCaption(itemId, values[i]);
                    comboBox.setValue(itemId);
                }

                comp = comboBox;
                break;
        }
        return comp;
    }
}
