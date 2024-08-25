package com.hartsad.hartRace.service.impl;


import com.github.javafaker.Faker;
import com.hartsad.hartRace.dto.CountryDto;
import com.hartsad.hartRace.dto.ContestantDto;
import com.hartsad.hartRace.entity.Country;
import com.hartsad.hartRace.entity.Contestant;
import com.hartsad.hartRace.entity.Eventitems;
import com.hartsad.hartRace.exception.ResourceNotFoundException;
import com.hartsad.hartRace.mapper.CountryMapper;
import com.hartsad.hartRace.mapper.ContestantMapper;
import com.hartsad.hartRace.repository.countryRepository;
import com.hartsad.hartRace.repository.contestantRepository;
import com.hartsad.hartRace.service.ContestantService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



@Service
@AllArgsConstructor
public class ContestantServImpl implements ContestantService {


    private contestantRepository empRepository;
    private countryRepository cntRepository;

    @Override
    public ContestantDto createEmployee(ContestantDto empdto) {
        Contestant contestant = ContestantMapper.maptoE(empdto);
        Contestant savedContestant = empRepository.save(contestant);
        return ContestantMapper.maptoEdto(savedContestant);
    }

    @Override
    public ContestantDto getEmployeeById(Long employeeeId) {
        Contestant contestant = empRepository.findById(employeeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with the Id doesnt exist: "+employeeeId));

        return ContestantMapper.maptoEdto(contestant);
    }

    @Override
    public List<ContestantDto> getAllEmployees() {
        List<Contestant> contestants = empRepository.findAll();
        return contestants.stream().map((contestant) -> ContestantMapper.maptoEdto(contestant))
                .collect(Collectors.toList());
    }

    @Override
    public ContestantDto updateEmployee(Long Id, ContestantDto updatedemployee) {
        Contestant contestant = empRepository.findById(Id).orElseThrow(
            () ->  new ResourceNotFoundException("Employee with given Id not present: "+Id)
        );
        contestant.setFName(updatedemployee.getFName());
        contestant.setLName(updatedemployee.getLName());
        contestant.setAge(updatedemployee.getAge());
        contestant.setGender(updatedemployee.getGender());
        contestant.setItem(updatedemployee.getItem());
        contestant.setEvent(updatedemployee.getEvent());
        contestant.setIsocode(updatedemployee.getIsocode());
        Contestant updateobj = empRepository.save(contestant);
        return ContestantMapper.maptoEdto(updateobj);
    }

    @Override
    public void deleteEmployee(Long Id) {
        Contestant contestant = empRepository.findById(Id).orElseThrow(
                () ->  new ResourceNotFoundException("Employee with given Id not present: "+Id)
        );
        empRepository.deleteById(Id);
    }

    public void checkCountryReg(String iso,String cname){
        Country country = cntRepository.findByCountryCode(iso);
        if (country == null){
            country = new Country();
            country.setCountryCode(iso);
            country.setCname(cname);
            country.setGold(0);
            country.setSilver(0);
            country.setBronze(0);
            country.setPoints(0);
            cntRepository.save(country);
        }
        return;

    }

    private final Faker faker = new Faker();
    //private final CustomKeyGenerator keyGenerator = new CustomKeyGenerator();
    @Transactional
    public void generateAndSaveRegistrations() {
        IntStream.range(0, 5000).forEach(i -> {
            ContestantDto emp = new ContestantDto();
            emp.setId((long) faker.number().numberBetween(1,Integer.MAX_VALUE));
            emp.setFName(faker.name().firstName());
            emp.setLName(faker.name().lastName());
            emp.setAge(faker.number().numberBetween(18, 36));
            emp.setGender(faker.options().option("Male", "Female", "Other"));
            emp.setItem(faker.options().option("Archery", "Artistic Swimming", "Athletics (Track and Field)", "Badminton", "Baseball/Softball",
                    "Archery", "Artistic Swimming", "Athletics (Track and Field)", "Badminton", "Baseball/Softball", "Basketball", "Beach Volleyball", "Boxing", "Breaking (Breakdancing)", "Canoe Sprint", "Canoe Slalom", "Cycling BMX", "Cycling Mountain Bike", "Cycling Road", "Cycling Track", "Diving", "Equestrian (Dressage)", "Equestrian (Eventing)", "Equestrian (Jumping)", "Fencing", "Football (Soccer)", "Golf", "Gymnastics Artistic", "Gymnastics Rhythmic", "Handball", "Hockey", "Judo", "Karate", "Modern Pentathlon", "Rowing", "Rugby Sevens", "Sailing", "Shooting", "Skateboarding", "Sport Climbing", "Surfing", "Swimming", "Table Tennis", "Taekwondo", "Tennis", "Triathlon", "Volleyball", "Water Polo", "Weightlifting", "Wrestling", "Alpine Skiing", "Biathlon", "Bobsleigh", "Cross-Country Skiing", "Curling", "Figure Skating", "Freestyle Skiing", "Ice Hockey", "Luge", "Nordic Combined", "Short Track Speed Skating", "Skeleton", "Ski Jumping", "Snowboarding", "Speed Skating"
            ));
            emp.setEvent(faker.options().option(
                    "Olympic Games",
                    "Paralympic Games",
                    "Youth Olympic Games",
                    "World Masters Games",
                    "Commonwealth Games",
                    "Asian Games",
                    "Pan American Games",
                    "European Games",
                    "Mediterranean Games",
                    "African Games"

            ));
            emp.setIsocode(faker.address().countryCode());
            emp.setCname(faker.country().name());
            checkCountryReg(emp.getIsocode(),emp.getCname());

            emp.setGold(0);
            emp.setSilver(0);
            emp.setBronze(0);

            createEmployee(emp);
            //empRepository.save(emp);

        });
    }

    @Override
    public List<ContestantDto> itemParticipants(String item) {
        List<Contestant> eventemployees = empRepository.findByItem(item);
        return eventemployees.stream().map((contestant) -> ContestantMapper.maptoEdto(contestant))
                .collect(Collectors.toList());
    }

    @Override
    public List<ContestantDto> genderBased(String gender) {
        List<Contestant> genderp = empRepository.findByGender(gender);
        return genderp.stream().map((contestant) -> ContestantMapper.maptoEdto(contestant))
                .collect(Collectors.toList());
    }

    public void updatemedals(List<Contestant> winners){
        if (winners.size() != 3) {
            throw new IllegalArgumentException("Expected exactly three winners");
        }

        for(int i=0;i< winners.size();i++){
            Contestant winn = winners.get(i);
            Contestant newupdwinn = empRepository.findById(winn.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee with the Id doesnt exist: "+winn.getId()));

            Country newupdcnt = cntRepository.findByCountryCode(winn.getIsocode());
            if(newupdcnt == null) {
                throw new ResourceNotFoundException("Country with the Iso doesnt exist: " + winn.getIsocode());
            }
            switch (i) {
                case 0:
                    newupdwinn.setGold(winn.getGold() + 1);
                    newupdcnt.setGold(newupdcnt.getGold() +1);
                    newupdcnt.setPoints(newupdcnt.getPoints() +3);
                    break;
                case 1:
                    newupdwinn.setSilver(winn.getSilver() + 1);
                    newupdcnt.setSilver(newupdcnt.getSilver() +1);
                    newupdcnt.setPoints(newupdcnt.getPoints() +2);
                    break;
                case 2:
                    newupdwinn.setBronze(winn.getBronze() + 1);
                    newupdcnt.setBronze(newupdcnt.getBronze() +1);
                    newupdcnt.setPoints(newupdcnt.getPoints() +1);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            Contestant upd = empRepository.save(newupdwinn);
            Country updc = cntRepository.save(newupdcnt);

        }
    }

    @Override
    public List<ContestantDto> simulateItem(String event, String item , String gender) {
        List<Contestant> itemparts;
        if(gender!=null) {
            itemparts = empRepository.findByEventAndItemAndGender(event, item, gender);
        }
        else{
            itemparts = empRepository.findByEventAndItem(event, item);
        }
        //List<EmployeeDto> winners= new ArrayList<>();
        Collections.shuffle(itemparts);
        List<Contestant> winners = itemparts.subList(0, Math.min(3, itemparts.size()));
        updatemedals(winners);

        return winners.stream().map((contestant) -> ContestantMapper.maptoEdto(contestant))
                .collect(Collectors.toList());
    }

    @Override
    public ContestantDto topMedalist() {
        List<Contestant> topm = empRepository.findTopMedalist();
        return ContestantMapper.maptoEdto(topm.get(0));

    }

    @Override
    public String topcontestant(String gender) {
        List<Contestant> topcg = empRepository.findTopContestantDetailsByGender(gender);
        if(topcg!=null) {
            return topcg.get(0).getFName() +" "+ topcg.get(0).getLName();
        }
        else {
            //log.error("No contestant found for gender: " + gender);
            return "No contestant found for the specified gender" ;
        }
    }

    @Override
    public CountryDto findbystats(String type, String criteria) {
        List<Country> ans;
        if( type.equalsIgnoreCase("lowest") || type.equalsIgnoreCase("low") ){
            if(criteria.equals("gold")){
                ans=cntRepository.findCountryWithMinimumGold();
            }
            else if(criteria.equals("silver")){
                ans=cntRepository.findCountryWithMinimumSilver();
            }
            else if(criteria.equals("bronze")){
                ans=cntRepository.findCountryWithMinimumBronze();
            }
            else{
                ans=cntRepository.findCountryWithMinimumPoints();
            }
        }
        else{
            if(criteria.equals("gold")){
                ans=cntRepository.findCountryWithMaximumGold();
            }
            else if(criteria.equals("silver")){
                ans=cntRepository.findCountryWithMaximumSilver();
            }
            else if(criteria.equals("bronze")){
                ans=cntRepository.findCountryWithMaximumBronze();
            }
            else{
                ans=cntRepository.findCountryWithMaximumPoints();
            }
        }
        return CountryMapper.maptoCdto(ans.get(0));
    }

    @Override
    public int simulateAll() {
        List<Eventitems> eventitemsList = empRepository.findAllEventAndItems();
        List<ContestantDto> winners;
        int flag = 0;
        for (Eventitems eventitems : eventitemsList) {
            try {
                winners = simulateItem(eventitems.getEvent(), eventitems.getItem(), null);
            }
            catch (Exception e){
                flag = 1;
            }
        }
        return flag;
    }

    @Override
    public List<CountryDto> topNation(int n,String event) {
        Pageable pageable = PageRequest.of(0, n);
        List<Country> topc;
        if(event!=null) {
             topc = cntRepository.findByEventNameOrderByMedals(event, pageable);
        }
        else{
             topc = cntRepository.findTopCountries(pageable);
        }
        return topc.stream().map((country) -> CountryMapper.maptoCdto(country))
                .collect(Collectors.toList());

    }

    @Override
    public CountryDto topMedalistNation() {
        Country tc = cntRepository.findFirstByOrderByGoldDescSilverDescBronzeDesc();
        return CountryMapper.maptoCdto(tc);
    }


    @Override
    public List<ContestantDto> eventContestants(String event) {
        List<Contestant> itemConts = empRepository.findByEvent(event);
        return itemConts.stream().map((contestant) -> ContestantMapper.maptoEdto(contestant))
                .collect(Collectors.toList());
    }

}
