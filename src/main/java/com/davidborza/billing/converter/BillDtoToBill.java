package com.davidborza.billing.converter;

import com.davidborza.billing.entity.Bill;
import com.davidborza.billing.model.dto.BillDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Component
@RequiredArgsConstructor
public class BillDtoToBill implements Converter<BillDto, Bill> {

    /**
     * Convert BillDto to Bill.
     *
     * @param source The given BillDto object.
     * @return Bill object.
     */
    @Override
    public Bill convert(final @NonNull BillDto source) {
        final Bill bill = new Bill();
        bill.setId(source.getId());
        bill.setComment(source.getComment());
        bill.setPrice(source.getPrice());
        bill.setDueDate(source.getDueDate());
        bill.setBuyersName(source.getBuyersName());
        bill.setItemName(source.getItemName());
        bill.setCreatedDate(source.getCreatedDate());

        return bill;
    }
}
