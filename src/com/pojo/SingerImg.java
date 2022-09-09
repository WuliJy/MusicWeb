package com.pojo;

public class SingerImg {

        private Integer imgId;
        private String imgPath;
        private Singer singer;

        public SingerImg() {
        }

        public Integer getImgId() {
            return imgId;
        }

        public void setImgId(Integer imgId) {
            this.imgId = imgId;
        }

        public String getImgPath() {
            return imgPath;
        }

        public void setImgPath(String imgPath) {
            this.imgPath = imgPath;
        }

        public Singer getSinger() {
            return singer;
        }

        public void setSinger(Singer singer) {
            this.singer = singer;
        }

        @Override
        public String toString() {
            return "SingerImg{" +
                    "imgId=" + imgId +
                    ", imgPath='" + imgPath + '\'' +
                    '}';
        }
}
