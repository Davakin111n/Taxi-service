package com.jean.taxi.entity;

public class OrderAddress extends Identifier {

    private Long orderId;
    private String destinationAddress;
    private String destinationHouseNumber;
    private String destinationPorchNumber;

    public String getDestinationPorchNumber() {
        return destinationPorchNumber;
    }

    public void setDestinationPorchNumber(String destinationPorchNumber) {
        this.destinationPorchNumber = destinationPorchNumber;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDestinationHouseNumber() {
        return destinationHouseNumber;
    }

    public void setDestinationHouseNumber(String destinationHouseNumber) {
        this.destinationHouseNumber = destinationHouseNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderAddress{" +
                "orderId=" + orderId +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", destinationHouseNumber='" + destinationHouseNumber + '\'' +
                ", destinationPorchNumber='" + destinationPorchNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderAddress that = (OrderAddress) o;

        if (!orderId.equals(that.orderId)) return false;
        if (!destinationAddress.equals(that.destinationAddress)) return false;
        if (!destinationHouseNumber.equals(that.destinationHouseNumber)) return false;
        return destinationPorchNumber.equals(that.destinationPorchNumber);

    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + destinationAddress.hashCode();
        result = 31 * result + destinationHouseNumber.hashCode();
        result = 31 * result + destinationPorchNumber.hashCode();
        return result;
    }
}
