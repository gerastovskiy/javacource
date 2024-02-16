package ru.cource.oop;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Currency {
    USD(840,"доллар США"),
    EUR(978, "евро"),
    RUB(643,"российский рубль"),
    CNY(156,"китайский юань");

    final int code;
    final String name;
}
