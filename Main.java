import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Reader readfile = new Reader();
        Scanner scanner = new Scanner(System.in);
        boolean isRun = true;
        while (isRun) {
            System.out.println("Введите путь до файла, 'exit' для выхода:");
            String filePath = scanner.nextLine();
            if (filePath.equalsIgnoreCase("exit")) {
                System.out.println("Выход!");
                isRun = false;
                continue;
            }
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Файла не сущетсвует!");
                continue;
            }
            try {
                long start = System.currentTimeMillis();
                List<City> cities = null;
                if (filePath.endsWith(".csv")) {
                    cities = readfile.csvRead(filePath);
                } else if (filePath.endsWith(".xml")) {
                    cities = readfile.xmlRead(filePath);
                } else {
                    System.out.println("Другой формат выбирите!");
                    continue;
                }
                readfile.statsOut(cities);
                long end = System.currentTimeMillis();
                System.out.println("Время обработки: " + (end - start) + " мс");
            } catch (IOException e) {
                System.out.println("Ошибка при обработке файла! " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ошибка! " + e.getMessage());
            }
        }
        scanner.close();
    }
}
