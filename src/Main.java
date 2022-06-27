import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseInteraction base = new DatabaseInteraction();
        boolean check = true;
        while (check) {
            System.out.println("""
                    =========================================================================
                    What do you want to do?
                    -------------------------------------------------------------------------
                    [1] - Open connect              [2] - Close connect
                    [3] - Check connect             [4] - Read one string by index
                    [5] - Check 1 record by key     [6] - Print one record by key
                    [7] - Read array of record      [8] - Number of records in the database
                    [9] - Add record in base        [10] - Update record value by index
                    [11] - Update record value by key
                    =========================================================================""");
            int num = new Scanner(System.in).nextInt();
            try {
                if (num < 1 && num > 11) throw new RuntimeException();
                switch (num) {
                    case 1:
                        base.connectOn();
                        break;
                    case 2:
                        base.connectOff();
                        break;
                    case 3:
                        if (!base.connectTest()) {
                            System.out.println("Not connected");
                        } else {
                            System.out.println("Connected");
                        }
                        base.connectTest();
                        break;
                    case 4:
                        System.out.println("What line do you want to count?");
                        int stringByIndex = new Scanner(System.in).nextInt() - 1;
                        try {
                            if (stringByIndex < FileService.readFile().length && stringByIndex > FileService.readFile().length)
                                throw new RuntimeException();
                            System.out.println(base.readElementByIndex(stringByIndex));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Which record do you want to check by key");
                        String checkRecordByKey = new Scanner(System.in).nextLine();
                        if (!base.checkElement(checkRecordByKey)) {
                            System.out.println("No such key exists");
                        } else {
                            System.out.println("Such a key exists");
                        }
                        break;
                    case 6:
                        System.out.println("Which record to read by key");
                        String readOneRecordByKey = new Scanner(System.in).nextLine();
                        System.out.println(base.readElementByKey(readOneRecordByKey));
                        break;
                    case 7:
                        System.out.println("Enter the start index");
                        int start = new Scanner(System.in).nextInt() - 1;
                        System.out.println("Enter the end index");
                        int end = new Scanner(System.in).nextInt() - 1;
                        try {
                            if (start < FileService.readFile().length && start > FileService.readFile().length ||
                                    end < FileService.readFile().length && end > FileService.readFile().length) {
                                throw new RuntimeException();
                            } else {
                                System.out.println(base.setOfElements(start, end));
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 8:
                        System.out.println(base.elementsAmount());
                        break;
                    case 9:
                        System.out.println("Enter the key for new record");
                        String key = new Scanner(System.in).nextLine();
                        System.out.println("Enter the value for new record");
                        String value = new Scanner(System.in).nextLine();
                        base.addInData(key, value);
                        break;
                    case 10:
                        try {
                            System.out.println("Enter index to update value");
                            int indexToUpdateValue = new Scanner(System.in).nextInt() - 1;
                            if (indexToUpdateValue < FileService.readFile().length && indexToUpdateValue > FileService.readFile().length) {
                                throw new RuntimeException();
                            } else {
                                System.out.println("Enter a value to update");
                                String valueToUpdate = new Scanner(System.in).nextLine();
                                base.updateValueByIndex(indexToUpdateValue, valueToUpdate);
                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 11:
                        System.out.println("Enter key to update value");
                        String keyToUpdateValue = new Scanner(System.in).nextLine();
                        System.out.println("Enter new content");
                        String valueUpdateFromKey = new Scanner(System.in).nextLine();
                        base.updateContentByKey(keyToUpdateValue, valueUpdateFromKey);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                check = false;
            }
        }
    }
}
