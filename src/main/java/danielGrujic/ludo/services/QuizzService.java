package danielGrujic.ludo.services;

import danielGrujic.ludo.entities.Quizz;
import danielGrujic.ludo.exceptions.NotFoundException;
import danielGrujic.ludo.payloads.QuizzPayloads.QuizzRequestDTO;
import danielGrujic.ludo.repositories.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuizzService {
    @Autowired
    private QuizzRepository quizzRepository;


    public Page<Quizz> getAll(int page, int size, String orderBy) {
    if(size>=20) size=20;
    Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
    return quizzRepository.findAll(pageable);
    };
    public Quizz findByUuid(UUID uuid) {
        return quizzRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }
    public void delete(UUID id) {
        quizzRepository.delete(this.findByUuid(id));
    }

    public Quizz save(QuizzRequestDTO quizzRequestDTO) {
        Quizz quizz = new Quizz();
        quizz.setQuestion(quizzRequestDTO.question());
        quizz.setAnswer(quizzRequestDTO.answer());
        quizz.setDecoy1(quizzRequestDTO.decoy1());
        quizz.setDecoy2(quizzRequestDTO.decoy2());
        quizz.setDecoy3(quizzRequestDTO.decoy3());
        return quizzRepository.save(quizz);
    }


}
