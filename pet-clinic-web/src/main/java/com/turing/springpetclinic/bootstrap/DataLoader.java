package com.turing.springpetclinic.bootstrap;

import com.turing.springpetclinic.model.Owner;
import com.turing.springpetclinic.model.Pet;
import com.turing.springpetclinic.model.PetType;
import com.turing.springpetclinic.model.Speciality;
import com.turing.springpetclinic.model.Vet;
import com.turing.springpetclinic.model.Visit;
import com.turing.springpetclinic.services.OwnerService;
import com.turing.springpetclinic.services.PetService;
import com.turing.springpetclinic.services.PetTypeService;
import com.turing.springpetclinic.services.SpecialityService;
import com.turing.springpetclinic.services.VetService;
import com.turing.springpetclinic.services.VisitService;
import com.turing.springpetclinic.utils.Helper;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Milan on 2023/03/28.
 */
@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetService petService;
	private final PetTypeService petTypeService;
	private final SpecialityService specialityService;
	private final VisitService visitService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService,
		SpecialityService specialityService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petService = petService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) {
		int count = petTypeService.findAll()
			.size();

		if (count == 0) {
			loadData();
		}
	}

	private void loadData() {
		PetType savedCatType = createPetType("Cat");
		PetType savedDogType = createPetType("Dog");
		createPetType("Other");

		Speciality savedRadiology = createSpeciality("radiology");
		Speciality savedSurgery = createSpeciality("surgery");
		Speciality savedDentistry = createSpeciality("dentistry");

		Owner owner1 = createOwner("Michael", "Weston", "King street 123", "London", "0123456789");
		ownerService.save(owner1);

		Pet pet1 = createPet("Shinobu", savedDogType, owner1, Helper.localDateToDate(LocalDate.now()
			.minusDays(330)));

		owner1.toBuilder()
			.pet(pet1)
			.build();
		ownerService.save(owner1);

		Owner owner2 = createOwner("Fiona", "Glenna", "Queen street 123", "Manchester", "0357903579");
		ownerService.save(owner2);

		Pet pet2 = createPet("Coco", savedCatType, owner2, Helper.localDateToDate(LocalDate.now()
			.minusDays(120)));

		owner2.toBuilder()
			.pet(pet2)
			.build();
		ownerService.save(owner2);

		createVisit("Dog immunization", pet1, Helper.localDateToDate(LocalDate.now()));
		createVisit("Routine cat check-up", pet2, Helper.localDateToDate(LocalDate.now()));

		createVet("Sam", "Axe", Set.of(savedRadiology, savedDentistry));
		createVet("Jessie", "Porter", Set.of(savedSurgery));

		log.info("Loaded PetTypes, Owners and Vets from DataLoader");
	}

	private Owner createOwner(String firstname, String lastname, String address, String city, String telephone) {
		return Owner.builder()
			.firstname(firstname)
			.lastname(lastname)
			.address(address)
			.city(city)
			.telephone(telephone)
			.build();
	}

	private Pet createPet(String name, PetType petType, Owner owner, Date birthDate) {
		return petService.save(Pet.builder()
			.name(name)
			.petType(petType)
			.owner(owner)
			.birthDate(birthDate)
			.build());
	}

	private PetType createPetType(String petTypeName) {
		return petTypeService.save(PetType.builder()
			.name(petTypeName)
			.build());
	}

	private void createVet(String firstname, String lastname, Set<Speciality> specialities) {
		vetService.save(Vet.builder()
			.firstname(firstname)
			.lastname(lastname)
			.specialties(specialities)
			.build());
	}

	private void createVisit(String description, Pet pet, Date visitDate) {
		visitService.save(Visit.builder()
			.description(description)
			.pet(pet)
			.date(visitDate)
			.build());
	}

	private Speciality createSpeciality(String specialtyName) {
		return specialityService.save(Speciality.builder()
			.description(specialtyName)
			.build());
	}
}
