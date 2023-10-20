package one.digitalinnovation.labdesignpatternsspring.core.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tb_address")
public class AddressModel {
    @Id
    @Column(name = "address_cep", length = 9, nullable = false)
    private String cep;

    @Column(name = "address_street", length = 50, nullable = false)
    private String street;

    @Column(name = "address_complement", length = 50)
    private String complement;

    @Column(name = "address_neighborhood", length = 50, nullable = false)
    private String neighborhood;

    @Column(name = "address_locality", length = 50, nullable = false)
    private String locality;

    @Column(name = "address_state", length = 50, nullable = false)
    private String state;

    @Column(name = "address_ibge", length = 50)
    private String ibge;

    @Column(name = "address_gia", length = 50)
    private String gia;

    @Column(name = "address_ddd", length = 50)
    private String ddd;

    @Column(name = "address_siafi", length = 50)
    private String siafi;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "address_create_date")
    @CreationTimestamp
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "address_last_update_date")
    @UpdateTimestamp
    private Date lastUpdateDate;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
