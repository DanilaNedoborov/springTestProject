package searchengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import searchengine.config.Site;
import searchengine.config.SitesList;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.model.PageTableRepository;
import searchengine.model.SiteTableRepository;
import searchengine.services.SiteСrawler;
import searchengine.services.StartSiteCrawler;
import searchengine.services.StatisticsService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final StatisticsService statisticsService;
    @Autowired
    private SitesList sitesList;

    public ApiController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @GetMapping("/startIndexing")
    public void startIndexing() {
        for(Site site: sitesList.getSites()){
            StartSiteCrawler startSiteCrawler = new StartSiteCrawler(site);
            new Thread(startSiteCrawler).start();
        }
    }
}
