package lab1;

import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите запрос: ");
        String userRequest = scanner.nextLine();

        Request request = new Request(userRequest); // создали наш класс взаимодействия с запросом
        request.urlEncode();

        List<WikiArticle> results = WikiSearch.search(request.toString());

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
            return;
        }

        System.out.println("Результаты: ");
        for (int i = 0; i < results.size(); i++) {
            System.out.println(i + 1 + " " + results.get(i).getTitle());
        }


        while (true) {
            System.out.print("Выберите статью: (введите 0 если хотите завершить программу) ");
            String input = scanner.nextLine().trim();
            if (!input.matches("\\d+")) {
                System.out.println("Введи число!");
                continue;
            }

            int choice = Integer.valueOf(input);
            if (choice == 0) break;
            if (choice > results.size() || choice < 0) {
                System.out.println("Неверный номер, введите заново");
            }
            else {
                int pageId = results.get(choice - 1).getPageId();


                String articleUrl = "https://ru.wikipedia.org/w/index.php?curid=" + pageId;
                try {
                    Desktop.getDesktop().browse(new URI(articleUrl));
                }
                catch (Exception e) {
                    System.out.println("Ошибка при открытии браузера: " + e.getMessage());
                }
            }

        }

    }
}