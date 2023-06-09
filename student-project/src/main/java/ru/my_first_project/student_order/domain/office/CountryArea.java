package ru.my_first_project.student_order.domain.office;

public class CountryArea {
    private String areaId;
    private String areaName;

    public CountryArea(String areaId, String areaName) {
        this.areaId = areaId;
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "CountryArea{" +
                "areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
