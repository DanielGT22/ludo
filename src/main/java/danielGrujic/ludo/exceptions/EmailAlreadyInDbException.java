package danielGrujic.ludo.exceptions;

public class EmailAlreadyInDbException extends  RuntimeException{
    public EmailAlreadyInDbException(String email) {
        super(email+" gia esistente, selezionane un'altra");
    }
}
