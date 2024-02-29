package danielGrujic.ludo.services;

import danielGrujic.ludo.entities.Asosijacija;
import danielGrujic.ludo.entities.Quizz;
import danielGrujic.ludo.exceptions.NotFoundException;
import danielGrujic.ludo.payloads.AsosijacijePayloads.AsoRequestDTO;
import danielGrujic.ludo.payloads.QuizzPayloads.QuizzRequestDTO;
import danielGrujic.ludo.repositories.AsosijacijeRepository;
import danielGrujic.ludo.repositories.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AsosijacijeService  {
    @Autowired
    private AsosijacijeRepository asosijacijeRepository;


    public Page<Asosijacija> getAll(int page, int size, String orderBy) {
        if(size>=20) size=20;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return asosijacijeRepository.findAll(pageable);
    };
    public Asosijacija findByUuid(UUID uuid) {
        return asosijacijeRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }
    public void delete(UUID id) {
        asosijacijeRepository.delete(this.findByUuid(id));
    }

    public Asosijacija save(AsoRequestDTO asoRequestDTO) {
        Asosijacija asosijacija = new Asosijacija();
        asosijacija.setA1(asoRequestDTO.A1());
        asosijacija.setA2(asoRequestDTO.A2());
        asosijacija.setA3(asoRequestDTO.A3());
        asosijacija.setA4(asoRequestDTO.A4());
        asosijacija.setA5(asoRequestDTO.A5());
        asosijacija.setA6(asoRequestDTO.A6());
        asosijacija.setA7(asoRequestDTO.A7());
        asosijacija.setA8(asoRequestDTO.A8());
        asosijacija.setA9(asoRequestDTO.A9());
        asosijacija.setA10(asoRequestDTO.A10());

        return asosijacijeRepository.save(asosijacija);
    }


}
