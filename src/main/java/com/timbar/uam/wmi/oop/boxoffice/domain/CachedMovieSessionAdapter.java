package com.timbar.uam.wmi.oop.boxoffice.domain;

import com.timbar.uam.wmi.oop.boxoffice.database.repo.MovieSessionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CachedMovieSessionAdapter implements MovieSessionAdapter {

    private static final Logger log = LoggerFactory.getLogger(CachedMovieSessionAdapter.class);

    private static final int updateEveryMillisec = 5 * 60 * 1000;

    private final MovieSessionRepo movieSessionRepo;
    private final Map<Integer, MovieSession> sessionIdCache = new ConcurrentHashMap<>();

    public CachedMovieSessionAdapter(MovieSessionRepo movieSessionRepo) {
        this.movieSessionRepo = movieSessionRepo;
    }

    @PostConstruct
    private void createCacheUpdatingThread() {
        Thread cacheUpdater = new Thread(() -> {
            log.debug("Movie session cache updating Thread started");
            while (true) {
                LocalDateTime now = LocalDateTime.now();
                for (MovieSession session : movieSessionRepo.findByStartTimeBetween(now, now.plusDays(10))) {
                    sessionIdCache.put(session.getId(), session);
                }

                try {
                    Thread.sleep(updateEveryMillisec);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cacheUpdater.setDaemon(true);
        cacheUpdater.start();
    }

    @Override
    public MovieSession getById(int id) {
        if (sessionIdCache.containsKey(id)) {
            log.debug("Movie session(id=" + id + ") returned from cache!");
            return sessionIdCache.get(id);
        }
        MovieSession movieSession = movieSessionRepo
                .findById(id)
                .orElseThrow(MovieSessionInfoUnavailableException::new);
        sessionIdCache.put(movieSession.getId(), movieSession);
        return movieSession;
    }

    @Override
    public List<MovieSession> findByStartTimeBetween(LocalDateTime start, LocalDateTime end) {
        return movieSessionRepo.findByStartTimeBetween(start, end);
    }
}
