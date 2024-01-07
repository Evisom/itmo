package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Objects;

@Named
@SessionScoped
public class SelectXBean implements Serializable {
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
                .add("drawGraphByR(" + (value == null ? 0 : value) + ");");
    }

    public void validateSelectX(FacesContext facesContext,
                                UIComponent uiComponent, Object o) {
        if (o == null) {
            value = null;
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
                    .add("drawGraphByR(0);");
            FacesMessage message = new FacesMessage("Please, select value!");
            throw new ValidatorException(message);
        }
    }

}