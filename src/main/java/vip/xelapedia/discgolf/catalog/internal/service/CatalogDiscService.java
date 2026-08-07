package vip.xelapedia.discgolf.catalog.internal.service;

import jakarta.transaction.Transactional;
import vip.xelapedia.discgolf.catalog.controller.dto.response.ManufacturerWebResponse;
import vip.xelapedia.discgolf.catalog.controller.dto.response.MoldWebResponse;
import vip.xelapedia.discgolf.catalog.controller.dto.response.PlasticWebResponse;
import vip.xelapedia.discgolf.catalog.internal.error.MoldNotFoundException;
import vip.xelapedia.discgolf.catalog.internal.entity.CatalogDisc;
import vip.xelapedia.discgolf.catalog.internal.entity.Manufacturer;
import vip.xelapedia.discgolf.catalog.internal.entity.Mold;
import vip.xelapedia.discgolf.catalog.internal.entity.Plastic;
import vip.xelapedia.discgolf.catalog.internal.repository.CatalogDiscRepository;
import vip.xelapedia.discgolf.catalog.internal.repository.MoldRepository;
import vip.xelapedia.discgolf.catalog.internal.repository.PlasticRepository;
import vip.xelapedia.discgolf.catalog.internal.repository.ManufacturerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CatalogDiscService {
    private final CatalogDiscRepository catalogDiscRepository;
    private final PlasticRepository plasticRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final MoldRepository moldRepository;

    public MoldWebResponse getMoldDetailsById(final UUID id) {
        final Mold mold = moldRepository.findById(id).orElseThrow(() -> new MoldNotFoundException(id));

        final List<CatalogDisc> catalogDiscs = catalogDiscRepository.findByMold(mold);

        final List<PlasticWebResponse> plastics = catalogDiscs.stream().map(CatalogDisc::getPlastic)
                .distinct()
                .map(plastic -> new PlasticWebResponse(plastic.getId(), plastic.getName()))
                .toList();

        final List<ManufacturerWebResponse> manufacturers = catalogDiscs.stream().map(CatalogDisc::getManufacturer)
                .distinct()
                .map(manufacturer -> new ManufacturerWebResponse(manufacturer.getId(), manufacturer.getName()))
                .toList();

        return MoldWebResponse.builder()
                .id(mold.getId())
                .name(mold.getName())
                .speed(mold.getSpeed())
                .glide(mold.getGlide())
                .turn(mold.getTurn())
                .fade(mold.getFade())
                .diameter(mold.getDiameter())
                .height(mold.getHeight())
                .rimDepth(mold.getRimDepth())
                .rimWidth(mold.getRimWidth())
                .manufacturers(manufacturers)
                .plastics(plastics)
                .build();
    }

    @Transactional
    public CatalogDisc persistDisc(final CatalogDisc newDisc) {
        return catalogDiscRepository.save(newDisc);
    }

    @Transactional
    public Manufacturer persistManufacturer(final Manufacturer newManufacturer) {
        return manufacturerRepository.save(newManufacturer);
    }

    @Transactional
    public Plastic persistPlastic(final Plastic newPlastic) {
        return plasticRepository.save(newPlastic);
    }

    @Transactional
    public Mold persistMold(final Mold mold) {
        return moldRepository.save(mold);
    }

    public Optional<Manufacturer> getManufacturerByKey(final String key) {
        return manufacturerRepository.findByKey(key);
    }

    public Optional<Plastic> getPlasticByKey(final String key) {
        return plasticRepository.findByKey(key);
    }

    public Optional<Mold> getMoldByKey(final String key) {
        return moldRepository.findByKey(key);
    }

    public Optional<CatalogDisc> getDiscByKey(final String key) {
        return catalogDiscRepository.findByKey(key);
    }
}

