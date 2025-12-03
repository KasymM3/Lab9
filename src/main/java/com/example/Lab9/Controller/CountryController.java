package com.example.Lab9.Controller;

import com.example.Lab9.Dto.CountryDto;
import com.example.Lab9.Service.ServiceImpl.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryServiceImpl countryService;

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<CountryDto> countryDtoList = countryService.getCountries();
        if(Objects.isNull(countryDtoList)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(countryDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        CountryDto countryDto = countryService.getCountry(id);
        if(Objects.isNull(countryDto)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(countryDto);
    }

    @PostMapping("/")
    public ResponseEntity<?> addCountry(@RequestBody CountryDto countryDto){
        CountryDto countryDto1= countryService.addCountry(countryDto);
        return ResponseEntity.ok(countryDto1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto){

        CountryDto updated=  countryService.updateCountry(id, countryDto);
        if(Objects.isNull(updated)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id){
        boolean result = countryService.deleteCountry(id);
        if(!result){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
