import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/* ошибки определения данных
 * 1.1 ошибка передачи данных
 * 1.2 ошиюка преобразования данных
 * 1.3 ошибка перезаписи данных
 * 1.4 неправильные данные
 * логические ошибки проектирования
 * 2.1 неприменимый метод
 * 2.2 неверный алгоритм
 * 2.3 неверная структура данных
 * 2.4 другие ошибки проектирования
 * логические ошибки кодирования
 * 3.1 некорректная работа с переменными
 * 3.2 некорректные вычисления
 * 3.3 ошибки межмодульных интерфейсов
 * 3.4 неправильная реализация алгоритма
 * 3.5 другие ошибки кодирования
 * ошиюки накопления погрешностей:
 * 4.1 игнорирование ограничений разрядной сетки
 * 4.2 игнорирование способов уменьшения погрешностт
 */

public class Main {

    // 2.2 неверно считает для строк с одинаковыми символами в конце
    // 3.4 неправильная реализация
    public static int findMaxSequenceLength(String s) {
        // 1.4
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // 3.5 лишняя проверка
        if (s.length() == 1) {
            return 1;
        }
        // 3.1
        int maxLength = 1;
        int currentLength = 1;

        // 4.1
        for (int i = 1; i < s.length(); i++) {
            char current = s.charAt(i);
            char previous = s.charAt(i - 1);

            // 3.2
            if (current != previous) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;  // 3.4
            }
        }
        maxLength = Math.max(maxLength, currentLength);
        return maxLength;
    }

    public static void main(String[] args) {
        // 1.4
        String filePath = "input.txt";
        // 3.1 инициализация пустой строкой
        String content = " ";

        try {
            // 1.1, 1.2, 4.2
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            // 3.3
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            content = "";  // 1.3
        }

        // 2.1
        int result = findMaxSequenceLength(content);

        // 1.4
        System.out.println("Обработанная строка (первые 50 символов): " +
                (content.length() > 50 ? content.substring(0, 50) + "..." : content));
        System.out.println("Общая длина строки: " + content.length());
        System.out.println("Максимальное количество идущих подряд символов с различными соседями: " + result);
    }
}