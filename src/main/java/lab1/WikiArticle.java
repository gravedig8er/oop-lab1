package lab1;

/*
 * Класс представляет собой единицу вики-страницы
 * */

public class WikiArticle {
    private int pageId;
    private String title;
    private String snippet;

    public WikiArticle(int pageId, String title, String snippet) {
        this.pageId = pageId;
        this.title = title;
        this.snippet = snippet;
    }

    public int getPageId() {return pageId;}

    public String getTitle() {return title;}

    public String getSnippet() {return snippet;}

    @Override
    public String toString() {
        return pageId + " " + title + " " + snippet;
    }
}
