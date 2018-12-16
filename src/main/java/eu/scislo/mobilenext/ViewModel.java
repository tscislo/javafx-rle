package eu.scislo.mobilenext;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ViewModel {

    private SimpleStringProperty source = new SimpleStringProperty();
    private SimpleStringProperty result = new SimpleStringProperty();

    private ObjectProperty<Mode> mode = new SimpleObjectProperty<>();

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


    public Mode getMode() {
        return mode.get();
    }

    public ObjectProperty<Mode> modeProperty() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode.set(mode);
    }

    public void modeEnumToggle() {
        mode.setValue((mode.getValue() == Mode.CODE) ? Mode.DECODE : Mode.CODE);
    }

}
