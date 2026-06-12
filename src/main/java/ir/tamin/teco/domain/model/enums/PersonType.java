package ir.tamin.teco.domain.model.enums;

import lombok.Getter;

@Getter
public enum PersonType {

    REAL("REAL", "شخص حقیقی"),
    LEGAL("LEGAL", "شخص حقوقی");

    private final String code;
    private final String name;

    PersonType(String code, String name) {
        this.code = code;
        this.name = name;
    }

}