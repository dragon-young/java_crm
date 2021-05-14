package cn.sixboys.domain;

/**
 * 部门管理实体
 * @author FuqiangWu
 *
 */
public class Department {
    private Long id;
    private String name;
    private String sn;

    public Department() {
    }

    public Department(Long id, String name, String sn) {
        this.id = id;
        this.name = name;
        this.sn = sn;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSn() {
        return sn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}
