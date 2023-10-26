package searchengine.services;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreeSite {
    private URL url;
    private List<TreeSite> link = new ArrayList();
    public TreeSite(){}
    public TreeSite(URL url){
        this.url = url;
    }
    public URL getUrl() {
        return url;
    }
    public void addLink(TreeSite newBranch) {
        link.add(newBranch);
    }
    public List<TreeSite> getLink() {
        return link;
    }
}

