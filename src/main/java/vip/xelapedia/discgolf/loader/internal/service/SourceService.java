package vip.xelapedia.discgolf.loader.internal.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.xelapedia.discgolf.loader.internal.entity.Source;
import vip.xelapedia.discgolf.loader.internal.entity.SourcePage;
import vip.xelapedia.discgolf.loader.internal.repository.PageRepository;


@Service
@AllArgsConstructor
@Slf4j
public class SourceService {
    private final PageRepository pageRepository;

    @Transactional
    public void persistPage(final SourcePage sourcePage) {
        pageRepository.save(sourcePage);
    }

    @Transactional
    public void clearPageDataForSource(final Source source) {
        pageRepository.deleteBySource(source);
    }
}
