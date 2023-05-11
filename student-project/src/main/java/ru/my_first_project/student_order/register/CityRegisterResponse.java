package ru.my_first_project.student_order.register;

public class CityRegisterResponse {
    private boolean existing;
    Boolean temporal;

    public boolean isExisting() {
        return existing;
    }

    public void setExisting(boolean existing) {
        this.existing = existing;
    }

    public Boolean getTemporal() {
        return temporal;
    }

    public void setTemporal(Boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "CityRegisterResponse{" +
                "existing=" + existing +
                ", temporal=" + temporal +
                '}';
    }
}
