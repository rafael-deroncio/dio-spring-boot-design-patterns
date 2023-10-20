package one.digitalinnovation.labdesignpatternsspring.domain.requests;

public class ClientRequest {
    private String name;
    private AddressRequest address;

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
