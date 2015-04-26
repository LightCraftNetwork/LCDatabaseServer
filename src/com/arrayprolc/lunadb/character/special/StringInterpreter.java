package com.arrayprolc.lunadb.character.special;

public class StringInterpreter {

    private static final SpecialCharacter SPECIAL_CHARACTERS = new SpecialCharacter();

    public String fixString(String input, SpecialCharacter sc) {
        String builder = "";
        for (char c : input.toCharArray()) {
            for (char cc : sc.getSpecialCases()) {
                if ((cc + "").equals((c + ""))) {
                    builder += "\\" + cc;
                } else {
                    builder += cc;
                }
            }
        }
        return builder;
    }

    public String fixString(String input) {
        return fixString(input, SPECIAL_CHARACTERS);
    }

}
