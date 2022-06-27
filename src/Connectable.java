import java.util.ArrayList;

public interface Connectable {
    void connectOn();
    void connectOff();
    boolean connectTest();
    String readElementByIndex(int byIndex);
    boolean checkElement(String byKey) throws Exception;
    String readElementByKey(String byKey) throws Exception;
    ArrayList<DataAccess> setOfElements(int start, int end) throws Exception;
    int elementsAmount() throws Exception;
    void addInData(String key, String value) throws Exception;
    void updateValueByIndex(int index, String newValue) throws Exception;
    void updateContentByKey(String key, String newValue) throws Exception;
}
