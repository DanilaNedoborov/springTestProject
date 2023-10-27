package searchengine.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import searchengine.config.Site;

import java.net.URL;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

@Service
public class StartSiteCrawler implements Runnable{
    private Site site;

    public StartSiteCrawler(Site site) {
        this.site = site;
    }

    @SneakyThrows
    @Override
    public void run() {
        int core = Runtime.getRuntime().availableProcessors(); /*получаем количество ядер компьютера*/
        ForkJoinPool pool = new ForkJoinPool(core);
        TreeSite tree = new TreeSite(new URL(site.getUrl()));
        SiteСrawler siteСrawler = new SiteСrawler(tree, tree);
        ForkJoinTask<?> task = pool.submit(siteСrawler);
        task.invoke();
        infoSite infoSite = new infoSite();
        infoSite.addListSiteMap(tree);
        infoSite.addRecordsDB(site);
    }
}
