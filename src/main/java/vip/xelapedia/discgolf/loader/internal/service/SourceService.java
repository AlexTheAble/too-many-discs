package vip.xelapedia.discgolf.loader.internal.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vip.xelapedia.discgolf.loader.internal.entity.Source;
import vip.xelapedia.discgolf.loader.internal.entity.SourcePage;
import vip.xelapedia.discgolf.loader.internal.enums.InfiniteDiscsPageType;
import vip.xelapedia.discgolf.loader.internal.repository.PageRepository;
import vip.xelapedia.discgolf.loader.internal.repository.SourceRepository;


@Service
@AllArgsConstructor
@Slf4j
public class SourceService {
    private final SourceRepository sourceRepository;
    private final PageRepository pageRepository;

    public Source getSourceByKey(final String key) {
        return sourceRepository.findByKey(key);
    }

    public Page<SourcePage> findAllBySourceAndType(final Source source, final InfiniteDiscsPageType type, final Pageable pageable) {
        return pageRepository.findAllBySourceAndType(source, type.name(), pageable);
    }

    @Transactional
    public void persistPage(final SourcePage sourcePage) {
        pageRepository.save(sourcePage);
    }

    @Transactional
    public void clearPageDataForSource(final Source source) {
        pageRepository.deleteBySource(source);
    }
}
