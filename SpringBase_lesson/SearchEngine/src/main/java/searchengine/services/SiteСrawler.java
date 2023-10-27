package searchengine.services;

import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.RecursiveAction;

@Service
@Setter
@Getter
public class SiteСrawler extends RecursiveAction{
    public SiteСrawler(){}
    TreeSite tree = new TreeSite();
    TreeSite branch = new TreeSite();

    public SiteСrawler(TreeSite tree, TreeSite branch){
        this.tree = tree;
        this.branch = branch;
    }
    private Set<String> text = new TreeSet<>();
    public void addHref(TreeSite newTree) throws IOException, InterruptedException {

        Thread.sleep(200);

        URL url = newTree.getUrl();

//        Set<URL> list = new TreeSet<>(Comparator.comparing(URL::toString));
        Document doc = Jsoup.connect(url.toString()).get();
        Elements href = doc.select("a[href]");
        for (Element e : href) {
            String linkText = e.attr("abs:href");
            if (linkText.isBlank()) {
                continue;
            }
            try {
                URL link = new URL(linkText);
                if (link.getHost().equals(url.getHost())) {
                    String absPath = link.getProtocol() + "://" + link.getHost() + link.getPath();
                    if (absPath.charAt(absPath.length() - 1) == '/') {
                        absPath = absPath.substring(0, absPath.length() - 1);
                    }
                    URL urlPath = new URL(absPath);
                    if (isComplies(urlPath,tree)) {
                        TreeSite newBranch = new TreeSite(urlPath);
                        newTree.addLink(newBranch);
                    }
                }
            } catch (Exception ignored) {}
        }
    }

    @Override
    protected void compute() {
        try {
            List<SiteСrawler> taskList = new ArrayList<>();
            addHref(branch);
            for (TreeSite newTree : branch.getLink()) {
                SiteСrawler parser = new SiteСrawler(tree, newTree);
                parser.fork();
                parser.addHref(newTree);
                taskList.add(parser);
            }
            for (SiteСrawler task: taskList){
                task.join();
            }
        } catch (Exception ignored) {}
    }
    private boolean isComplies(URL url, TreeSite testTree) {
        if (testTree.getUrl().equals(url)) {
            return false;
        }
        for (TreeSite link : testTree.getLink()) {
            if (link.getUrl().equals(url)) {
                return false;
            }
            if (!isComplies(url, link)) {
                return false;
            }
        }
        return true;
    }

}

