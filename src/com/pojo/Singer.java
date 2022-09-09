package com.pojo;

import java.util.List;

public class Singer {
    private Integer singerId;
    private String singerName;
    private String singerSex;
    private String singerLabel;
    private String singerInitial;
    private Integer star;
    private String singerIntro ;
    //配置1对N的关系(看需求，可以配置，也可以不配置)
    private List<SingerImg> singerImgList;



    public void setSingerInitial(String singerInitial) {
        this.singerInitial = singerInitial;
    }

    public Singer() {
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerSex() {
        return singerSex;
    }

    public void setSingerSex(String singerSex) {
        this.singerSex = singerSex;
    }

    public String getSingerLabel() {
        return singerLabel;
    }

    public void setSingerLabel(String singerLabel) {
        this.singerLabel = singerLabel;
    }

    public Integer getStar() {
        return star;
    }

    public String getSingerInitial() {
        return singerInitial;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getSingerIntro() {
        return singerIntro;
    }

    public void setSingerIntro(String singerIntro) {
        this.singerIntro = singerIntro;
    }

    public List<SingerImg> getSingerImgList() {
        return singerImgList;
    }

    public void setSingerImgList(List<SingerImg> singerImgList) {
        this.singerImgList = singerImgList;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "singerId=" + singerId +
                ", singerName='" + singerName + '\'' +
                ", singerSex='" + singerSex + '\'' +
                ", singerLabel='" + singerLabel + '\'' +
                ", singerInitial='" + singerInitial + '\'' +
                ", star=" + star +
                ", singerIntro='" + singerIntro + '\'' +
                '}';
    }
}
