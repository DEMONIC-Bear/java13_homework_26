import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseInteraction implements Connectable {
    ArrayList<DataAccess> elements;
    boolean connect = false;

    public DatabaseInteraction() {
        DataAccess[] arr = FileService.readFile();
        elements = new ArrayList<>();
        elements.addAll(Arrays.asList(arr));
    }
    @Override
    public void connectOn() {
        connect = true;
        System.out.println("Connection open!");
    }
    @Override
    public void connectOff() {
        connect = false;
        System.out.println("Connection closed");
    }
    @Override
    public boolean connectTest() {
        return connect;
    }
    @Override
    public String readElementByIndex(int byIndex) {
        try {
            if (!connectTest()) throw new Exception("Connection failed");
            if (byIndex < elements.size()) {
                return elements.get(byIndex).toString();
            } else {
                throw new Exception("There is no element at this index");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public boolean checkElement(String key) throws Exception {
        if (!connectTest()) throw new Exception("Connection failed");
        for (var e : elements) {
            if (e.getKey().equals(key)) return true;
        }
        return false;
    }
    @Override
    public String readElementByKey(String byKey) throws Exception {
        if (!connectTest()) throw new Exception("Connection failed");
        for (var e : elements) {
            if (e.getKey().equals(byKey)) return e.getKey();
        }
        throw new Exception("There is no element at this key");
    }
    @Override
    public ArrayList<DataAccess> setOfElements(int start, int end) throws Exception {
        if (!connectTest()) throw new Exception("Connection failed");
        ArrayList<DataAccess> list = new ArrayList<>();
        if (start > 0 && end < elements.size()) {
            for (int i = start; i < end; i++) {
                list.add(elements.get(i));
            }
        } else {
            throw new Exception("Wrong request");
        }
        return list;
    }
    @Override
    public int elementsAmount() throws Exception {
        if (!connectTest()) throw new Exception("Connection failed");
        return elements.size();
    }
    @Override
    public void addInData(String key, String value) throws Exception {
        if (!connectTest()) throw new Exception("Connection failed");
        if (!checkElement(key)) {
            DataAccess d = new DataAccess(key, value);
            elements.add(d);
            FileService.writeFile(elements);
            System.out.println("You have successfully added a new entry");
        }
    }
    @Override
    public void updateValueByIndex(int index, String newValue) throws Exception {
        if (!connectTest()) throw new Exception("Connection failed");
        try {
            if (index < elements.size()) {
                elements.get(index).setValue(newValue);
                FileService.writeFile(elements);
                System.out.println("Key value at index " + index + " successfully updated to " + newValue);
            } else {
                throw new Exception("There is no element at this index");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void updateContentByKey(String key, String newValue) throws Exception {
        if (!connectTest()) throw new Exception("Connection failed");
        try {
            if (checkElement(key)) {
                for (DataAccess d : elements) {
                    if (d.getKey().equals(key)) d.setValue(newValue);
                    FileService.writeFile(elements);
                }
                System.out.println("Key " + key + " content successfully updated to " + newValue);
            } else throw new Exception("There is no element at this key");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (DataAccess d : elements) {
            s.append(d.toString());
        }
        return s.toString();
    }
}
