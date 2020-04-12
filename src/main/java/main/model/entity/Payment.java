package main.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;

    private double amount;

    private Timestamp payment_date;

    public Payment() {
    }

    public Payment(int payment_id, double amount, Timestamp payment_date) {
        this.payment_id = payment_id;
        this.amount = amount;
        this.payment_date = payment_date;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Timestamp payment_date) {
        this.payment_date = payment_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return payment_id == payment.payment_id &&
                Double.compare(payment.amount, amount) == 0 &&
                Objects.equals(payment_date, payment.payment_date);}

    @Override
    public int hashCode() {
        return Objects.hash(payment_id, amount, payment_date);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_id=" + payment_id +
                ", amount=" + amount +
                ", payment_date=" + payment_date +
                '}';
    }
}
