package nl.avans.android.todos.domain;

import org.joda.time.DateTime;

/**
 * Created by Matthijs on 19-6-2017.
 */

public class FilmHuur {

    private int rental_id;
    private DateTime rental_date;
    private int inventory_id;
    private int customer_id;
    private DateTime return_date;
    private int staff_id;
    private DateTime last_update;

    public FilmHuur(int staff_id){
        this.staff_id = staff_id;
    }

    public FilmHuur(int rental_id, DateTime rental_date, int inventory_id, int customer_id, DateTime return_date, int staff_id, DateTime last_update) {
        this.rental_id = rental_id;
        this.rental_date = rental_date;
        this.inventory_id = inventory_id;
        this.customer_id = customer_id;
        this.return_date = return_date;
        this.staff_id = staff_id;
        this.last_update = last_update;
    }


    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public DateTime getRental_date() {
        return rental_date;
    }

    public void setRental_date(DateTime rental_date) {
        this.rental_date = rental_date;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public DateTime getReturn_date() {
        return return_date;
    }

    public void setReturn_date(DateTime return_date) {
        this.return_date = return_date;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public DateTime getLast_update() {
        return last_update;
    }

    public void setLast_update(DateTime last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "FilmHuur{" +
                "staff_id=" + staff_id + '\'' +
                ", rental_date=" + rental_date +
                '}';
    }
}
