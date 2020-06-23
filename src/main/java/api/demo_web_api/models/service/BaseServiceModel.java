package api.demo_web_api.models.service;

public abstract  class BaseServiceModel {
    private String id;

    public BaseServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
