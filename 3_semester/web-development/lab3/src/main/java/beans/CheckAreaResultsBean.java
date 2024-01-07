package beans;

import database.DAOFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

@Named
@SessionScoped
public class CheckAreaResultsBean implements Serializable {
    @Inject
    private SelectXBean selectXBean;
    @Inject
    private SelectYBean selectYBean;
    @Inject
    private SelectRBean selectRBean;

    private LinkedList<CheckAreaBean> results;

    public CheckAreaResultsBean() {
        super();
        results = new LinkedList<>();
        try {
            results = new LinkedList<>(DAOFactory.getInstance().getResultDAO().getAllResults());
        } catch (SQLException ignored) {}
    }

    @Named(value = "resultList")
    public LinkedList<CheckAreaBean> getResults() {
        return results;
    }

    public void setResults(LinkedList<CheckAreaBean> results) {
        this.results = results;
    }

    public void newResult(final double x, final double y, final double r) {
        final CheckAreaBean currentResult = new CheckAreaBean();
        final long startExec = System.nanoTime();
        final boolean result = AreaResultChecker.getResult(x, y, r);
        final long endExec = System.nanoTime();
        final long executionTime = endExec - startExec;
        currentResult.setX(x);
        currentResult.setY(y);
        currentResult.setR(r);
        currentResult.setResult(result);
        currentResult.setExecutedAt(LocalDateTime.now());
        currentResult.setExecTime(executionTime);
        try {
            DAOFactory.getInstance().getResultDAO().addNewResult(currentResult);
        } catch (SQLException ignored) {}
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("console.log('!!'); drawPoint(" + x + ", " + y + ", " + r + ", " + result + ");");
        results.addFirst(currentResult);
    }

    public void clearResults() {
        results.clear();
        try {
            DAOFactory.getInstance().getResultDAO().clearResults();
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (SQLException | IOException ignored) {}
    }
}
