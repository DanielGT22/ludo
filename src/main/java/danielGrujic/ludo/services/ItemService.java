package danielGrujic.ludo.services;

import danielGrujic.ludo.entities.Items;
import danielGrujic.ludo.entities.Quizz;
import danielGrujic.ludo.exceptions.NotFoundException;
import danielGrujic.ludo.payloads.Item.ItemRequestDTO;
import danielGrujic.ludo.payloads.QuizzPayloads.QuizzRequestDTO;
import danielGrujic.ludo.repositories.ItemmsRepository;
import danielGrujic.ludo.repositories.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    private ItemmsRepository itemmsRepository;

    public Page<Items> getAll(int page, int size, String orderBy) {
        if(size>=20) size=20;
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return itemmsRepository.findAll(pageable);
    };

    public Items findByUuid(UUID uuid) {
        return itemmsRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(uuid));
    }

    public void delete(UUID id) {
        itemmsRepository.delete(this.findByUuid(id));
    }
    public Items save(ItemRequestDTO itemRequestDTO) {
        Items items = new Items();
        items.setName(itemRequestDTO.name());
        items.setImg(itemRequestDTO.img());
        items.setPrice(itemRequestDTO.price());
        return itemmsRepository.save(items);
    }
}
