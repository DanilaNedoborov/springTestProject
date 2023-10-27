package searchengine.services;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import searchengine.config.Site;
import searchengine.model.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class infoSite {
    private List<String> treeSiteList = new ArrayList<>();
    @Autowired
    private SiteTableRepository siteTableRepository;
    @Autowired
    private PageTableRepository pageTableRepository;

    public void addListSiteMap(TreeSite tree) {

        treeSiteList.add(tree.getUrl().toString());

        for (TreeSite branch : tree.getLink()) {
            addListSiteMap(branch);
        }
    }
//    TODO adding records to the database PageTable
    public void addRecordsDB(Site site) throws IOException {
        SiteTable siteTable = new SiteTable();// создание экземпляра объекта SiteTable
        siteTable.setName(site.getName());
        siteTable.setUrl(site.getUrl());
        String datePattern = "MM-dd-yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        String dateTime = simpleDateFormat.format(new Date());
        for(int i = 0; i < treeSiteList.size(); i++){
            PageTable pageTable = new PageTable(); // создание экземпляра объекта PageTable
            URL url = new URL(treeSiteList.get(i));
//            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
            Connection.Response response = Jsoup.connect(url.toString())
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
                    .timeout(10000)
                    .execute();
            int httpStatus = response.statusCode();
            if ((httpStatus == HttpStatus.OK.value())){ //Если HTTP статус 200, то происходит заполнение БД SiteTable и PageTable
                siteTable.setStatus(Status.INDEXING); //Изменение статуса во время записи
                siteTable.setStatusTime(dateTime);
                String content = !response.body().isEmpty() ? response.body() : "Non content"; //Если тело
                /*
                    Write in DB PageTable
                */
            }else {
                siteTable.setStatus(Status.FAILED);
                siteTable.setLastError("!Error! Http status = " + httpStatus);
            }

            //System.out.println("Content \n" + connection1.getContent().toString());
        }
        siteTable.setStatus(Status.INDEXED); //изменение статуса после завершения
        siteTableRepository.save(siteTable);
    }
}
