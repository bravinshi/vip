package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LogisticsBean implements Serializable {
    @SerializedName("message")
    private String message;
    @SerializedName("nu")
    private String nu;
    @SerializedName("ischeck")
    private String ischeck;
    @SerializedName("com")
    private String com;
    @SerializedName("status")
    private String status;
    @SerializedName("state")
    private String state;
    @SerializedName("condition")
    private String condition;
    @SerializedName("routeInfo")
    private RouteInfoDTO routeInfo;
    @SerializedName("returnCode")
    private String returnCode;
    @SerializedName("result")
    private Boolean result;
    @SerializedName("trailUrl")
    private String trailUrl;
    @SerializedName("arrivalTime")
    private String arrivalTime;
    @SerializedName("totalTime")
    private String totalTime;
    @SerializedName("remainTime")
    private String remainTime;
    @SerializedName("data")
    private List<DataDTO> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public RouteInfoDTO getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(RouteInfoDTO routeInfo) {
        this.routeInfo = routeInfo;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getTrailUrl() {
        return trailUrl;
    }

    public void setTrailUrl(String trailUrl) {
        this.trailUrl = trailUrl;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class RouteInfoDTO implements Serializable {
        @SerializedName("from")
        private FromDTO from;
        @SerializedName("cur")
        private CurDTO cur;
        @SerializedName("to")
        private ToDTO to;

        public FromDTO getFrom() {
            return from;
        }

        public void setFrom(FromDTO from) {
            this.from = from;
        }

        public CurDTO getCur() {
            return cur;
        }

        public void setCur(CurDTO cur) {
            this.cur = cur;
        }

        public ToDTO getTo() {
            return to;
        }

        public void setTo(ToDTO to) {
            this.to = to;
        }

        public static class FromDTO implements Serializable {
            @SerializedName("number")
            private String number;
            @SerializedName("name")
            private String name;

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class CurDTO implements Serializable {
            @SerializedName("number")
            private String number;
            @SerializedName("name")
            private String name;

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ToDTO implements Serializable {
            @SerializedName("number")
            private String number;
            @SerializedName("name")
            private String name;

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class DataDTO implements Serializable {
        @SerializedName("time")
        private String time;
        @SerializedName("context")
        private String context;
        @SerializedName("ftime")
        private String ftime;
        @SerializedName("areaCode")
        private String areaCode;
        @SerializedName("areaName")
        private String areaName;
        @SerializedName("status")
        private String status;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
