package edu.esprit.kaddem.dto.search;

public class SearchRequest {


    private int page;
    private int size;

    public SearchRequest() {
    }

    public SearchRequest(String query, int page, int size) {
        this.page = page;
        this.size = size;
    }



    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

