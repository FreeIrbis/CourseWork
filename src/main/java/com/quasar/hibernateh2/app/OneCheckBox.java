package com.quasar.hibernateh2.app;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Irbis
 */
public class OneCheckBox {

    public static Long selectOneCheckBox(CheckBox chk1, CheckBox chk2) {
        Long check = null;
        if (chk1.isSelected() == true) {
            check = 1L;
        }
        if (chk2.isSelected() == true) {
            check = 2L;
        }
        return check;
    }

    public static void chekedOneCheckBox(CheckBox chk1, CheckBox chk2) {
        if (chk1.isFocused()) {
            chk1.setSelected(true);
            chk2.setSelected(false);
        } else {
            chk2.setSelected(true);
            chk1.setSelected(false);
        }
    }
}
