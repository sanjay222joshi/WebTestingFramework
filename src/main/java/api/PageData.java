package api;

import java.util.List;

/**
 * Created by jorge on 22-12-2015.
 */
public interface PageData {
    String getURL();
    String getCanonical();
    String getTitle();
    String getMetaDescription();
    String getBreadcrumbsText();
    List<String> getBreadcrumbs();
}
