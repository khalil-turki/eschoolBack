package edu.esprit.kaddem.dto.search.util;

import edu.esprit.kaddem.dto.search.PageRequest;
import edu.esprit.kaddem.dto.search.SearchRequest;

public class SearchRequestUtil {
    private static final int DEFAULT_PAGE_SIZE = 100;
    private SearchRequestUtil() {}

    public static PageRequest toPageRequest(final SearchRequest request){
        if(request == null){
            return new PageRequest(0, DEFAULT_PAGE_SIZE);
        }
        final int requestedSize = request.getSize();
        return new PageRequest(request.getPage(), requestedSize == 0 ? DEFAULT_PAGE_SIZE : requestedSize);
    }

}
