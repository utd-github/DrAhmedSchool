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
public class Payments {
    String  no,id,type,Amount,payto,fdate;

    public Payments(String no, String id, String type, String Amount, String payto, String date) {
        this.no = no;
        this.id = id;
        this.type = type;
        this.Amount = Amount;
        this.payto = payto;
        this.fdate = date;
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

    public String getPayto() {
        return payto;
    }

    public void setPayto(String payto) {
        this.payto = payto;
    }

    public String getDate() {
        return fdate;
    }

    public void setDate(String date) {
        this.fdate = date;
    }
  
}
