package ru.my_first_project.student_order.register;

public class CityRegisterResponse {
    private boolean registered;
    boolean temporal;

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "CityRegisterResponse{" +
                "existing=" + registered +
                ", temporal=" + temporal +
                '}';
    }
}
