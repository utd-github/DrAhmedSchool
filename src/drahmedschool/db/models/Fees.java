/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drahmedschool.db.models;

/**
 *
 * @author Mohamed Jiingad
 */
public class Fees {
    String  no,id,type,Amount,payfrom,fdate;

    public Fees(String no, String id, String type, String Amount, String payfrom, String fdate) {
        this.no = no;
        this.id = id;
        this.type = type;
        this.Amount = Amount;
        this.payfrom = payfrom;
        this.fdate = fdate;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getPayfrom() {
        return payfrom;
    }

    public void setPayfrom(String payfrom) {
        this.payfrom = payfrom;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }
    
    
}
