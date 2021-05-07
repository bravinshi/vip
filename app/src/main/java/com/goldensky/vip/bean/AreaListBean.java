package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AreaListBean implements Serializable {


    private Integer areaid;
    private Integer parentareaid;
    private String areaname;
    private Integer areaorderby;
    private String areanamewithspell;
    private String areacode;
    private List<ChildrenDTOX> children;

    public AreaListBean(Integer areaid, Integer parentareaid, String areaname, Integer areaorderby, String areanamewithspell, String areacode, List<ChildrenDTOX> children) {
        this.areaid = areaid;
        this.parentareaid = parentareaid;
        this.areaname = areaname;
        this.areaorderby = areaorderby;
        this.areanamewithspell = areanamewithspell;
        this.areacode = areacode;
        this.children = children;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public Integer getParentareaid() {
        return parentareaid;
    }

    public void setParentareaid(Integer parentareaid) {
        this.parentareaid = parentareaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Integer getAreaorderby() {
        return areaorderby;
    }

    public void setAreaorderby(Integer areaorderby) {
        this.areaorderby = areaorderby;
    }

    public String getAreanamewithspell() {
        return areanamewithspell;
    }

    public void setAreanamewithspell(String areanamewithspell) {
        this.areanamewithspell = areanamewithspell;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public List<ChildrenDTOX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenDTOX> children) {
        this.children = children;
    }

    public static class ChildrenDTOX implements Serializable {
        private Integer areaid;
        private Integer parentareaid;
        private String areaname;
        private Integer areaorderby;
        private String areanamewithspell;
        private Object areacode;
        private List<ChildrenDTO> children;

        public ChildrenDTOX(Integer areaid, Integer parentareaid, String areaname, Integer areaorderby, String areanamewithspell, Object areacode, List<ChildrenDTO> children) {
            this.areaid = areaid;
            this.parentareaid = parentareaid;
            this.areaname = areaname;
            this.areaorderby = areaorderby;
            this.areanamewithspell = areanamewithspell;
            this.areacode = areacode;
            this.children = children;
        }

        public Integer getAreaid() {
            return areaid;
        }

        public void setAreaid(Integer areaid) {
            this.areaid = areaid;
        }

        public Integer getParentareaid() {
            return parentareaid;
        }

        public void setParentareaid(Integer parentareaid) {
            this.parentareaid = parentareaid;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public Integer getAreaorderby() {
            return areaorderby;
        }

        public void setAreaorderby(Integer areaorderby) {
            this.areaorderby = areaorderby;
        }

        public String getAreanamewithspell() {
            return areanamewithspell;
        }

        public void setAreanamewithspell(String areanamewithspell) {
            this.areanamewithspell = areanamewithspell;
        }

        public Object getAreacode() {
            return areacode;
        }

        public void setAreacode(Object areacode) {
            this.areacode = areacode;
        }

        public List<ChildrenDTO> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenDTO> children) {
            this.children = children;
        }

        public static class ChildrenDTO implements Serializable {
            private Integer areaid;
            private Integer parentareaid;
            private String areaname;
            private Integer areaorderby;
            private String areanamewithspell;
            private Object areacode;

            public ChildrenDTO(Integer areaid, Integer parentareaid, String areaname, Integer areaorderby, String areanamewithspell, Object areacode) {
                this.areaid = areaid;
                this.parentareaid = parentareaid;
                this.areaname = areaname;
                this.areaorderby = areaorderby;
                this.areanamewithspell = areanamewithspell;
                this.areacode = areacode;
            }

            public Integer getAreaid() {
                return areaid;
            }

            public void setAreaid(Integer areaid) {
                this.areaid = areaid;
            }

            public Integer getParentareaid() {
                return parentareaid;
            }

            public void setParentareaid(Integer parentareaid) {
                this.parentareaid = parentareaid;
            }

            public String getAreaname() {
                return areaname;
            }

            public void setAreaname(String areaname) {
                this.areaname = areaname;
            }

            public Integer getAreaorderby() {
                return areaorderby;
            }

            public void setAreaorderby(Integer areaorderby) {
                this.areaorderby = areaorderby;
            }

            public String getAreanamewithspell() {
                return areanamewithspell;
            }

            public void setAreanamewithspell(String areanamewithspell) {
                this.areanamewithspell = areanamewithspell;
            }

            public Object getAreacode() {
                return areacode;
            }

            public void setAreacode(Object areacode) {
                this.areacode = areacode;
            }
        }
    }
}
