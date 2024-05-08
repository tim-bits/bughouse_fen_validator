package org.bughouse.fen;

public class ReturnCode {

    public ReturnCode(){

    }

    public ReturnCode(boolean valid, int code, String description) {
        this.valid = valid;
        this.code = code;
        this.description = description;
    }

    private boolean valid;
    private int code;
    private String description;


    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{valid: " + valid + ", code: " + code + ", description: \"" + description + "\"}";
    }
}
