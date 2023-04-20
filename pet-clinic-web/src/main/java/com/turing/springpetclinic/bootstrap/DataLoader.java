package com.turing.springpetclinic.bootstrap;

import com.turing.springpetclinic.model.*;
import com.turing.springpetclinic.services.OwnerService;
import com.turing.springpetclinic.services.PetTypeService;
import com.turing.springpetclinic.services.SpecialityService;
import com.turing.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Milan on 2023/03/28.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = PetType.builder().name("Dog").build();
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = PetType.builder().name("Cat").build();
        PetType savedCatType = petTypeService.save(cat);

        Speciality radiology = Speciality.builder().description("radiology").build();
        Speciality surgery = Speciality.builder().description("surgery").build();
        Speciality dentistry = Speciality.builder().description("dentistry").build();

        Speciality savedRadiology = specialityService.save(radiology);
        Speciality savedSurgery = specialityService.save(surgery);
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = Owner.builder().firstname("Michael").lastname("Weston").address(
                "King street 123").city("London").telephone("0123456789").build();

        Pet pet1 = Pet.builder().name("Shinobu").petType(savedDogType).owner(owner1).birthDate(
                LocalDate.now().minusDays(330)).build();

        owner1.toBuilder().pet(pet1).build();
        ownerService.save(owner1);

        Owner owner2 = Owner.builder().firstname("Fiona").lastname("Glenna").address(
                "Queen street 123").city("Manchester").telephone("0357903579").build();

        Pet pet2 = Pet.builder().name("Coco").petType(savedCatType).owner(owner2).birthDate(
                LocalDate.now().minusDays(120)).build();

        owner2.toBuilder().pet(pet2).build();
        ownerService.save(owner2);

        Vet vet1 = Vet.builder().firstname("Sam").lastname("Axe").build();
        vet1.toBuilder().specialties(Set.of(savedRadiology, savedDentistry)).build();
        vetService.save(vet1);

        Vet vet2 = Vet.builder().firstname("Jessie").lastname("Porter").build();
        vet2.toBuilder().specialty(savedSurgery).build();
        vetService.save(vet2);

        System.out.println("******* Loaded PetTypes, Owners and Vets from DataLoader *******");
    }
}
