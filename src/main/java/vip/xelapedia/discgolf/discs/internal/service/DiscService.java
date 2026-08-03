package vip.xelapedia.discgolf.discs.internal.service;

import jakarta.transaction.Transactional;
import vip.xelapedia.discgolf.discs.internal.entity.Disc;
import vip.xelapedia.discgolf.discs.internal.entity.Manufacturer;
import vip.xelapedia.discgolf.discs.internal.entity.Mold;
import vip.xelapedia.discgolf.discs.internal.entity.Plastic;
import vip.xelapedia.discgolf.discs.internal.repository.DiscRepository;
import vip.xelapedia.discgolf.discs.internal.repository.MoldRepository;
import vip.xelapedia.discgolf.discs.internal.repository.PlasticRepository;
import vip.xelapedia.discgolf.discs.internal.repository.ManufacturerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DiscService {
    private final DiscRepository discRepository;
    private final PlasticRepository plasticRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final MoldRepository moldRepository;

    @Transactional
    public Disc persistDisc(final Disc newDisc) {
        return discRepository.save(newDisc);
    }

    public Iterable<Disc> getDiscs(final Set<UUID> discIds) {
        return discRepository.findAllById(discIds);
    }

    @Transactional
    public Plastic persistPlastic(final Plastic newPlastic) {
        return plasticRepository.save(newPlastic);
    }

    public Iterable<Plastic> getPlastics(final Set<UUID> plasticIds) {
        return plasticRepository.findAllById(plasticIds);
    }

    @Transactional
    public Manufacturer persistManufacturer(final Manufacturer newManufacturer) {
        return manufacturerRepository.save(newManufacturer);
    }

    public Iterable<Manufacturer> getManufacturers(final Set<UUID> manufacturerIds) {
        return manufacturerRepository.findAllById(manufacturerIds);
    }

    public Optional<Manufacturer> getManufactuerersByKey(final String key) {
        return manufacturerRepository.findByKey(key);
    }

    @Transactional
    public Mold persistMold(final Mold mold) {
        return moldRepository.save(mold);
    }

    public Iterable<Mold> getMolds(final Set<UUID> moldIds) {
        return moldRepository.findAllById(moldIds);
    }

    @Transactional
    public void clearAllData() {
        discRepository.deleteAll();
        plasticRepository.deleteAll();
        manufacturerRepository.deleteAll();
    }
}

