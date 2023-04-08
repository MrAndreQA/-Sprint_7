package ru.yandex.practikum.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderModel {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;
    private String track;

    public OrderModel() {
    }

    public OrderModel(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public OrderModel(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderModel)) return false;

        OrderModel order = (OrderModel) o;

        if (rentTime != order.rentTime) return false;
        if (!firstName.equals(order.firstName)) return false;
        if (!lastName.equals(order.lastName)) return false;
        if (!address.equals(order.address)) return false;
        if (!metroStation.equals(order.metroStation)) return false;
        if (!phone.equals(order.phone)) return false;
        if (!Objects.equals(comment, order.comment)) return false;
        if (!Objects.equals(color, order.color)) return false;
        return Objects.equals(track, order.track);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + metroStation.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + rentTime;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (track != null ? track.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", metroStation='" + metroStation + '\'' +
                ", phone='" + phone + '\'' +
                ", rentTime=" + rentTime +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", comment='" + comment + '\'' +
                ", color=" + color +
                ", track='" + track + '\'' +
                '}';
    }
}
