package searchengine.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import searchengine.config.Site;
import searchengine.model.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class infoSite {
    private List<String> treeSiteList = new ArrayList<>();

    private Site site;
    private SiteTableRepository siteTableRepository;
    private PageTableRepository pageTableRepository;


    public infoSite(Site site){
        this.site = site;
    }
    public void addListSiteMap(TreeSite tree) {

        treeSiteList.add(tree.getUrl().toString());

        for (TreeSite branch : tree.getLink()) {
            addListSiteMap(branch);
        }
    }
    public void printDetailInfoSite () throws IOException {
        SiteTable siteTable = new SiteTable();
        siteTable.setName(site.getName());
        siteTable.setUrl(site.getUrl());
        String datePattern = "MM-dd-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        for(int i = 0; i < treeSiteList.size(); i++){
            PageTable pageTable = new PageTable();
            String dateTime = simpleDateFormat.format(new Date());
            URL url = new URL(treeSiteList.get(i));
            HttpURLConnection connection1 = (HttpURLConnection)url.openConnection();
            connection1.setRequestMethod("GET");
            connection1.connect();
            int httpStatus = connection1.getResponseCode();
            if ((httpStatus == HttpStatus.OK.value())){
                siteTable.setStatus(Status.INDEXING);
                siteTable.setStatusTime(dateTime);
                String content = connection1.getContentLength() > 0 ? connection1.getContent().toString() : "Non content";

            }else {
                Logger.getLogger("serviceLog_infoSite").info("!Error! Http status = " + httpStatus);
                siteTable.setStatus(Status.FAILED);
                siteTable.setLastError("!Error! Http status = " + httpStatus);

            }

            //System.out.println("Content \n" + connection1.getContent().toString());
        }
        siteTable.setStatus(Status.INDEXED); //изменение на статуса
        siteTableRepository.save(siteTable);
//        Connection connection = Jsoup.connect(treeSiteList.get(1).toString())
//                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//                .referrer("http://www.google.com");
//        Connection.Response response = connection.execute();

        //System.out.println(document.html());
    }
}
