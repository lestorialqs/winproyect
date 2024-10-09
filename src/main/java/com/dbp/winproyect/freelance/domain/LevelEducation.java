package com.dbp.winproyect.freelance.domain;

public enum LevelEducation {
    PRIMARY("Primary education / Elementary education"),
    SECONDARY("Secondary education / High school"),
    VOCATIONAL("Vocational training / Technical education"),
    UNDERGRADUATE("Undergraduate education / Bachelor's degree"),
    MASTERS("Master's degree"),
    DOCTORATE("Doctorate / PhD"),
    CONTINUING_EDUCATION("Continuing education / Certificate programs");

    private String description;

    LevelEducation(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

