package com.vaadin.demo.dashboard.view.form;

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
        Component comp = chooseType(type, defaultValue);
        comp.setSizeFull();
        setSizeFull();
        setMargin(true);
        Label label = new Label(id);
        addComponent(label);
        setComponentAlignment(label, Alignment.BOTTOM_CENTER);
        addComponent(comp);
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
