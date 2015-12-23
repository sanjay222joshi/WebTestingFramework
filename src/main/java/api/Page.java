package api;

import java.util.List;

/**
 * Created by jorge on 22-12-2015.
 */
public interface Page {
    String getURL();
    String getTitle();
    String getMetaDescription();
    String getBreadCrumbsText();
    List<String> getBreadCrumbs();
}
