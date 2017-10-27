package com.wxy.hyprodemo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017\10\26 0026.
 */

public class PMInfo extends BaseInfo{


    /**
     * data : {"uid":"215","rongyunToken":"y0wefHUpJrG1CY3Gg9c2JmBU33WeDNraZyafkrjnWTdv1vR2gRlFufTjUxBDpxIfcJiv0Mj6W0nfSUhDDsugiA==","FolderList":[{"name":"666","time":1505626021,"mtids":[],"Fid":17},{"name":"96546454啦啦","time":1495794708,"mtids":["427"],"Fid":12},{"name":"999","time":1505626035,"mtids":[],"Fid":18},{"name":"TextViewTextChangeEvent{text=TextViewTextChangeEvent{text=TextViewTextChangeEvent{text=656569你结我还在破罐子破, start=8, before=0, count=7, view=android.support.v7.widget.AppCompatEditText{87efe VFED..CL. .F...... 127,0-1080,128 #7f0e00a6 app:id/tv_name}}, start=0, before=0, count=0, view=android.support.v7.widget.AppCompatEditText{95d200c VF.D..CL. ........ 0,0-1080,128 #7f0e00a6 app:id/tv_name}}, start=0, before=0, count=0, view=android.support.v7.widget.AppCompatEditText{be4ebb1 VF.D..CL. ........ 0,0-1080,128 #7f0e00a6 app:id/tv_name}}","time":1495791717,"mtids":["956"],"Fid":10},{"name":"TextViewTextChangeEvent{text=TextViewTextChangeEvent{text=oijj987987饿你啦9, start=0, before=0, count=0, view=android.support.v7.widget.AppCompatEditText{bb90c02 VF.D..CL. ........ 0,0-1080,128 #7f0e00a6 app:id/tv_name}}, start=0, before=0, count=0, view=android.support.v7.widget.AppCompatEditText{a7ba7ba VF.D..CL. ........ 0,0-1080,128 #7f0e00a6 app:id/tv_name}}","time":1495787716,"mtids":["939"],"Fid":5},{"name":"ddddddddddddddddddddd明","time":1495787980,"mtids":["737","983"],"Fid":8},{"name":"一名","time":1495851710,"mtids":["704"],"Fid":13},{"name":"一名。","time":1504250765,"mtids":[],"Fid":15},{"name":"么么啦你好你好你好好。。。hxjd","time":1495787709,"mtids":["579","904","962","963"],"Fid":4},{"name":"哦哦哦96","time":1504250299,"mtids":[],"Fid":14},{"name":"我现在在","time":1504251427,"mtids":[],"Fid":16}],"IOS_token":"170976fa8ab4567ecab"}
     */


    @SerializedName("data")
    private DataBean dataX;



    public DataBean getDataX() {
        return dataX;
    }

    public void setDataX(DataBean dataX) {
        this.dataX = dataX;
    }

    public static class DataBean {
        /**
         * uid : 215
         * rongyunToken : y0wefHUpJrG1CY3Gg9c2JmBU33WeDNraZyafkrjnWTdv1vR2gRlFufTjUxBDpxIfcJiv0Mj6W0nfSUhDDsugiA==
         * FolderList : [{"name":"666","time":1505626021,"mtids":[],"Fid":17},{"name":"96546454啦啦","time":1495794708,"mtids":["427"],"Fid":12},{"name":"999","time":1505626035,"mtids":[],"Fid":18},{"name":"TextViewTextChangeEvent{text=TextViewTextChangeEvent{text=TextViewTextChangeEvent{text=656569你结我还在破罐子破, start=8, before=0, count=7, view=android.support.v7.widget.AppCompatEditText{87efe VFED..CL. .F...... 127,0-1080,128 #7f0e00a6 app:id/tv_name}}, start=0, before=0, count=0, view=android.support.v7.widget.AppCompatEditText{95d200c VF.D..CL. ........ 0,0-1080,128 #7f0e00a6 app:id/tv_name}}, start=0, before=0, count=0, view=android.support.v7.widget.AppCompatEditText{be4ebb1 VF.D..CL. ........ 0,0-1080,128 #7f0e00a6 app:id/tv_name}}","time":1495791717,"mtids":["956"],"Fid":10},{"name":"TextViewTextChangeEvent{text=TextViewTextChangeEvent{text=oijj987987饿你啦9, start=0, before=0, count=0, view=android.support.v7.widget.AppCompatEditText{bb90c02 VF.D..CL. ........ 0,0-1080,128 #7f0e00a6 app:id/tv_name}}, start=0, before=0, count=0, view=android.support.v7.widget.AppCompatEditText{a7ba7ba VF.D..CL. ........ 0,0-1080,128 #7f0e00a6 app:id/tv_name}}","time":1495787716,"mtids":["939"],"Fid":5},{"name":"ddddddddddddddddddddd明","time":1495787980,"mtids":["737","983"],"Fid":8},{"name":"一名","time":1495851710,"mtids":["704"],"Fid":13},{"name":"一名。","time":1504250765,"mtids":[],"Fid":15},{"name":"么么啦你好你好你好好。。。hxjd","time":1495787709,"mtids":["579","904","962","963"],"Fid":4},{"name":"哦哦哦96","time":1504250299,"mtids":[],"Fid":14},{"name":"我现在在","time":1504251427,"mtids":[],"Fid":16}]
         * IOS_token : 170976fa8ab4567ecab
         */

        private String uid;
        private String rongyunToken;
        private String IOS_token;
        private List<FolderListBean> FolderList;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getRongyunToken() {
            return rongyunToken;
        }

        public void setRongyunToken(String rongyunToken) {
            this.rongyunToken = rongyunToken;
        }

        public String getIOS_token() {
            return IOS_token;
        }

        public void setIOS_token(String IOS_token) {
            this.IOS_token = IOS_token;
        }

        public List<FolderListBean> getFolderList() {
            return FolderList;
        }

        public void setFolderList(List<FolderListBean> FolderList) {
            this.FolderList = FolderList;
        }

        public static class FolderListBean {
            /**
             * name : 666
             * time : 1505626021
             * mtids : []
             * Fid : 17
             */

            private String name;
            private int time;
            private int Fid;
            private List<?> mtids;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getFid() {
                return Fid;
            }

            public void setFid(int Fid) {
                this.Fid = Fid;
            }

            public List<?> getMtids() {
                return mtids;
            }

            public void setMtids(List<?> mtids) {
                this.mtids = mtids;
            }
        }
    }
}
