package bean;

import java.sql.Timestamp;

public class SqShare {
    private Integer id;

    private String name;

    private String singers;

    private String type;

    private String tags;

    private String srcType;

    private String format;

    private String intro;

    private Integer heat;

    private String down_url;

    private String member;

    private Timestamp create_time;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSingers() {
        return singers;
    }

    public void setSingers(String singers) {
        this.singers = singers == null ? null : singers.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getSrcType() {
        return srcType;
    }

    public void setSrcType(String srcType) {
        this.srcType = srcType == null ? null : srcType.trim();
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }

    public String getDownUrl() {
        return down_url;
    }

    public void setDownUrl(String down_url) {
        this.down_url = down_url == null ? null : down_url.trim();
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member == null ? null : member.trim();
    }

    public Timestamp getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        return "SqShare{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singers='" + singers + '\'' +
                ", type='" + type + '\'' +
                ", tags='" + tags + '\'' +
                ", srcType='" + srcType + '\'' +
                ", format='" + format + '\'' +
                ", intro='" + intro + '\'' +
                ", heat=" + heat +
                ", down_url='" + down_url + '\'' +
                ", member='" + member + '\'' +
                ", create_time=" + create_time +
                ", status='" + status + '\'' +
                '}';
    }
}