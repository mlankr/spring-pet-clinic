package com.turing.springpetclinic.services.map;

import com.turing.springpetclinic.model.Speciality;
import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.services.SpecialityService;
import com.turing.springpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Milan on 2023/02/18.
 */
@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet save(Vet vet) {

        if (vet.getSpecialties().size() > 0) {
            vet.getSpecialties().forEach(speciality -> {
                if (speciality.getId() == null) {
                    Speciality savedSpecialty = specialityService.save(speciality);
                    speciality.toBuilder().id(savedSpecialty.getId()).build();
                }
            });
        }
        return super.save(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
