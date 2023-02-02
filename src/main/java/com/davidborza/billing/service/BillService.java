package com.davidborza.billing.service;

import com.davidborza.billing.entity.Bill;
import java.util.List;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
public interface BillService {

    /**
     * Save the given bill.
     *
     * @param bill The given bill.
     * @return Saved bill object.
     */
    Bill save(Bill bill);

    /**
     * Get all bills.
     *
     * @return List of bills.
     */
    List<Bill> getAll();

    /**
     * Get bill object by ID.
     *
     * @param id The given ID.
     * @return The found bill object.
     */
    Bill getById(Long id);

    /**
     * Update the given bill.
     *
     * @return The updated bill object.
     */
    Bill update(Long id, Bill bill);

    /**
     * Delete bill by ID.
     *
     * @param id The given ID.
     */
    void deleteById(Long id);
}
