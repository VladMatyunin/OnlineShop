package ru.kpfu.itis.model.additional;

/**
 * Created by vladislav on 04.05.17.
 */
public class TransactionInform {
    public TransactionInform(boolean success, String message){
        this.success = success;
        this.message = message;
    }
    public boolean success;
    public String message;
}
