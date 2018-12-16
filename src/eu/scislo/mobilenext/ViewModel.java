package eu.scislo.mobilenext;

import javafx.beans.property.SimpleStringProperty;

public class ViewModel {

    private SimpleStringProperty source = new SimpleStringProperty();
    private SimpleStringProperty result = new SimpleStringProperty();

    public String getSource() {
        return source.get();
    }

    public SimpleStringProperty sourceProperty() {
        return source;
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public String getResult() {
        return result.get();
    }

    public SimpleStringProperty resultProperty() {
        return result;
    }

    public void setResult(String result) {
        this.result.set(result);
    }
}
