package id.wonderdeal.wonderdealapps.utils;

import android.content.SharedPreferences;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import id.wonderdeal.wonderdealapps.WonderdealApp;

/**
 * Created by agustinaindah on 10/10/2017.
 */

public class Cart {

    /**
     * {
     "posts": {
     "unique_code": 123, -> generateUniqueCode()
     "grand_total": 500000, -> calculateGrandTotal()
     "metode_pembayaran": "transfer",
     "invoice": [
     {
     ----------- from hotpromo detail
     "merchant_id": 4,
     "merchant_name": "Sepatu Seekil",
     -----------
     ----------- chosen address
     "note": "jangan sampai lecet",
     "dest_address": "JL Gejayan No 32",
     "dest_province": "DI Yogyakarta",
     "dest_city": "KAB. GUNUNG KIDUL",
     "dest_subdistrict": "PATUK",
     "dest_zipcode": 55522,
     "dest_mobile_phone_number": "085878166698",
     ----------
     "items": [
     {
     "id": 5, -> hotpromoid
     "name": "Tas 2D, Tas 3D, Cartoon Bag First Date",
     "image": "57a4261e22b26.jpg",
     "qty": 1,
     "price": 250000,
     "weight": 1000
     }
     ]
     }
     ]
     }
     }
     */

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private int grandTotal;
    private String paymentMethod;
    private JsonArray jsonArrayInvoice;
    private JsonObject jsonObjectMerchant;


    public Cart() {
        this.mPref      = WonderdealApp.getInstance().Prefs();
        this.mEditor    = mPref.edit();
    }

    private void calculateGrandTotal() {
        // TODO: 11/3/16
        /**
         * merchant 1 :
         *  items [
         *      {qty = 2, price = 250000},
         *      {qty = 1, price = 100000}
         *  ]
         *  merchant 2 :
         *   items [
         *      {qty = 1, price = 80000}
         *   ]
         */
    }

    /**
     * generate random range
     * min : 100
     * max : 399
     * @return
     */
   /* public int generateUniqueCode() {
        return Helper.randInt(100,399);
    }*/

    public void setPaymentMethod(String method) {
        this.paymentMethod =  method;
    }

    public JsonArray getInvoice() {
        return null;
    }

    public JsonObject getMerchantById(int id) {
        return null;
    }

    public JsonArray getItemsByMerchantId(int id) {
        return null;
    }

    public void saveCart() {
        // TODO: 11/3/16
    }
}
