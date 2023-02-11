package daehyun.loveShare.domain.member;

public enum GenderType {
    MEN("남자"), WOMEN("여자");

    private final String description;

    GenderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
