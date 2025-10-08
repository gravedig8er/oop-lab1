package lab1;

import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userRequest = scanner.nextLine();

        Request request = new Request(userRequest); // создали наш класс взаимодействия с запросом
        request.urlEncode();

        List<WikiArticle> results = WikiSearch.search(request.toString());

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
        }

        System.out.println("Результаты: ");
        for (int i = 0; i < results.size(); i++) {
            System.out.println(i + 1 + " " + results.get(i).getTitle());
        }

        System.out.println("Выберите статью: ");
        int choice = scanner.nextInt();
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