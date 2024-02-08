package danielGrujic.ludo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("elemento " +id+ " non trovato");
    }
    public NotFoundException(String email){
        super("la mail " +email+ " non esiste");
    }
    public NotFoundException(Long numero){
        super("Elemento con id " + numero + " non trovato");
    }


}
