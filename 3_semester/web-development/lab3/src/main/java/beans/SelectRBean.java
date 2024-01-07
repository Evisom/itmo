package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class SelectRBean implements Serializable {
    private boolean selected1;
    private boolean selected2;
    private boolean selected3;
    private boolean selected4;
    private boolean selected5;

    private SelectR lastSelected;

    public SelectRBean() {
        lastSelected = SelectR.UNSELECTED;
    }

    public boolean isSelected1() {
        return selected1;
    }


    public void setSelected1(boolean selected1) {
        this.selected1 = selected1;
    }

    public void toggleSelected1() {
        this.selected1 = !this.selected1;
        checkboxValueChanged(); // call to update lastSelected
    }

    public boolean isSelected2() {
        return selected2;
    }

    public void setSelected2(boolean selected2) {
        this.selected2 = selected2;
    }

    public void toggleSelected2() {
        this.selected2 = !this.selected2;
        checkboxValueChanged(); // call to update lastSelected
    }

    public boolean isSelected3() {
        return selected3;
    }

    public void setSelected3(boolean selected3) {
        this.selected3 = selected3;
    }

    public void toggleSelected3() {
        this.selected3 = !this.selected3;
        checkboxValueChanged(); // call to update lastSelected
    }

    public boolean isSelected4() {
        return selected4;
    }

    public void setSelected4(boolean selected4) {
        this.selected4 = selected4;
    }

    public void toggleSelected4() {
        this.selected4 = !this.selected4;
        checkboxValueChanged(); // call to update lastSelected
    }

    public boolean isSelected5() {
        return selected5;
    }

    public void setSelected5(boolean selected5) {
        this.selected5 = selected5;
    }

    public void toggleSelected5() {
        this.selected5 = !this.selected5;
        checkboxValueChanged(); // call to update lastSelected
    }
    public SelectR getLastSelected() {
        return lastSelected;
    }

    public double getValue() {
        if (lastSelected.getValue() == null) {
            return 0.0; // Default value if no selection is made
        }
        return lastSelected.getValue();
    }

    public void checkboxValueChanged() {
        if (isSelected1() && lastSelected.ordinal() == 0) setSelected1(false);
        if (isSelected2() && lastSelected.ordinal() == 1) setSelected2(false);
        if (isSelected3() && lastSelected.ordinal() == 2) setSelected3(false);
        if (isSelected4() && lastSelected.ordinal() == 3) setSelected4(false);
        if (isSelected5() && lastSelected.ordinal() == 4) setSelected5(false);


        if (isSelected1()) lastSelected = SelectR.P1;
        else if (isSelected2()) lastSelected = SelectR.P2;
        else if (isSelected3()) lastSelected = SelectR.P3;
        else if (isSelected4()) lastSelected = SelectR.P4;
        else if (isSelected5()) lastSelected = SelectR.P5;
        else lastSelected = SelectR.UNSELECTED;
    }

    public void validateSelectX(FacesContext context, UIComponent component, Object value) {
        if (lastSelected.getValue() == null) {
            FacesMessage message = new FacesMessage("Please, select at least one checkbox!");
            throw new ValidatorException(message);
        }
    }

}
