import java.util.ArrayList;

public interface Connectable {
    void connectOn();
    void connectOff();
    boolean connectTest();
    String readElementByIndex();
    boolean checkElement();
    String readElementByKey();
    ArrayList<DataAccess> setOfElements(int start, int end);
    int elementsAmount();
    void addInData();
    void updateValueByIndex();
    void updateContentByKey();




}
