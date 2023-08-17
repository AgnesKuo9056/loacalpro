package com.example.myapplication3.Bean;

import java.io.Serializable;

public class Book implements Serializable {


        /**
         * bname : 微积分
         * course : 学科基础课程
         * _id : 1
         * type : 数学类别
         */

        private String bname;
        private String course;
        private int _id;
        private String type;

        public String getName() {
            return bname;
        }

        public void setName(String bname) {
            this.bname = bname;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public int getId() {
            return _id;
        }

        public void setId(int _id) {
            this._id = _id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

}
