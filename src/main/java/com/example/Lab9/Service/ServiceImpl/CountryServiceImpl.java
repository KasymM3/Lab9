package com.example.Lab9.Service.ServiceImpl;

import com.example.Lab9.Dto.CountryDto;
import com.example.Lab9.Entity.Country;
import com.example.Lab9.Mapper.CountryMapper;
import com.example.Lab9.Mapper.ItemMapper;
import com.example.Lab9.Repository.CountryRepository;
import com.example.Lab9.Service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> getCountries() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toDtoList(countries);
    }

    @Override
    public CountryDto getCountry(Long id) {
        Country country = countryRepository.findById(id).orElse(null);

        return countryMapper.toDto(country);
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        Country country = countryMapper.toEntity(countryDto);
        Country country1 = countryRepository.save(country);
        CountryDto countryDto1 = countryMapper.toDto(country1);
        return countryDto1;
    }

    @Override
    public CountryDto updateCountry(CountryDto countryDto) {
        Country country = countryMapper.toEntity(countryDto);
        Country country1 = countryRepository.save(country);
        CountryDto countryDto1 = countryMapper.toDto(country1);
        return countryDto1;
    }

    @Override
    public boolean deleteCountry(Long id) {
        CountryDto countryDto = getCountry(id);
        if(Objects.isNull(countryDto)){
            return false;
        }
        countryRepository.deleteById(id);
        return true;
    }
}
