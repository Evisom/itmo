package model;

import model.Result;
import java.util.ArrayList;
import java.io.Serializable;


public class ResultsList implements Serializable {
    public ArrayList<Result> results;

    public ResultsList() {
        this.results = new ArrayList<Result>();
    }

    public void addResult(Result result) {
        this.results.add(0, result);
    }
    public ArrayList<Result> getResults() {
        return this.results;
    }
}
