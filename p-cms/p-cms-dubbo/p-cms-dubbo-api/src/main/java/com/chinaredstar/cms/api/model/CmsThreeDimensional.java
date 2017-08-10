package com.chinaredstar.cms.api.model;

import java.io.Serializable;
import java.util.Date;

public class CmsThreeDimensional implements Serializable{
    private static final long serialVersionUID = -6355360246874163499L;
    private Integer id;

    private String brandid;

    private String brandname;

    private Date createdate;

    private String storagename;

    private String filetype;

    private String status;

    private String tag;

    private String companyid;

    private String companyname;

    private String productid;

    private String productspu;

    private String productname;

    private String iosmodel;

    private String androidmodel;

    private String h5model;

    private String cover;

    private String remark;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid == null ? null : brandid.trim();
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname == null ? null : brandname.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getStoragename() {
        return storagename;
    }

    public void setStoragename(String storagename) {
        this.storagename = storagename == null ? null : storagename.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid == null ? null : companyid.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid == null ? null : productid.trim();
    }

    public String getProductspu() {
        return productspu;
    }

    public void setProductspu(String productspu) {
        this.productspu = productspu == null ? null : productspu.trim();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getIosmodel() {
        return iosmodel;
    }

    public void setIosmodel(String iosmodel) {
        this.iosmodel = iosmodel == null ? null : iosmodel.trim();
    }

    public String getAndroidmodel() {
        return androidmodel;
    }

    public void setAndroidmodel(String androidmodel) {
        this.androidmodel = androidmodel == null ? null : androidmodel.trim();
    }

    public String getH5model() {
        return h5model;
    }

    public void setH5model(String h5model) {
        this.h5model = h5model == null ? null : h5model.trim();
    }

    private String version;
    private String h5ModelSize;
    private String iosModelSize;
    private String androidModelSize;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getH5ModelSize() {
        return h5ModelSize;
    }

    public void setH5ModelSize(String h5ModelSize) {
        this.h5ModelSize = h5ModelSize;
    }

    public String getIosModelSize() {
        return iosModelSize;
    }

    public void setIosModelSize(String iosModelSize) {
        this.iosModelSize = iosModelSize;
    }

    public String getAndroidModelSize() {
        return androidModelSize;
    }

    public void setAndroidModelSize(String androidModelSize) {
        this.androidModelSize = androidModelSize;
    }
}