package lab1;

import java.net.URLEncoder;
/*
* Класс хранит запрос пользователя и кодирует его в определеннный формат
* */
public class Request {
    private String userRequest;
    private String encodeURL;

    public Request(String userRequest) {
        this.userRequest = userRequest;
    }

    public void urlEncode() {
        try {
            encodeURL = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch="
                    + URLEncoder.encode(userRequest, "UTF-8");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return this.encodeURL;
    }
}
