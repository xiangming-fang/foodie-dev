package indi.xm.bo;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.bo
 * @ClassName: AddressBo
 * @Author: albert.fang
 * @Description: 地址管理BO
 * @Date: 2021/10/15 13:00
 */
public class UserAddressBO {
    private String city;
    /* 详细地址 */
    private String detail;
    /* 街道 */
    private String district;
    private String mobile;
    private String province;
    /* 收件人 */
    private String receiver;
    private String userId;
    private String addressId;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
