package com.davidborza.billing.service.impl;

import com.davidborza.billing.entity.Bill;
import com.davidborza.billing.repository.BillRepository;
import com.davidborza.billing.service.BillService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Bill save(final Bill bill) {
        return billRepository.save(bill);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bill getById(final Long id) {
        final Optional<Bill> bill = billRepository.findById(id);
        if (bill.isPresent()) {
            return bill.get();
        }

        throw new EntityNotFoundException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bill update(final Long id, final Bill bill) {
        bill.setId(id);

        return billRepository.save(bill);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(final Long id) {
        billRepository.deleteById(id);
    }
}
