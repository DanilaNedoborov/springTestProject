package searchengine.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "page")
public class PageTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "site_id")
    private SiteTable site;

    private String path;

    private int code;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('MEDIUMTEXT')")
    private MediumTextType content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SiteTable getSite() {
        return site;
    }

    public void setSite(SiteTable site) {
        this.site = site;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MediumTextType getContent() {
        return content;
    }

    public void setContent(MediumTextType content) {
        this.content = content;
    }
}
